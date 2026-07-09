/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinefood;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.ArrayList;
public class CartPage extends JFrame {
    private ArrayList<Meal> cart;
    private double orderAmount;
    private double deliveryFee;
    private double discount = 0.0;
    private String discountCode = "";
    private JLabel summary;
    private int restaurantId;
    private int customerId;

    public CartPage(ArrayList<Meal> cart, int restaurantId, int customerId) {
        this.cart = cart;
        this.restaurantId = restaurantId;
        this.customerId = customerId;

        setTitle("Shopping Cart");
        setSize(350, 500);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        orderAmount = 0.0;
        for (Meal meal : cart) {
            orderAmount += meal.getPrice();
        }


        deliveryFee = orderAmount > 60 ? 0.0 : 10.0;

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        summary = new JLabel();
        updateSummary();
        summaryPanel.add(summary);
        mainPanel.add(summaryPanel);

        mainPanel.add(Box.createVerticalStrut(15));

        JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        discountPanel.add(new JLabel("Enter Discount Code:"));
        JTextField discountField = new JTextField(12);
        discountPanel.add(discountField);
        mainPanel.add(discountPanel);

        mainPanel.add(Box.createVerticalStrut(10));

        JButton applyDiscountBtn = new JButton("Apply Discount");
        applyDiscountBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyDiscountBtn.addActionListener(e -> {
            discountCode = discountField.getText();
            if ("CS342".equals(discountCode)) {
                discount = orderAmount * 0.25;
                JOptionPane.showMessageDialog(this, "Applied 25% Discount");
            } else {
                discount = 0.0;
                JOptionPane.showMessageDialog(this, "Invalid Discount Code.");
            }
            updateSummary();
        });
        mainPanel.add(applyDiscountBtn);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JRadioButton cashOnDelivery = new JRadioButton("Cash on Delivery");
        JRadioButton cardPayment = new JRadioButton("Credit Card Payment");
        ButtonGroup group = new ButtonGroup();
        group.add(cashOnDelivery);
        group.add(cardPayment);
        cashOnDelivery.setSelected(true);
        paymentPanel.add(cashOnDelivery);
        paymentPanel.add(cardPayment);
        mainPanel.add(paymentPanel);

        mainPanel.add(Box.createVerticalStrut(15));

        JButton nowBtn = new JButton("Complete Order Now");
        nowBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        nowBtn.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Your cart is empty.");
            } else {
                String paymentMethod = cashOnDelivery.isSelected() ? "Cash on Delivery" : "Credit Card Payment";
                String message = "Order Completed Successfully!\nPayment Method: " + paymentMethod +
                        "\nAmount: " + orderAmount + " SAR" +
                        "\nDiscount: " + discount + " SAR" +
                        "\nDelivery Fee: " + deliveryFee + " SAR" +
                        "\nTotal: " + (orderAmount - discount + deliveryFee) + " SAR";
                JOptionPane.showMessageDialog(this, message);

                new ReviewPage(restaurantId, customerId);
                dispose();
            }
        });
        mainPanel.add(nowBtn);

        mainPanel.add(Box.createVerticalStrut(10));

        JButton laterBtn = new JButton("Complete Order After One Hour");
        laterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        laterBtn.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Your cart is empty.");
            } else {
                String deliveryTime = JOptionPane.showInputDialog(this, "Enter Delivery Time (e.g., 3:00 PM)");
                String message = "Order Completed Successfully!\nDelivery Time: " + deliveryTime +
                        "\nAmount: " + orderAmount + " SAR" +
                        "\nDiscount: " + discount + " SAR" +
                        "\nDelivery Fee: " + deliveryFee + " SAR" +
                        "\nTotal: " + (orderAmount - discount + deliveryFee) + " SAR";
                JOptionPane.showMessageDialog(this, message);

                new ReviewPage(restaurantId, customerId);
                dispose();
            }
        });
        mainPanel.add(laterBtn);

        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateSummary() {
        double total = orderAmount - discount + deliveryFee;

      
        if (orderAmount - discount < 60) {
            deliveryFee = 10.0;
        }

        StringBuilder updatedItems = new StringBuilder();
        updatedItems.append("<html><div style='text-align: left;'>Meals:<br><br>");
        for (Meal meal : cart) {
            updatedItems.append(meal.toString()).append("<br>");
        }

        updatedItems.append("<br>Amount: ").append(orderAmount).append(" SAR<br>");
        updatedItems.append("Discount: ").append(discount).append(" SAR<br>");
        updatedItems.append("Delivery Fee: ").append(deliveryFee).append(" SAR<br>");
        updatedItems.append("<b>Total: ").append(total).append(" SAR</b></div></html>");

        summary.setText(updatedItems.toString());
    }

    public class ReviewPage extends JFrame {
        private int restaurantId;
        private int customerId;

        public ReviewPage(int restaurantId, int customerId) {
            this.restaurantId = restaurantId;
            this.customerId = customerId;

            setTitle("Restaurant Review");
            setSize(400, 400);
            setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            ratingPanel.add(new JLabel("Rate the restaurant (1 to 5):"));
            Integer[] ratings = {1, 2, 3, 4, 5};
            JComboBox<Integer> ratingComboBox = new JComboBox<>(ratings);
            ratingPanel.add(ratingComboBox);
            panel.add(ratingPanel);

            JPanel reviewLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            reviewLabelPanel.add(new JLabel("Write your review (optional):"));
            panel.add(reviewLabelPanel);

            JTextArea reviewTextArea = new JTextArea(5, 30);
            reviewTextArea.setLineWrap(true);
            reviewTextArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(reviewTextArea);
            panel.add(scrollPane);

            JButton submitReviewButton = new JButton("Submit Review");
            submitReviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitReviewButton.addActionListener(e -> {
                String reviewText = reviewTextArea.getText().trim();
                int rating = (Integer) ratingComboBox.getSelectedItem();

                if (!reviewText.isEmpty() || rating >= 1) {
                    saveReview(rating, reviewText);
                    JOptionPane.showMessageDialog(this, "Thank you! Your review has been submitted.");
                }
                dispose();
            });

            panel.add(Box.createVerticalStrut(10));
            panel.add(submitReviewButton);

            add(panel, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private void saveReview(int rating, String reviewText) {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO reviews (user_id, restaurant_id, rating, review_text, review_date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, customerId);
                stmt.setInt(2, restaurantId);
                stmt.setInt(3, rating);
                stmt.setString(4, reviewText);
                stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving review.");
            }
        }
    }
}
