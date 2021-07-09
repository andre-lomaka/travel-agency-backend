# RestAPIDocs

## Endpoints

* List all trips: `GET /api/trips`
* Get trip by id: `GET /api/trips/{id}`
* List promoted trips: `GET /api/trips/promoted`
* List trips by continent id: `GET /api/trips?continent=[id]`
* List trips by country id: `GET /api/trips?country=[id]`
* List trips filtered by properties: `GET /api/trips?fromCity=[id]&toCity=[id]&bbt=[id]&hotelStars=[number]&vacancies=[number]&departureDate=[date]&returnDate=[date]`
  * `fromCity`: city of departure id
  * `toCity`: city of stay id
  * `bbt`: board basis type id
  * `hotelStars`: hotel standard
  * `vacancies`: number of vacancies
  * `departureDate`: departure date (yyyy-mm-dd)
  * `returnDate`: return date (yyyy-mm-dd)
* Create trip: `POST /api/trips`
* List all continents: `GET /api/continents`
* Get continent by id: `GET /api/continents/{id}`
* List all cities: `GET /api/cities`
* List all airports: `GET /api/airports`
* List all hotels: `GET /api/hotels`
* List all countries: `GET /api/countries`
* Create country: `POST /api/countries`
* List all purchases: `GET /api/purchases`
* Get purchase by id: `GET /api/purchases/{id}`
* Create purchase: `POST /api/purchases`
* Update purchase at a given id: `PUT /api/purchases/{id}`
* Delete purchase at a given id: `DELETE /api/purchases/{id}`
