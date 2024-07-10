CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

INSERT INTO users (username, password, enabled)
VALUES ('happy', '12345', TRUE);
CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(50) NOT NULL UNIQUE,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);
INSERT INTO authorities (username, authority)
VALUES ('happy', 'write');

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE customer
(
    customer_id   SERIAL
        PRIMARY KEY,
    first_name    VARCHAR(254) NOT NULL,
    last_name     VARCHAR(254) NOT NULL,
    middle_name   VARCHAR(254),
    email         VARCHAR(254) NOT NULL UNIQUE,
    mobile_number VARCHAR(20),
    pwd           VARCHAR(64)  NOT NULL,
    role          VARCHAR(50)  NOT NULL,
    is_active     BOOLEAN DEFAULT FALSE,
    create_dt     DATE    DEFAULT CURRENT_DATE
);

COMMENT ON TABLE customer IS 'Данные пользователя';
COMMENT ON COLUMN customer.customer_id IS 'Идентификатор записи';
COMMENT ON COLUMN customer.first_name IS 'Имя пользователя';
COMMENT ON COLUMN customer.last_name IS 'Фамилия пользователя';
COMMENT ON COLUMN customer.middle_name IS 'Отчество пользователя (опционально)';
COMMENT ON COLUMN customer.email IS 'Электронная почта';
COMMENT ON COLUMN customer.mobile_number IS 'Телефонный номер';
COMMENT ON COLUMN customer.pwd IS 'Пароль';
COMMENT ON COLUMN customer.role IS 'Роль';
COMMENT ON COLUMN customer.is_active IS 'Статус активности записи';
COMMENT ON COLUMN customer.create_dt IS 'Дата создания аккаунта';

DROP TABLE IF EXISTS user_account;
DROP TABLE IF EXISTS accounts;


CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

CREATE TABLE accounts
(
    customer_id    INT          NOT NULL,
    account_number INT PRIMARY KEY,
    account_type   VARCHAR(100) NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    create_dt      DATE DEFAULT CURRENT_DATE,
    CONSTRAINT customer_idfk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO accounts (customer_id, account_number, account_type, branch_address)
VALUES (1, 1865764534, 'Savings', 'Astana, Bokeychana st. 25');

SELECT *
FROM customer
         JOIN accounts USING (customer_id);

DROP TABLE IF EXISTS account_transactions;

CREATE TABLE account_transactions
(
    transaction_id      uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    account_number      INT          NOT NULL,
    customer_id         INT          NOT NULL,
    transaction_dt      DATE         NOT NULL,
    transaction_summary VARCHAR(200) NOT NULL,
    transaction_type    VARCHAR(100) NOT NULL,
    transaction_amt     INT          NOT NULL,
    closing_balance     INT          NOT NULL,
    create_dt           DATE DEFAULT NULL,
    CONSTRAINT account_number_fk_1 FOREIGN KEY (account_number) REFERENCES accounts (account_number) ON DELETE CASCADE,
    CONSTRAINT customer_id_fk_2 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO account_transactions (account_number, customer_id, transaction_dt, transaction_summary,
                                  transaction_type, transaction_amt,
                                  closing_balance, create_dt)
VALUES (1865764534, 1, CURRENT_DATE - INTERVAL '7 days', 'Coffee Shop', 'Withdrawal', 30, 34500,
        CURRENT_DATE - INTERVAL '7 days');

INSERT INTO account_transactions (account_number, customer_id, transaction_dt, transaction_summary,
                                  transaction_type, transaction_amt,
                                  closing_balance, create_dt)
VALUES (1865764534, 1, CURRENT_DATE - INTERVAL '6 days', 'Uber', 'Withdrawal', 100, 34400,
        CURRENT_DATE - INTERVAL '6 days'),
       (1865764534, 1, CURRENT_DATE - INTERVAL '5 days', 'Self Deposit', 'Deposit', 500, 34900,
        CURRENT_DATE - INTERVAL '5 days'),
       (1865764534, 1, CURRENT_DATE - INTERVAL '4 days', 'Ebay', 'Withdrawal', 600, 34300,
        CURRENT_DATE - INTERVAL '4 days'),
       (1865764534, 1, CURRENT_DATE - INTERVAL '2 days', 'OnlineTransfer', 'Deposit', 700, 35000,
        CURRENT_DATE - INTERVAL '2 days'),
       (1865764534, 1, CURRENT_DATE - INTERVAL '1 days', 'Amazon.com', 'Withdrawal', 100, 34900,
        CURRENT_DATE - INTERVAL '1 days');

CREATE TABLE loans
(
    loan_number        SERIAL PRIMARY KEY,
    customer_id        INT          NOT NULL,
    start_dt           DATE         NOT NULL,
    loan_type          VARCHAR(100) NOT NULL,
    total_loan         INT          NOT NULL,
    amount_paid        INT          NOT NULL,
    outstanding_amount INT          NOT NULL,
    create_dt          DATE DEFAULT NULL,
    CONSTRAINT loan_customer_id_fk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
VALUES (1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13'),
       (1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06'),
       (1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14'),
       (1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

CREATE TABLE cards
(
    card_id          SERIAL PRIMARY KEY,
    card_number      VARCHAR(100) NOT NULL,
    customer_id      INT          NOT NULL,
    card_type        VARCHAR(100) NOT NULL,
    total_limit      INT          NOT NULL,
    amount_used      INT          NOT NULL,
    available_amount INT          NOT NULL,
    create_dt        DATE DEFAULT NULL,
    CONSTRAINT card_customer_id_fk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURRENT_DATE),
       ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURRENT_DATE),
       ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURRENT_DATE);

CREATE TABLE notice_details
(
    notice_id      SERIAL PRIMARY KEY,
    notice_summary VARCHAR(200) NOT NULL,
    notice_details VARCHAR(500) NOT NULL,
    notic_beg_dt   DATE         NOT NULL,
    notic_end_dt   DATE DEFAULT NULL,
    create_dt      DATE DEFAULT NULL,
    update_dt      DATE DEFAULT NULL
);

INSERT INTO notice_details (notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt, update_dt)
VALUES ('Home Loan Interest rates reduced',
        'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL),
       ('Net Banking Offers',
        'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL),
       ('Mobile App Downtime',
        'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL),
       ('E Auction notice',
        'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL),
       ('Launch of Millennia Cards',
        'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL),
       ('COVID-19 Insurance',
        'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL);

CREATE TABLE contact_messages
(
    contact_id    VARCHAR(50)   PRIMARY KEY,
    contact_name  VARCHAR(50)   NOT NULL,
    contact_email VARCHAR(100)  NOT NULL,
    subject       VARCHAR(500)  NOT NULL,
    message       VARCHAR(2000) NOT NULL,
    create_dt     DATE DEFAULT NULL
);