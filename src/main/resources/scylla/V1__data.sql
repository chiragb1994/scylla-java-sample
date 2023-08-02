CREATE KEYSPACE scylla_sample
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE scylla_sample.user_data
(
    user_id text,
    data    map<text, text>,
    PRIMARY KEY (user_id)
);
