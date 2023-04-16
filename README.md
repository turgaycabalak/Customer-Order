# Customer - Order

A Spring boot Api sample for CRUD operations and relations between tables/objects. We have one-to-many relation between Customer and Order.

## Installation

Use the version 17 or greater [JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) to run the project.
Postman collection json exists in project folder for you to do your API tests easily.

You can import it into your postman workspace. Spring Boot version is ```3.0.5```

### Setting postgres up on docker
It is possible to use docker for db integration, ```postgres```. You need to run ```docker-compose up -d``` command in the main path of project where docker-compose.yml file exists. Two containers will be runing, postgres and ui.

UI url for postgres is ```http://localhost:5050/browser/```

- Postgres UI Password is ```qwerty```

- After you successfully login to Postgres UI, you should now 'add a new server'. For this click to ```Add New Server``` in the middle of page.

- In the ```General``` tab enter the ```Name``` as **postgresdb** or whatever you want.

- In the ```Connection``` tab enter ```Hostname/address``` as **postgres** just like defined in the compose file. And set the password ```qwerty``` in the same tab.

- Now you can create a new *Database* named as ```backendchallenge``` which is defined in the **application.properties** file in project.
