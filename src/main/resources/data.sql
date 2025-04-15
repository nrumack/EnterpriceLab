-- Insert addresses
INSERT INTO address (street, city, postal_code) VALUES ( '123 Maple St', 'Springfield', '12345');
INSERT INTO address (street, city, postal_code) VALUES ('456 Oak Ave', 'Riverdale', '23456');
INSERT INTO address (street, city, postal_code) VALUES ('789 Pine Ln', 'Hill Valley', '34567');
INSERT INTO address (street, city, postal_code) VALUES ('101 Birch Blvd', 'Gotham', '45678');
INSERT INTO address (street, city, postal_code) VALUES ('202 Cedar Rd', 'Metropolis', '56789');

-- Insert members
INSERT INTO memberdb (first_name, last_name, email, phone, dateofbirth) VALUES
('Alice', 'Johnson',  'alice.johnson@example.com', '123-456-7890', '1990-01-15'),
('Bob', 'Smith',  'bob.smith@example.com', '234-567-8901', '1985-05-22'),
('Charlie', 'Brown',  'charlie.brown@example.com', '345-678-9012', '1992-07-08'),
('Diana', 'Prince', 'diana.prince@example.com', '456-789-0123', '1988-11-03'),
('Ethan', 'Hunt', 'ethan.hunt@example.com', '567-890-1234', '1979-09-30');

ALTER TABLE address ALTER COLUMN id RESTART WITH 6;

