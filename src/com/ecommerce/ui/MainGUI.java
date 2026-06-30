package com.ecommerce.ui;

import com.ecommerce.model.CartItem;
import com.ecommerce.service.CartService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainGUI extends Application {

    ListView<String> cartList = new ListView<>();

    public void start(Stage stage) {

        Label title = new Label("🛒 ShopEase");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane productGrid = new GridPane();
        productGrid.setHgap(15);
        productGrid.setVgap(15);

        String[] names = {
                "Shoes", "Watch", "Headphones", "Laptop", "Phone",
                "Bag", "Keyboard", "Mouse", "Charger", "Speaker",
                "Tablet", "Camera", "Bottle", "T-Shirt", "Jeans",
                "Cap", "Earbuds", "Powerbank", "Fan", "Mic"
        };

        double[] prices = {
                1500, 2500, 1200, 55000, 30000,
                800, 700, 500, 300, 1500,
                20000, 45000, 200, 600, 1200,
                300, 2000, 1200, 2500, 900
        };

        int col = 0, row = 0;

        for (int i = 0; i < names.length; i++) {

            VBox card = createProductCard(names[i], prices[i]);

            productGrid.add(card, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        Label cartLabel = new Label("Cart");
        cartLabel.setStyle("-fx-text-fill: white;");

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        checkoutBtn.setOnAction(e -> {
            new CheckoutGUI().start(new Stage());
        });

        cartList.setStyle("-fx-control-inner-background: #2b2b2b; -fx-text-fill: white;");

        VBox root = new VBox(15,
                title,
                new Label("Products") {{
                    setStyle("-fx-text-fill: white;");
                }},
                productGrid,
                cartLabel,
                cartList,
                checkoutBtn
        );

        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color:#1e1e1e; -fx-padding:20;");

        stage.setScene(new Scene(root, 750, 600));
        stage.setTitle("ShopEase Dashboard");
        stage.show();
    }

    private VBox createProductCard(String name, double price) {

        Label label = new Label(name + " ₹" + price);
        label.setStyle("-fx-text-fill: white;");

        Button addBtn = new Button("Add to Cart");
        addBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        addBtn.setOnAction(e -> {
            CartService.addToCart(new CartItem(name, price, 1));
            cartList.getItems().add(name + " ₹" + price);
        });

        VBox box = new VBox(10, label, addBtn);
        box.setStyle("-fx-background-color:#2b2b2b; -fx-padding:10; -fx-border-color: gray;");
        box.setPrefWidth(140);

        return box;
    }
}