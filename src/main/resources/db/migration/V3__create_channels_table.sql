CREATE TABLE channels
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255)                      NOT NULL,
    description VARCHAR(255)                      NOT NULL,
    language    VARCHAR(10)                       NOT NULL,
    category_id BIGINT REFERENCES categories (id) NOT NULL,
    avatar      TEXT,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    author_id   BIGINT REFERENCES users (id)      NOT NULL
);