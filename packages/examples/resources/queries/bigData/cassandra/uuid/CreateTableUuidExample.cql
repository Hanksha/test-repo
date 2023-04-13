CREATE TABLE IF NOT EXISTS cassandra_examples.uuid_example (
    id int PRIMARY KEY,
    uuid uuid,
    uuid2 list<uuid>,
    uuid3 map<int, uuid>,
    uuid4 set<uuid>
)