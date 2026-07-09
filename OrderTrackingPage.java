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

public class OrderTrackingPage extends JFrame {
    private int customerId;
    private JLabel timerLabel;
    private int countdownSeconds = 2700;
    private String[] statuses = {"Preparing", "Out for Delivery", "Delivered"};
    private int currentStatusIndex = 0;
    private boolean isDelivered = false;

    public OrderTrackingPage(int customerId) {
        this.customerId = customerId;

        setTitle("Order Tracking");
        setSize(200, 200);
        setLayout(new BorderLayout());

        timerLabel = new JLabel("", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(timerLabel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        startTracking();
    }

    private void startTracking() {
        Thread trackingThread = new Thread(() -> {
            while (countdownSeconds >= 0) {
                int minutes = countdownSeconds / 60;
                int seconds = countdownSeconds % 60;

                if (countdownSeconds >= 2400) {
                    if (currentStatusIndex != 0) {
                        currentStatusIndex = 0;
                    }
                } else if (countdownSeconds >= 1800) {
                    if (currentStatusIndex != 1) {
                        currentStatusIndex = 1;
                    }
                } else if (countdownSeconds >= 0) {
                    if (currentStatusIndex != 2) {
                        currentStatusIndex = 2;
                    }
                }

                String status = statuses[currentStatusIndex];
                if (status.equals("Delivered")) {
                    isDelivered = true;
                }

                timerLabel.setText(String.format("Time left: %02d:%02d\nStatus: %s", minutes, seconds, status));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countdownSeconds--;
            }

            if (isDelivered) {
                timerLabel.setText("Order Delivered");
            } else {
                timerLabel.setText("Time's up!");
            }
        });

        trackingThread.start();
    }

  
}
