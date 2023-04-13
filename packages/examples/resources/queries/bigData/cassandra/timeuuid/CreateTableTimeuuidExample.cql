CREATE TABLE IF NOT EXISTS cassandra_examples.timeuuid_example (
    id int PRIMARY KEY,
    timeuuid timeuuid,
    timeuuid2 list<timeuuid>,
    timeuuid3 map<int, timeuuid>,
    timeuuid4 set<timeuuid>
)