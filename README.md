# Entity Manager
## Set up
To prepare app for usage you need
* Create database
* Prepare db.properties
```
cp src/main/resources/db.properties.dist src/main/resources/db.properties
```
Insert your values
* Import db structure from
```
src/main/resources/db/migration/test.sql
```
* Now you can launch app
```
 gradle run
```
Or run method *main* from class *ConsoleApplication*
## Available commands
Acceptable command's format is:
```
'action' 'entity' [params]
```
Available commands are: *create|remove|show*

Available entities are: *customer|product|purchase*
# Tests
To run tests your need to provide *src/test/resources/META-INF/persistence.xml*