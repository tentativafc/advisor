CREATE KEYSPACE IF NOT EXISTS advisor_ks WITH replication = {'class':'SimpleStrategy','replication_factor':'1'};

CREATE TABLE IF NOT EXISTS advisor_ks.user
(
    id             VARCHAR,
    access_token   VARCHAR,
    email          VARCHAR,
    google_user_id VARCHAR,
    PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS user_google_user_id_idx ON advisor_ks.user (google_user_id);
CREATE INDEX IF NOT EXISTS user_email_idx ON advisor_ks.user (email);

