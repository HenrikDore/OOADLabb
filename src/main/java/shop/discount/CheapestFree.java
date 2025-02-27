package shop.discount;

import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CheapestFree implements DiscountInterface {

    private String discountName = "Cheapest item discount";
    double cheapest = 0;


    @Override
    public String getDiscountName() {
        return discountName;
    }

    @Override
    public BigDecimal calculatePrice(ArrayList<ShoppingCartItem> items){
        var sum = BigDecimal.ZERO;
        for (var item: items) {
            if (cheapest == 0 || item.itemCost().doubleValue() < cheapest) {
                cheapest = item.itemCost().doubleValue();
            }
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }
        return sum.subtract(new BigDecimal(cheapest));
    }

}