CREATE KEYSPACE IF NOT EXISTS advisor_ks WITH replication = {'class':'SimpleStrategy','replication_factor':'1'};

CREATE TABLE IF NOT EXISTS advisor_ks.user
(
    id             UUID,
    email          VARCHAR,
    google_user_id VARCHAR,
    PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS user_google_user_id_idx ON advisor_ks.user (google_user_id);
CREATE INDEX IF NOT EXISTS user_email_idx ON advisor_ks.user (email);

CREATE TABLE IF NOT EXISTS advisor_ks.advice
(
    id                UUID,
    symbol            VARCHAR,
    date              TIMESTAMP,
    flag_buy          BOOLEAN,
    symbol_value      DOUBLE,
    profit_estimated  DOUBLE,
    signals_triggered VARCHAR,
    PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS advice_symbol_idx ON advisor_ks.advice (symbol);
CREATE INDEX IF NOT EXISTS advice_date_idx ON advisor_ks.advice (date);
CREATE INDEX IF NOT EXISTS advice_flag_buy_idx ON advisor_ks.advice (flag_buy);