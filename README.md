# spring-data-demo

Playing a little bit with Hibernate.

## Documentation
The best way to dig in is to read and execute this [StudentDAO](https://github.com/niushapaks/spring-data-demo/blob/main/src/test/java/eu/pakseresht/springdatademo/repository/StudentDAOTest.java) test class.

## Run
### Use docker to run a Postgres Database :
```
docker-compose up
```
### Use Maven Spring-Boot plugin to build and run the application :
It will create the database according to the [schema.sql](https://github.com/niushapaks/spring-data-demo/blob/main/src/main/resources/schema.sql) file and populate it from [data.sql](https://github.com/niushapaks/spring-data-demo/blob/main/src/main/resources/data.sql).
```
mvn spring-boot:run
```
