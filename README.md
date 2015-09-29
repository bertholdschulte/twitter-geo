Slim web project to show tweet locations.
Based on spring boot, spring twitter api, google maps api and bootstrap.

A twitter api account and oauth-access is needed and keys to be placed in a file called twitter.properties which is located in classpath.

build
-----

mvn install

deploy
------

docker build -t twitter-geo:0.0.1 .
docker run -d --name twitter-geo -p 8080:8080 twitter-geo:0.0.1

test
----

open browser with: http://localhost:8080/?search

