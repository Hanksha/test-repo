CREATE TABLE IF NOT EXISTS blob_example (
    id int PRIMARY KEY,
    blob blob,
    blob2 list<blob>,
    blob3 map<int, blob>,
    blob4 set<blob>
)