-- =============================================================================
-- FLASH SALE ENGINE — Session 14 (MySQL 8+)
-- SRS Giai doan 1: DDL + FK + CHECK + Stored Procedures / Function
--
-- ERD (tom tat quan he):
--   Users (1) ----< Orders (1) ----< Order_Details >---- (1) Products
--
-- Khoi tao DB: CREATE DATABASE IF NOT EXISTS session14_flash_sale; USE session14_flash_sale;
-- Hoac chay Session14SchemaInitializer (doc file nay — cac khoi tach bang ---BLOCK---).
-- =============================================================================

---BLOCK---
SET NAMES utf8mb4
---BLOCK---
DROP PROCEDURE IF EXISTS SP_GetTopBuyers
---BLOCK---
DROP PROCEDURE IF EXISTS SP_GetCategoryRevenue
---BLOCK---
DROP FUNCTION IF EXISTS FUNC_CalculateCategoryRevenue
---BLOCK---
DROP TABLE IF EXISTS Order_Details
---BLOCK---
DROP TABLE IF EXISTS Orders
---BLOCK---
DROP TABLE IF EXISTS Products
---BLOCK---
DROP TABLE IF EXISTS Users
---BLOCK---
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB
---BLOCK---
CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT chk_products_stock_non_negative CHECK (stock >= 0)
) ENGINE=InnoDB
---BLOCK---
CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES Users (user_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB
---BLOCK---
CREATE TABLE Order_Details (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    CONSTRAINT fk_od_order FOREIGN KEY (order_id) REFERENCES Orders (order_id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_od_product FOREIGN KEY (product_id) REFERENCES Products (product_id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chk_od_quantity_positive CHECK (quantity > 0)
) ENGINE=InnoDB
---BLOCK---
CREATE PROCEDURE SP_GetTopBuyers()
BEGIN
    SELECT
        u.username AS username,
        CAST(SUM(od.quantity) AS UNSIGNED) AS total_quantity
    FROM Users u
    INNER JOIN Orders o ON o.user_id = u.user_id
    INNER JOIN Order_Details od ON od.order_id = o.order_id
    GROUP BY u.user_id, u.username
    ORDER BY total_quantity DESC
    LIMIT 5;
END
---BLOCK---
CREATE PROCEDURE SP_GetCategoryRevenue()
BEGIN
    SELECT
        p.category AS category,
        SUM(od.quantity * od.unit_price) AS revenue
    FROM Order_Details od
    INNER JOIN Products p ON p.product_id = od.product_id
    GROUP BY p.category;
END
---BLOCK---
CREATE FUNCTION FUNC_CalculateCategoryRevenue(cat VARCHAR(255))
RETURNS DECIMAL(12, 2)
READS SQL DATA
DETERMINISTIC
BEGIN
    DECLARE total DECIMAL(12, 2);
    SELECT COALESCE(SUM(od.quantity * od.unit_price), 0) INTO total
    FROM Order_Details od
    INNER JOIN Products p ON p.product_id = od.product_id
    WHERE p.category = cat;
    RETURN total;
END
