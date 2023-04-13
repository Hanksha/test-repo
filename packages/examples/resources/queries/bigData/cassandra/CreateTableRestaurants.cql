CREATE TABLE IF NOT EXISTS restaurants.restaurants (
    name text,
    restaurant_id int,
    address frozen<address>,
    borough text,
    cuisine text,
    grades list<frozen<grade>>,
    PRIMARY KEY (name, restaurant_id)
)