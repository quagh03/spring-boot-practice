DROP DATABASE IF EXISTS db_practice_ecommerce;

CREATE DATABASE IF NOT EXISTS db_practice_ecommerce;

USE db_practice_ecommerce;
SET time_zone = "+07:00";

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `fullname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` ENUM('ROLE_ADMIN', 'ROLE_USER') DEFAULT 'ROLE_USER' NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `date_of_birth` date DEFAULT NULL,
  `facebook_account_id` int DEFAULT '0',
  `google_account_id` int DEFAULT '0',
  `role_id` int DEFAULT '1',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `is_active` tinyint(1),
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `coupons` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `code` varchar(50) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `coupon_conditions` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `coupon_id` int NOT NULL,
  `attribute` varchar(255) NOT NULL,
  `operator` varchar(10) NOT NULL,
  `value` varchar(255) NOT NULL,
  `discount_amount` decimal(5,2) NOT NULL,
  FOREIGN KEY (`coupon_id`) REFERENCES `coupons`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(350) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Tên sản phẩm',
  `price` float DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `is_active` tinyint(1),
  FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `favorites` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product_images` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product_id` int DEFAULT NULL,
  `image_url` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `social_accounts` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `provider` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Tên nhà social network',
  `provider_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Email tài khoản',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Tên người dùng',
  `user_id` int DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tokens` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `user_id` int DEFAULT NULL,
  `is_mobile` tinyint(1) DEFAULT '0',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `refresh_expiration_date` datetime DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` int DEFAULT NULL,
  `fullname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `note` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('pending','processing','shipped','delivered','cancelled') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Trạng thái đơn hàng',
  `total_money` float DEFAULT NULL,
  `shipping_method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `shipping_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `shipping_date` date DEFAULT NULL,
  `tracking_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `payment_method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `coupon_id` int DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY (`coupon_id`) REFERENCES `coupons`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `number_of_products` int DEFAULT '1',
  `total_money` float DEFAULT '0.00',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `coupon_id` int DEFAULT NULL,
  FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
  FOREIGN KEY (`coupon_id`) REFERENCES `coupons`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO categories (name, is_active) VALUES
('Technology',1),
('Laptop',1),
('Phone',1),
('PC',1);

-- Inserting products
INSERT INTO products (name, price, thumbnail, description, created_at, updated_at, category_id, is_active) VALUES
('MacBook Pro', 1999.99, 'macbook_pro_thumbnail.jpg', 'Powerful and portable laptop by Apple.', NOW(), NOW(), 2,1),
('iPhone 13', 899.99, 'iphone_13_thumbnail.jpg', 'Latest smartphone from Apple with advanced features.', NOW(), NOW(), 3,1),
('Dell XPS 13', 1499.99, 'dell_xps_13_thumbnail.jpg', 'High-performance laptop with a stunning display.', NOW(), NOW(), 2,1),
('Google Pixel 6', 799.99, 'google_pixel_6_thumbnail.jpg', 'Innovative smartphone by Google with exceptional camera capabilities.', NOW(), NOW(), 3,0),
('Alienware Aurora', 2499.99, 'alienware_aurora_thumbnail.jpg', 'Powerful gaming PC designed for immersive gaming experiences.', NOW(), NOW(), 4,0);

-- Inserting product images
INSERT INTO product_images (product_id, image_url) VALUES
(1, 'macbook_pro_image1.jpg'),
(1, 'macbook_pro_image2.jpg'),
(2, 'iphone_13_image1.jpg'),
(2, 'iphone_13_image2.jpg'),
(3, 'dell_xps_13_image1.jpg'),
(4, 'google_pixel_6_image1.jpg'),
(5, 'alienware_aurora_image1.jpg');
