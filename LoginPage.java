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
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame {
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginBtn = new JButton("Login");
    JButton signupBtn = new JButton("Sign Up");

    public LoginPage() {
        setTitle("Login");
        setSize(300, 220);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        JLabel passLabel = new JLabel("Password:");

        emailLabel.setBounds(20, 20, 80, 25);
        passLabel.setBounds(20, 60, 80, 25);
        emailField.setBounds(100, 20, 150, 25);
        passwordField.setBounds(100, 60, 150, 25);
        loginBtn.setBounds(40, 110, 100, 25);
        signupBtn.setBounds(150, 110, 100, 25);

        add(emailLabel);
        add(passLabel);
        add(emailField);
        add(passwordField);
        add(loginBtn);
        add(signupBtn);

        loginBtn.addActionListener(e -> login());
        signupBtn.addActionListener(e -> openSignUpPage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void openSignUpPage() {
        dispose();
        new SignUpPage();
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int userId = rs.getInt("id");

                if ("customer".equalsIgnoreCase(role)) {
                    JOptionPane.showMessageDialog(this, "Login successful! Role: Customer");
                    dispose();
                    new CustomerPage(userId);
                } else if ("admin".equalsIgnoreCase(role)) {
                    JOptionPane.showMessageDialog(this, "Login successful! Role: Admin");
                    dispose();
                    new AdminPage();  
                } else {
                    JOptionPane.showMessageDialog(this, "Unknown role.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while connecting to the database.");
        }
    }
}