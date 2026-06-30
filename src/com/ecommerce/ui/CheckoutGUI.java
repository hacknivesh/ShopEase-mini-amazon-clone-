package com.ecommerce.ui;

import com.ecommerce.service.CartService;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckoutGUI {

    public void start(Stage stage) {

        TextField address = new TextField();
        address.setPromptText("Enter Address");

        ComboBox<String> payment = new ComboBox<>();
        payment.getItems().addAll("UPI", "Card", "Cash on Delivery");

        Button placeOrder = new Button("Place Order");

        placeOrder.setOnAction(e -> {

            if (address.getText().isEmpty() || payment.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "Fill all details").show();
                return;
            }

            double total = CartService.getTotal();

            CartService.clearCart();

            new Alert(Alert.AlertType.INFORMATION,
                    "✅ Order Placed!\nTotal: ₹" + total
            ).show();

            stage.close();
        });

        VBox root = new VBox(10,
                new Label("Checkout"),
                address,
                payment,
                placeOrder
        );

        root.setStyle("-fx-padding:20;");

        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Checkout");
        stage.show();
    }
}