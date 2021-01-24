package shop;

import shop.discount.CheapestFree;
import shop.discount.DiscountOver500;
import shop.discount.ThreeForTwo;
import shop.history.HistoryStack;

public class Main {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        Product jacket = new Product("Jacket");
        Product hat = new Product("Hat");
        Product pants = new Product("Pants");
        Product tshirt = new Product("T-shirt");


        cart.addCartItem(new ShoppingCartItem(pants, 500, 1));
        cart.addCartItem(new ShoppingCartItem(jacket, 1000, 1));
        cart.addCartItem(new ShoppingCartItem(tshirt, 200, 1));
        cart.addCartItem(new ShoppingCartItem(hat, 100, 1));


        cart.discountCondition.addDiscount(new CheapestFree());
        cart.discountCondition.addDiscount(new ThreeForTwo());
        cart.discountCondition.addDiscount(new DiscountOver500());

        System.out.println(cart.receipt());

       HistoryStack.undo();

        System.out.println(cart.receipt());

        HistoryStack.redo();
        System.out.println(cart.receipt());

    }
}
