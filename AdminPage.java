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

public class AdminPage extends JFrame {

    public AdminPage() {
        setTitle("Admin Dashboard");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Admin Control Panel", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        add(header, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton manageRestaurantsBtn = new JButton("View Restaurants");
        JButton manageOrdersBtn = new JButton("View All Orders");
        JButton manageUsersBtn = new JButton("View All Users");
        JButton logoutBtn = new JButton("Logout");

        manageRestaurantsBtn.addActionListener(e -> showRestaurants());
        manageOrdersBtn.addActionListener(e -> showOrders());
        manageUsersBtn.addActionListener(e -> showUsers());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        buttonPanel.add(manageRestaurantsBtn);
        buttonPanel.add(manageOrdersBtn);
        buttonPanel.add(manageUsersBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showRestaurants() {
        JFrame frame = new JFrame("Restaurant List");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        frame.add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM restaurants");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" | Name: ").append(rs.getString("name"))
                  .append(" | Category: ").append(rs.getString("category"))
                  .append(" | Location: ").append(rs.getString("location"))
                  .append(" | Rating: ").append(rs.getDouble("rating"))
                  .append("\n");
            }
            area.setText(sb.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading restaurants: " + ex.getMessage());
        }

        frame.setVisible(true);
    }

    private void showOrders() {
        JFrame frame = new JFrame("All Orders");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        frame.add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Order ID: ").append(rs.getInt("id"))
                  .append(" | User ID: ").append(rs.getInt("user_id"))
                  .append(" | Total: ").append(rs.getDouble("total_price")).append(" SAR")
                  .append(" | Date: ").append(rs.getTimestamp("order_date"))
                  .append("\n");
            }
            area.setText(sb.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading orders: " + ex.getMessage());
        }

        frame.setVisible(true);
    }

    private void showUsers() {
JFrame frame = new JFrame("User List");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        frame.add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" | Name: ").append(rs.getString("name"))
                  .append(" | Email: ").append(rs.getString("email"))
                  .append(" | Role: ").append(rs.getString("role"))
                  .append("\n");
            }
            area.setText(sb.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading users: " + ex.getMessage());
        }

        frame.setVisible(true);
    }
}