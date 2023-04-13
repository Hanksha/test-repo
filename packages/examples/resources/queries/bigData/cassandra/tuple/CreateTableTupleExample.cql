CREATE TABLE IF NOT EXISTS cassandra_examples.tuple_example (
    id int PRIMARY KEY,
    tuple frozen<tuple<int, text>>
)