CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password)
VALUES ('john_doe', '$2a$12$51YEG248w9FKsBU9QXuCueWki.JuuXVToC6KoQbnoBpoJZIiBeA2q');#test123
INSERT INTO users (username, password)
VALUES ('jane_doe', '$2a$12$51YEG248w9FKsBU9QXuCueWki.JuuXVToC6KoQbnoBpoJZIiBeA2q');
