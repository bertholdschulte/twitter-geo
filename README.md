Slim web project to show tweet locations.
Based on spring boot, spring twitter api, google maps api and bootstrap.

A twitter api account and oauth-access is needed and keys have to be provided in the command-line statement.

build
-----

mvn install

run
---
java -jar twitter-geo-0.0.1-SNAPSHOT.jar --consumerKey=<twitter consumerKey> --consumerSecret=<twitter consumerSecret> --accessToken=<twitter accessToken> --accessTokenSecret=<twitter accessTokenSecret> 


deploy via docker
-----------------

docker build -t twitter-geo:0.0.1 .
docker run -d --name twitter-geo -p 8080:8080 twitter-geo:0.0.1 --consumerKey=<twitter consumerKey> --consumerSecret=<twitter consumerSecret> --accessToken=<twitter accessToken> --accessTokenSecret=<twitter accessTokenSecret> 


test
----

open browser with: http://localhost:8080/?search

