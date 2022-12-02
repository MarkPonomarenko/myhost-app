# MyHost Service
Web store which provides possibility to rent a dedicated server for users

---

### Entities
* User(Personal, Admin)
* Server
* Provider
* Transaction

---

### Used libs and techs
* Spring Boot
* HTML, CSS, Thymeleaf, JS( + IMask & Chart.js)
* LiqPay API (sandbox mode because I don't own any company (that means that cards isn't charged))
* Database: MySql; DBMS: MySql Workbench

---

### Start Up
* Launch ElasticSearch ([elasticsearch 7.6.2](https://www.elastic.co/downloads/past-releases/elasticsearch-7-6-2) used in development)
* Launch App (init users in data.sql)
* GoTo http://localhost:8080

---

### Default Users
* Admin (email - admin@mail.com, password - rootroot)
* Personal (email - test@mail.com, password - password)
