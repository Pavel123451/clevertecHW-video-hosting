CREATE TABLE subscriptions
(
    user_id       BIGINT REFERENCES users (id),
    channel_id    BIGINT REFERENCES channels (id),
    subscribed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, channel_id)
);