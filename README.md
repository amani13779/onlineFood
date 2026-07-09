# Online Food Ordering System

## Overview
The **Online Food Ordering System** is a desktop application developed using Java Swing and SQL Server. The system enables users to browse restaurant menus, add meals to a shopping cart, and place food orders through a simple graphical user interface.

This project was developed as part of the **Visual Programming** course to apply object-oriented programming concepts, GUI development, and database connectivity in a practical desktop application.

---

## Features
- User Registration and Login
- Browse Restaurant Menu
- Add Meals to Shopping Cart
- Place Orders
- Order Tracking
- Admin Management
- Database Integration with SQL Server

---

## Technologies Used
- Java
- Java Swing
- SQL Server
- JDBC
- NetBeans IDE

---

## Project Structure

```text
onlineFood/
│
├── Source Packages/
│   └── onlinefood/
│       ├── AdminPage.java
│       ├── CartPage.java
│       ├── CustomerPage.java
│       ├── DBConnection.java
│       ├── LoginPage.java
│       ├── Meal.java
│       ├── MenuPage.java
│       ├── OnlineFood.java
│       ├── OrderTrackingPage.java
│       └── SignUpPage.java
│
├── README.md
└── food_ordering_system.sql
```

---

## File Description

| File | Description |
|------|-------------|
| OnlineFood.java | Main entry point of the application. |
| LoginPage.java | User login interface. |
| SignUpPage.java | User registration interface. |
| CustomerPage.java | Customer dashboard. |
| MenuPage.java | Displays available meals. |
| CartPage.java | Shopping cart management. |
| OrderTrackingPage.java | Order tracking interface. |
| AdminPage.java | Administrator interface. |
| Meal.java | Meal data model. |
| DBConnection.java | Database connection using JDBC. |

---

## Database
The application uses **SQL Server** as the backend database. Import the provided SQL file before running the project and update the database connection settings if needed.

---

## How to Run
1. Clone the repository.
2. Open the project in NetBeans.
3. Import the SQL Server database.
4. Configure the database connection.
5. Build and run the application.

---

## Learning Outcomes
- Object-Oriented Programming (OOP)
- Java Swing GUI Development
- JDBC Connectivity
- SQL Server Database Integration
- Event-Driven Programming
- Desktop Application Development

---

## Future Improvements
- Online Payment Integration
- Customer Reviews and Ratings
- Restaurant Management Module
- Improved User Interface
- Mobile Version

---
