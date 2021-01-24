package shop.discount;

import shop.ShoppingCart;
import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DiscountCondition {

    DecimalFormat decimalFormat = new DecimalFormat("0.###");

    ArrayList<DiscountInterface> discounts = new ArrayList<>();
    ArrayList<ShoppingCartItem> items;


    public DiscountCondition(ArrayList<ShoppingCartItem> items) {
        this.items = items;
    }

    public DiscountInterface getMostProfitDiscount() {
        DiscountInterface mostProfitDiscount = null;

        for (DiscountInterface discountInterface : discounts) {
            if (mostProfitDiscount == null) {
                mostProfitDiscount = discountInterface;
            }
            if (mostProfitDiscount.calculatePrice(items).doubleValue() > discountInterface.calculatePrice(items).doubleValue()) {
                mostProfitDiscount = discountInterface;
            }
        }
        return mostProfitDiscount;
    }


    public String receipt() {
        String line = "-------------------------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        var list = items.stream()
                .sorted(Comparator.comparing(item -> item.product().name()))
                .collect(Collectors.toList());
        for (var each : list) {
            sb.append(String.format("%s %-24s % 7.2f \n", each.quantity() + "x", each.product().name(), each.itemCost().multiply(new BigDecimal(each.quantity()))));
        }
        sb.append(line);
        sb.append(String.format("%28s% 8.2f \n", "TOTAL:", ShoppingCart.calculatePrice(items)));

        sb.append(String.format("%20s%s \n", "", getMostProfitDiscount().getDiscountName() + ": -" + decimalFormat.format(ShoppingCart.calculatePrice(items).subtract(getMostProfitDiscount().calculatePrice(items)).doubleValue()) + "kr")) ;
        sb.append(String.format("%28s% 8.2f", "To pay:", getMostProfitDiscount().calculatePrice(items)));
        return sb.toString();
    }




    public void addDiscount(DiscountInterface discountInterface) {
        discounts.add(discountInterface);
    }
}