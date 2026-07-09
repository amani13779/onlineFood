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

public class SignUpPage extends JFrame {
    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JComboBox<String> roleBox = new JComboBox<>(new String[]{"customer", "admin"});
    JButton signupBtn = new JButton("Sign Up");

    public SignUpPage() {
        setTitle("Sign Up");
        setSize(300, 300);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passLabel = new JLabel("Password:");
        JLabel roleLabel = new JLabel("Role:");

        nameLabel.setBounds(20, 20, 80, 25);
        emailLabel.setBounds(20, 60, 80, 25);
        passLabel.setBounds(20, 100, 80, 25);
        roleLabel.setBounds(20, 140, 80, 25);

        nameField.setBounds(100, 20, 150, 25);
        emailField.setBounds(100, 60, 150, 25);
        passwordField.setBounds(100, 100, 150, 25);
        roleBox.setBounds(100, 140, 150, 25);
        signupBtn.setBounds(90, 190, 100, 25);

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passLabel);
        add(passwordField);
        add(roleLabel);
        add(roleBox);
        add(signupBtn);

        signupBtn.addActionListener(e -> signup());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void signup() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = (String) roleBox.getSelectedItem();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this, "All fields must be filled.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed.");
                return;
            }

            String checkEmailSql = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Email is already in use.");
                    return;
                }

                String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, name);
                    stmt.setString(2, email);
                    stmt.setString(3, password);
                    stmt.setString(4, role);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "User registered successfully!");
                    dispose();  
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}