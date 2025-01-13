INSERT INTO types (name) VALUES ('Perro');
INSERT INTO types (name) VALUES ('Gato');
INSERT INTO types (name) VALUES ('Pajaro');

INSERT INTO pets (name, age, type_id, available) VALUES ('Rex', 2, 1, true);
INSERT INTO pets (name, age, type_id, available) VALUES ('Max', 5, 1, true);
INSERT INTO pets (name, age, type_id, available) VALUES ('Luna', 1, 2, false);
INSERT INTO pets (name, age, type_id, available) VALUES ('Sol', 1, 3, true);

INSERT INTO users (name, email, phone) VALUES ('John', 'jhondoe@mail.com', '26');
INSERT INTO users (name, email, phone) VALUES ('Andrea', 'andreadoe@mail.com', '20');
INSERT INTO users (name, email, phone) VALUES ('Anya', 'anyadoe@mail.com', '30');
INSERT INTO users (name, email, phone) VALUES ('Santiago', 'santiagodoe@mail.com', '44');

INSERT INTO adoptions (pet_id, user_id, adoption_Date) VALUES (3, 2, '2024-02-12');