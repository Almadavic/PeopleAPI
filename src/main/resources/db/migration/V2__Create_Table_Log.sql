CREATE TABLE tb_logs(
 id SERIAL PRIMARY KEY,
 event VARCHAR(255) NOT NULL,
 event_time TIMESTAMP NOT NULL
);