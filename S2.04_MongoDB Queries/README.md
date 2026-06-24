# S2.04_MongoDB Queries — Query Exercises

![MongoDB](https://img.shields.io/badge/MongoDB-5-47A248?logo=mongodb)

## Description

33 MongoDB query exercises on a `restaurants` collection (sample dataset with 3,771 documents from NYC restaurant inspections). Demonstrates the full range of MongoDB CRUD read operations: filtering, projection, comparison operators, regular expressions, array queries, sorting, and aggregation pipeline stages.

## What I Learned

- `find()` with projection (include/exclude fields)
- Filtering operators: `$gt`, `$lt`, `$gte`, `$lte`, `$ne`, `$in`, `$nin`, `$mod`, `$exists`, `$type`
- Logical operators: `$and`, `$or`, `$nor`, `$not`
- Array queries: `$elemMatch` for matching multiple conditions on array elements
- Positional array access (`grades.1.date`) for querying specific array indices
- Regular expressions with `$regex` for string pattern matching
- Pagination: `limit()`, `skip()`
- Sorting: `sort()` with ascending/descending on single and multiple fields
- `count()` without filters
- Date queries with `ISODate`

## Files

| File | Description |
|------|-------------|
| [S2.04-MongoDB_Queries.js](S2.04-MongoDB_Queries.js) | 33 query statements on the restaurants collection |
| [restaurants.json](restaurants.json) | Sample dataset with 3,771 restaurant documents (grades, address, borough, cuisine) |

### Query Examples

```js
// Restaurants with scores between 80 and 100 (using $elemMatch)
db.restaurants.find({"grades":{$elemMatch:{"score":{"$gt":80,"$lt":100}}}}).pretty();

// Restaurants sorted by cuisine ascending, borough descending
db.restaurants.find().sort({"cuisine":1,"borough":-1}).pretty();

// Restaurants whose name ends with "ces"
db.restaurants.find({"name":{"$regex":/ces$/}}, {"restaurant_id":1,"name":1,"_id":-1}).pretty();
```

### Dataset Fields

- `address` — building, coord (longitude/latitude), street, zipcode
- `borough` — Bronx, Brooklyn, Manhattan, Queens, Staten Island
- `cuisine` — type of cuisine
- `grades` — array of grade objects (date, grade, score)
- `name` — restaurant name
- `restaurant_id` — unique identifier

## How to Run

```bash
# Import sample data first
mongoimport --db test --collection restaurants --file restaurants.json

# Run queries
mongosh < S2.04-MongoDB_Queries.js
```

## Built With

- MongoDB 5+
- mongosh (MongoDB Shell)
