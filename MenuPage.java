/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinefood;

/**
 *
 * @author asus
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class MenuPage extends JFrame {
    private int restaurantId;
    private int customerId;
    private ArrayList<Meal> cart;

    public MenuPage(int restaurantId, int customerId, ArrayList<Meal> cart) {
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.cart = cart;

        setTitle("Meal Menu");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel mealPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(mealPanel);
        add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM menu_items WHERE restaurant_id = ?");
            stmt.setInt(1, restaurantId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int mealId = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");

                Meal meal = new Meal(mealId, name, price);
                JButton mealBtn = new JButton(meal.toString());
                mealBtn.addActionListener(e -> {
                    cart.add(meal);
                    JOptionPane.showMessageDialog(this, name + " added to the cart.");
                });
                mealPanel.add(mealBtn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cartBtn = new JButton("View Cart");
        JButton backBtn = new JButton("Back");

        cartBtn.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new CartPage(cart, restaurantId, customerId);
            }
        });

        backBtn.addActionListener(e -> dispose());

        bottomPanel.add(backBtn);
        bottomPanel.add(cartBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
