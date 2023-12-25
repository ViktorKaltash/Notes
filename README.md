![Notes](https://github.com/ViktorKaltash/Notes/assets/150521039/1d929443-040a-4fec-9ca7-c9a4afddc38b)

Notes is a program that stores notes from registered users in
MySQL database. The project is still under development and will be updated.

## Used libraries and IDE
This program used IDE Intellij IDEA, SceneBuilder, libraries such as
JavaFX, MySQL-Connector/J, h2 database java. Programm assembled with Maven.

Required libraries to use:

* JavaFX - https://openjfx.io
* MySQL-Connector/J - https://dev.mysql.com/downloads/connector/j/
* H2 database java - https://h2database.github.io/html/main.html

## Installing a database in MySQL

First of all, you need to connect the MySQL database. To do this, in the project folder
"src/main/resources/MySQL" contains files for initialization. It is necessary to run the file "Init DB.sql"
in MySQL.

## Using the program

In order to use Notes, you need to register a user; for this we register him in our database. Enter the user’s email and password on the initial screen and press the Register button;

![image](https://github.com/ViktorKaltash/Notes/assets/150521039/8b158882-fe7b-41fa-a2c6-9384b0055918)


Then we fill in nickname to the database

![image](https://github.com/ViktorKaltash/Notes/assets/150521039/c7a06e58-4149-43e0-b956-ce65c81a266b)


After these steps, our account is registered in the database

![image](https://github.com/ViktorKaltash/Notes/assets/150521039/1e6d1dc2-4bd8-4958-b5ac-faa393e71b8b)


Now log in to the system and go to our program. Let's create a new user note

![image](https://github.com/ViktorKaltash/Notes/assets/150521039/a1479db8-6176-453b-9965-08a451ffb2ca)


![image](https://github.com/ViktorKaltash/Notes/assets/150521039/440740f2-1566-459c-8b22-f158ad103774)



As you can see, our note has been added to our workspace and is now stored in the database. If we decide to re-login to the program on the same account, our note will be uploaded to us.

![image](https://github.com/ViktorKaltash/Notes/assets/150521039/ad56964b-a6cd-4afd-9b1b-3d953cfd2197)

![image](https://github.com/ViktorKaltash/Notes/assets/150521039/11e9bc66-99a5-4aec-bd50-1752d2314fac)

## [Work In Progress]

* New design for the program
* Ability to be offline and not interact with the database in any way
* Fix bugs
* Saving password in hash style
