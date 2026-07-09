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
import java.sql.*;
import java.util.ArrayList;

public class CustomerPage {
    private JFrame frame;
    private JPanel restaurantPanel;
    private ArrayList<Meal> cart = new ArrayList<>();
    private int customerId;

    public CustomerPage(int customerId) {
        this.customerId = customerId;
        frame = new JFrame("Restaurant List");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        restaurantPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(restaurantPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        loadRestaurants();

        
        JButton trackOrdersBtn = new JButton("Track My Orders");
        trackOrdersBtn.addActionListener(e -> new OrderTrackingPage(customerId));
        frame.add(trackOrdersBtn, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadRestaurants() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM restaurants");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                double rating = rs.getDouble("rating");

                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBorder(BorderFactory.createTitledBorder(name));
                card.add(new JLabel("Location: " + location));
                card.add(new JLabel("Rating: " + rating));

                JButton menuBtn = new JButton("View Menu");
                menuBtn.addActionListener(e -> new MenuPage(id, customerId, cart));

                card.add(menuBtn);
                restaurantPanel.add(card);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}