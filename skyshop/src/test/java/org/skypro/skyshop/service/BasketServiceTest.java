package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    void getUserBasket_shouldThrowException_whenProductMissing() {

        UUID nonExistentProductID = UUID.randomUUID();

        when(storageService.getProductById(nonExistentProductID))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(nonExistentProductID));

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void addExistingProduct_shouldEvokeAddProduct() {
        UUID productID = UUID.randomUUID();
        productBasket.addProduct(productID);
        verify(productBasket).addProduct(productID);
    }

    @Test
    void getEmptyBasket() {
        when(productBasket.getBasket()).
                thenReturn(Map.of());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getItems().isEmpty());
        verify(productBasket).getBasket();
        verifyNoInteractions(storageService);
    }

    @Test
    void getUserBasket_shouldReturnCorrectBasket_whenProductsExist() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        Product product1 = new SimpleProduct("Phone", 50000, productId1);
        Product product2 = new SimpleProduct("Laptop", 100000, productId2);


        when(productBasket.getBasket())
                .thenReturn(Map.of(
                        productId1, 1,
                        productId2, 2
                ));


        when(storageService.getProductById(productId1))
                .thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2))
                .thenReturn(Optional.of(product2));

        UserBasket result = basketService.getUserBasket();

        assertEquals(2, result.getItems().size());

        BasketItem item1 = result.getItems().get(0);
        assertEquals(product1, item1.getProduct());
        assertEquals(1, item1.getAmount());

        BasketItem item2 = result.getItems().get(1);
        assertEquals(product2, item2.getProduct());
        assertEquals(2, item2.getAmount());


        verify(productBasket).getBasket();
        verify(storageService, times(2)).getProductById(any());

    }


}
