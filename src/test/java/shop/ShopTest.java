package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

    final ShoppingCart shoppingCart = new ShoppingCart();

    @BeforeEach
    void putSomeItemsInShoppingCart() {
        shoppingCart.addCartItem(new ShoppingCartItem(new Product("Milk"), 9.99, 2));
        shoppingCart.addCartItem(new ShoppingCartItem(new Product("Bread"), 3.0, 3));
        shoppingCart.addCartItem(new ShoppingCartItem(new Product("Butter"), 44.95, 1));
    }

    /*  @Test
    void payingFullPrice() {
        assertThat(shoppingCart.stream().mapToInt(ShoppingCartItem::quantity).sum()).isEqualTo(6);
        assertThat(shoppingCart.calculatePrice()).isEqualTo(BigDecimal.valueOf(73.93));
    }
    */
    @Test
    void createReceiptReturnsTextStringWithAllProducts(){
        assertThat(shoppingCart.receipt()).isEqualTo("""
                --------------------------------
                Bread                       3,00
                Butter                     44,95
                Milk                        9,99
                --------------------------------
                                  TOTAL:   73,93""");
    }
}
