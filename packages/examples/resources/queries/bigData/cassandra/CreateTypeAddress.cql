CREATE TYPE IF NOT EXISTS restaurants.address (
    building text,
    coord frozen<tuple<decimal, decimal>>,
    street text,
    zipcode int
);