# locations-service
## Description  
Service for collecting data about places. There are 4 types of places in this app:  
 * food place - for places like restaurants, bars and anything related to food. 
 * stay place - for hotels, motels, shelters, bnbs, etc.
 * area place - for places like parks, national parks, states, etc.
 * point location - for point on map without address like mountain, viewpoint, etc. Point Location can be a part of a Area Place.   

Evey place have description, images, type, comments and place-specific attributes:
 * food place: 
    * food rate
    * pricing rate
    * address
 * stay place:
    * peace rate
    * location rate
    * pricing rate
    * if pets are allowed
    * if has car parking
    * comfort rate
    * standard rate (standard of the rooms/services)
 * area place:
    * area (value of the area of the place in some units)
    * unit (units in which the area is expressed: m^2, acres, hectares, etc.)
    * if the admission is paid
    * list of Point Locations
    * spot (a spot of this place, can be a child spot of a city or some other area places)
 * point location:
    * latitude
    * longitude
    * parent Area Place

## Build  
Application uses Gradle. The default task is `bootRun` and the default Spring Profile is `dev`.  
To run `dev` version simply use one of the following commands:  
  `gradlew`  
  `gradlew bootRun`  
  `gradlew bootRun -Pdev`  
  `gradlew -Pdev`  
To build the app use the `build` task:  
  `gradlew build`  
  `gradlew build -Pdev`  
If you want to add other profiles like `prod`, add `application-prod.yml` to `resources` directory and use the `-Pprod` flag.

There is a `PostgreSQL` DB needed to run this application. The `dev` version uses ***locations_service*** db with the same user and password.  
The app uses `Flyway` migration tool to create DB schema and tables at app start, so there is no need to configure it. Just run the app, tables will be create automaticly.
