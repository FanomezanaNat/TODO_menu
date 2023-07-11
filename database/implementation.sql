\c postgres;

DROP DATABASE IF EXISTS todo;

CREATE DATABASE todo;

\c todo;

DROP TABLE todo IF EXISTS;

CREATE TABLE todo (
    id SERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description TEXT,
    deadline TIMESTAMP,
    priority INT DEFAULT 5 CHECK (priority >= 0 and priority <= 10),
    done BOOLEAN DEFAULT 'f'
);

INSERT INTO todo 
(title, description, deadline, priority, done)
values
('First Tache', 'jchqkckqsckjqskjckjkjqs','2023-07-15', 5, 'f' ),
('Second Tache', 'jchqkckqsckjqskjckjkjqs','2023-07-16', 6, 't' ),
('Thrith Tache', 'jchqkckqsckjqskjckjkjqs','2023-07-25', 7, 't' ),
('Fourth Tache', 'jchqkckqsckjqskjckjkjqs','2023-07-05', 10, 'f' );