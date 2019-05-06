# Trackify
This is Trackify - A place for keeping an eye on the products available in your household.
We offer a fast and responsive way of keeping track of your household goods with a constantly updating database of products.
In addition we show simple statistics of those products for a brief overview of expenditures.
There are similar services available but they are not directly targeted towards Estonia's market.
## App
The product uses AWS with RDS and S3. As well as for authentification we use a simple JWT based system.
The website runs on: ec2-3-92-62-1.compute-1.amazonaws.com
## Bugs
* none so far...
## Dev on local machine
* server:
```
    ./gradlew bootRun
    ec2-3-92-62-1.compute-1.amazonaws.com:9000
```
* client:
```
    yarn install
    yarn serve
    localhost:8080
```
* demo
```
username: user
password: password
```
## Team
The dev team consists of 3 people:
Risto Leesmäe
Peter-Sten Annus
Helina Kruuk
