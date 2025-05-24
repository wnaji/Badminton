-- Create schema if not exists
CREATE SCHEMA IF NOT EXISTS badminton;

-- Create user table
CREATE TABLE IF NOT EXISTS badminton.user (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE
);

-- Create game table
CREATE TABLE IF NOT EXISTS badminton.game (
    id BIGINT PRIMARY KEY,
    player1 BIGINT NOT NULL,
    player2 BIGINT NOT NULL,
    score_player1 INTEGER NOT NULL,
    score_player2 INTEGER NOT NULL,
    winner VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    max_score INTEGER NOT NULL,
    CONSTRAINT fk_game_player1 FOREIGN KEY (player1) REFERENCES badminton.user(id),
    CONSTRAINT fk_game_player2 FOREIGN KEY (player2) REFERENCES badminton.user(id)
);