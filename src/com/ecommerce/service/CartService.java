package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import java.util.*;

public class CartService {

    public static List<CartItem> cart = new ArrayList<>();

    public static void addToCart(CartItem item) {
        cart.add(item);
    }

    public static void clearCart() {
        cart.clear();
    }

    public static double getTotal() {
        double total = 0;
        for (CartItem c : cart) {
            total += c.getPrice() * c.getQuantity();
        }
        return total;
    }
}