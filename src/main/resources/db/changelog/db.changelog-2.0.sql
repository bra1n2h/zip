-- liquibase formatted sql

INSERT INTO company (id, name)
VALUES (1, 'Google'),
       (2, 'Meta'),
       (3, 'Amazon');

-- Insert company locales
INSERT INTO company_locales (company_id, lang, description)
VALUES ((SELECT id FROM company WHERE name = 'Google'), 'en', 'Google description'),
       ((SELECT id FROM company WHERE name = 'Google'), 'ru', 'Google описание'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'en', 'Meta description'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'ru', 'Meta описание'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'en', 'Amazon description'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'ru', 'Amazon описание');

-- Insert users
INSERT INTO users (birth_date, firstname, lastname, role, username, company_id)
VALUES ('1990-01-10', 'Ivan', 'Ivanov', 'ADMIN', 'ivan@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       ('2000-10-19', 'Petr', 'Petrov', 'USER', 'petr@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       ('1985-02-23', 'Sveta', 'Svetikova', 'USER', 'sveta@gmail.com', (SELECT id FROM company WHERE name = 'Meta')),
       ('2002-03-14', 'Vlad', 'Vladikov', 'USER', 'vlad@gmail.com', (SELECT id FROM company WHERE name = 'Amazon')),
       ('2002-03-14', 'Kate', 'Smith', 'ADMIN', 'kate@gmail.com', (SELECT id FROM company WHERE name = 'Amazon'));

-- Insert payments
INSERT INTO payment (amount, receiver_id)
VALUES (100, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (400, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (600, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (700, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (340, (SELECT id FROM users WHERE username = 'sveta@gmail.com')),
       (670, (SELECT id FROM users WHERE username = 'sveta@gmail.com')),
       (800, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (900, (SELECT id FROM users WHERE username = 'kate@gmail.com'));

-- Insert chats
INSERT INTO chat (id, name)
VALUES (1, 'java'),
       (2, 'minsk'),
       (3, 'database');

-- Insert users_chats
INSERT INTO users_chat (user_id, chat_id)
VALUES ((SELECT id FROM users WHERE username = 'ivan@gmail.com'), (SELECT id FROM chat WHERE name = 'minsk')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'minsk')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE name = 'minsk')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'vlad@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'database')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'database'));
