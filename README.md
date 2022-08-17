
# REST API for an Covid-19 Application

* We have developed this REST API for an Covid-19 Application. This API performs all the fundamental CRUD operations of any Covid-19 Application platform with user validation at every step.
* This project is developed by team of 6 Back-end Developers during project week in Masai School. 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger

## Modules

* Login, Logout Module
* User Module
* Admin Module

## Features

* User and Admin authentication & validation with session uuid having.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid session token can add/update/delete driver or customer from main database
    * Admin can access the details of different Appointment, Member ,Vaccine Center ,Vaccine Inventory and Vaccine Ragistration.
* User Features:
    * A user can register himself or herself on the platform.
    * He/She can check the vaccine centres and vaccine availabilty.
    * If vaccine is available, can book an appointment slot.
    * After booking an appointment, he will get appointment details for the vaccine dose.    


## Contributors

* [@Micheal George](https://github.com/Micheal-George)
* [@Shivam Maheshwari](https://github.com/shivamgarg796)
* [@Avinash Kumar](https://github.com/avinash-here)
* [@Gaurav Singh](https://github.com/GauravSinghh)
* [@Md Farhan Nawaz](https://github.com/nvFARHAN)
* [@Moh Shahrukh Khan](https://github.com/MohShahrukhKhan)



## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/nvFARHAN/cowin.gov.in/blob/master/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/cowin;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint

`https://localhost:8080/`

`http://localhost:8080/swagger-ui/`


## API Module Endpoints

### Login Module

* `POST //api/adminlogin` : Admin can login with mobile number and password provided at the time of registation
<!--
### User Module


* `POST /customer/login` : Logging in customer with valid mobile number & password
* `GET /customer/availablecabs` : Getting the list of all the available cabs
* `GET /customers/cabs` : Getting All the cabs
* `GET /customers/checkhistory` : Getting the history of completed tr
* `PUT /customer/update/{mobile}` : Updates customer details based on mobile number
* `PATCH /customer/updatepassword/{mobile}` : Updates customer's password based on the given mobile number
* `POST /customer/booktrip` : Customer can book a cab
* `POST /customer/updatetrip` : Customer can modify or update the trip
* `POST /customer/logout` : Logging out customer based on session token
* `DELETE /customer/delete` : Deletes logged in user 
* `DELETE /customer/complete/{tripid}` : Completed the trip with the given tripid 
* `DELETE /customer/canceltrip` : Cancel the trip with the given tripid   


### Admin Module

* `POST /admin/register` : Register a new admin with proper data validation and admin session
* `POST /admin/login` : Admin can login with mobile number and password provided at the time of registation
* `GET /admin/logout` : Logging out admin based on session token
* `GET /admin/listoftripsbycustomer` : Get list of trips of by a customer id
* `GET /admin/listoftrips` : Get list of trips of all the trips
* `GET /admin/listocustomers` : Get list of all the customers
* `GET /admin/listodrivers` : Get list of all the drivers
* `PUT /admin/update/{username}` : Updates admin detaisl by passed user name
* `DELETE /admin/delete` : Deletes the admin with passed id   -->


### Sample API Response for Customer Login

`POST   localhost:80211/login/customer`

* Request Body

```
    {
        "mobileId": "85022457580",
        "password": "Clickme@007"
    }
```

* Response

```
    {
        "sessionId": 3,
        "token": "0ad57094",
        "userId": 9,
        "userType": "customer",
        "sessionStartTime": "2022-06-10T10:48:20.0109626",
        "sessionEndTime": "2022-06-10T11:48:20.0109626"
    }
```
### E-R Diagram Of Covid-19 Application
---
<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/WhatsApp%20Image%202022-08-10%20at%209.06.41%20PM.jpeg?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Swagger UI

---

<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/WhatsApp%20Image%202022-08-17%20at%2010.21.37%20AM.jpeg?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Login Controller

---

<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/Login.png?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Admin Controller

---

<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/WhatsApp%20Image%202022-08-17%20at%2010.24.11%20AM.jpeg?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### User Controller

---

<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/User.png?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Model Controller

---

<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/mODELS.png?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

<img src="https://github.com/masai-course/shivam_fw17_1124/blob/master/unit-1/evaluation/Thank-you-word-cloud.jpg?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---





