-- Sample data for expenses table
-- Note: id is auto-generated, so it's not included in the INSERT statements

INSERT INTO expenses (user_id, amount, category, expense_date, payment_mode, description) VALUES
(1, 450.00, 'FOOD', '2026-01-15', 'UPI', 'Grocery shopping at local supermarket'),
(1, 1200.00, 'TRANSPORT', '2026-01-16', 'DEBIT_CARD', 'Monthly fuel expense'),
(2, 15000.00, 'RENT', '2026-01-01', 'NET_BANKING', 'Monthly apartment rent'),
(2, 2500.00, 'UTILITIES', '2026-01-05', 'CREDIT_CARD', 'Electricity and water bill'),
(3, 800.00, 'ENTERTAINMENT', '2026-01-18', 'CREDIT_CARD', 'Movie tickets and dinner'),
(1, 3500.00, 'HEALTH', '2026-01-10', 'DEBIT_CARD', 'Medical checkup and medicines'),
(3, 2200.00, 'SHOPPING', '2026-01-20', 'WALLET', 'Winter clothing purchase'),
(2, 5000.00, 'EDUCATION', '2026-01-12', 'NET_BANKING', 'Online course subscription'),
(1, 350.00, 'FOOD', '2026-01-22', 'CASH', 'Restaurant dinner with family'),
(2, 180.00, 'TRANSPORT', '2026-01-23', 'UPI', 'Auto rickshaw fare'),
(3, 1500.00, 'UTILITIES', '2026-01-08', 'DEBIT_CARD', 'Internet and mobile bill'),
(1, 950.00, 'ENTERTAINMENT', '2026-01-25', 'CREDIT_CARD', 'Streaming service subscriptions'),
(2, 4200.00, 'HEALTH', '2026-01-14', 'CREDIT_CARD', 'Dental treatment'),
(3, 3800.00, 'SHOPPING', '2026-01-26', 'DEBIT_CARD', 'Electronics purchase'),
(1, 600.00, 'FOOD', '2026-01-27', 'UPI', 'Weekly grocery shopping'),
(2, 12000.00, 'EDUCATION', '2026-01-03', 'NET_BANKING', 'Professional certification fee'),
(3, 250.00, 'TRANSPORT', '2026-01-28', 'CASH', 'Local bus and metro fare'),
(1, 1500.00, 'UTILITIES', '2026-01-11', 'UPI', 'Gas cylinder and maintenance'),
(2, 890.00, 'ENTERTAINMENT', '2026-01-19', 'WALLET', 'Concert tickets'),
(3, 500.00, 'OTHER', '2026-01-21', 'CASH', 'Miscellaneous household items');


-- Sample data for income table
INSERT INTO income (user_id, amount, source, date, payment_mode, description) VALUES
(1, 75000.00, 'SALARY', '2026-01-01', 'NET_BANKING', 'Monthly salary for January'),
(2, 65000.00, 'SALARY', '2026-01-01', 'NET_BANKING', 'Monthly salary for January'),
(3, 55000.00, 'SALARY', '2026-01-01', 'NET_BANKING', 'Monthly salary for January'),
(1, 5000.00, 'INVESTMENT', '2026-01-10', 'DEBIT_CARD', 'Dividend from stocks'),
(2, 12000.00, 'BUSINESS', '2026-01-15', 'UPI', 'Freelance project payment'),
(3, 2000.00, 'OTHER', '2026-01-20', 'CASH', 'Gift from relative'),
(1, 1500.00, 'INVESTMENT', '2026-01-25', 'NET_BANKING', 'Interest from savings account'),
(2, 8000.00, 'BUSINESS', '2026-01-28', 'UPI', 'Consulting fee'),
(3, 10000.00, 'SALARY', '2026-01-15', 'NET_BANKING', 'Mid-month bonus'),
(1, 3000.00, 'OTHER', '2026-01-22', 'WALLET', 'Cashback reward'),
(2, 4500.00, 'INVESTMENT', '2026-01-12', 'DEBIT_CARD', 'Mutual fund returns'),
(3, 7000.00, 'BUSINESS', '2026-01-05', 'UPI', 'Sales commission');
