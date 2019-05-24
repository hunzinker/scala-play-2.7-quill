# Example Play 2.7 Application With Quill 

Example of how to use `quill-jdbc` with play 2.7.

Setup:

- H2 In-Memory DB
- Play Evolutions
- Quill DB Dependency Injection

This example is 100% contrived.

## Install

### Dependencies

Install [sbt](https://www.scala-sbt.org/1.x/docs/Setup.html).

### Test

* `sbt test`

### Run the app locally

* `sbt run`

Apply the migrations: [http://localhost:9000](http://localhost:9000)

## API

The API accepts `POST` request data in a `GET` query string for simplicity.

```
POST /visitors?timestamp={millis_since_epoch}&user={user_id}&event={click|impression}
GET /visitors?timestamp={millis_since_epoch}
```

`GET` requests return the below for the given hour in GMT:
```
unique_visitors,{number_of_unique_visitor_ids}
clicks,{number_of_clicks}
impressions,{number_of_impressions}
```

Example usage:

```
curl -s -X GET 'localhost:9000/visitors?timestamp=1558140664000'
unique_visitors,0
clicks,0
impressions,0
```

`POST` data:
```
curl -s -X POST 'localhost:9000/visitors?timestamp=1558140664000&user=16&event=impression'
curl -s -X POST 'localhost:9000/visitors?timestamp=1558140664000&user=15&event=impression'
curl -s -X POST 'localhost:9000/visitors?timestamp=1558140664000&user=14&event=click'
```

```
curl -s -X GET 'localhost:9000/visitors?timestamp=1558140664000'
unique_visitors,3
clicks,1
impressions,2
```
