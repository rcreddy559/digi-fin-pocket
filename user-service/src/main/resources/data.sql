-- Sample users for user-service
-- Using ON CONFLICT DO NOTHING to prevent duplicate inserts on application restart

-- Create or update table user

CREATE TABLE IF NOT EXISTS "user" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INTEGER NOT NULL
);  

INSERT INTO "user" (id, name, user_name, email, phone_number, password, role)
VALUES (1, 'Alice Johnson', 'alice.johnson', 'alice.johnson@example.com', '+1-555-0101', 'hashed_password_1', 0)
ON CONFLICT (id) DO NOTHING;

INSERT INTO "user" (id, name, user_name, email, phone_number, password, role)
VALUES (2, 'Bob Smith', 'bob.smith', 'bob.smith@example.com', '+1-555-0102', 'hashed_password_2', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO "user" (id, name, user_name, email, phone_number, password, role)
VALUES (3, 'Charlie Brown', 'charlie.brown', 'charlie.brown@example.com', '+1-555-0103', 'hashed_password_3', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO "user" (id, name, user_name, email, phone_number, password, role)
VALUES (4, 'Diana Prince', 'diana.prince', 'diana.prince@example.com', '+1-555-0104', 'hashed_password_4', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO "user" (id, name, user_name, email, phone_number, password, role)
VALUES (5, 'Eve Martinez', 'eve.martinez', 'eve.martinez@example.com', '+1-555-0105', 'hashed_password_5', 0)
ON CONFLICT (id) DO NOTHING;
