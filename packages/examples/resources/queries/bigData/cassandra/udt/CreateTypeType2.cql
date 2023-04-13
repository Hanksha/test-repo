CREATE TYPE IF NOT EXISTS cassandra_examples.type2 (
    id int,
    data frozen<type1>
);