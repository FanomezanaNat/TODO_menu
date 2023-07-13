-- You must implement this database in your postgres
-- before begin:

\c postgres;

DROP DATABASE IF EXISTS todolist;

CREATE DATABASE todolist;

\c todolist;

DROP TABLE todo IF EXISTS;

CREATE TABLE todo (
    id SERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description TEXT,
    deadline TIMESTAMP CHECK (deadline >= CURRENT_TIMESTAMP),
    priority INT DEFAULT 5 CHECK (priority >= 0 and priority <= 10),
    done BOOLEAN DEFAULT 'f'
);


-- There are some insert just for test:
INSERT INTO todo 
(title, description, deadline, priority, done)
values
('First Tache', 'jchqkckqsckjqskjckjkjqs','2023-07-30', 5, 'f' ),
('Second Tache', 'jchqkckqsckjqskjckjkjqs','2023-08-30', 6, 't' ),
('Thrith Tache', 'jchqkckqsckjqskjckjkjqs','2023-07-30', 7, 't' ),
('Fourth Tache', 'jchqkckqsckjqskjckjkjqs','2023-10-05', 10, 'f' );