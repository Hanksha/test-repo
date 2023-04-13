CREATE TABLE IF NOT EXISTS cassandra_examples.udt_example (
    id int PRIMARY KEY,
    nested_udt type2,
    udt type1
)