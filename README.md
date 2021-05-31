# User Managment API documentation

## 1. Overview
All apis are Restful 

Passwords are not ecripted


## Following are the list of apis

## 1. API for Registration
here id,username and password are stored and api_token is stored as null.
### Example Request
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "ankush",
    "password": "ankush@123"
}'  http://localhost:8080/users/register

### Description of fields:

| id | api_token                            | password   | username  |
|----|--------------------------------------|------------|-----------|
|  3 | NULL                                 | 1324234    | ankfdsait |
|  6 | NULL                                 | 1234       | ankit11   |
|  7 | NULL                                 | ankush@123 | ankush    |

### Example Response
"REGISTERED_SUCCESSFULLY"

If already registered then response is 
### Example Response
"USER_ALREADY_EXISTS"

## 2. API for Login
Here if username and password are there token is generated and saved

### Example Request
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "ankit11",
    "password": "1234"
}'  http://localhost:8080/users/login

### Description of fields:
| id | api_token                            | password   | username  |
|----|--------------------------------------|------------|-----------|
|  3 | NULL                                 | 1324234    | ankfdsait |
|  6 | 0ab65092-367b-487e-a967-2186f41b79ba                                 | 1234       | ankit11   |
|  7 | NULL                                 | ankush@123 | ankush    |

### Example Response
{"responsestatus":"LOGGEDIN_SUCCESSFULLY","token":"0ab65092-367b-487e-a967-2186f41b79ba"}

if username and password are incorrect

### Example Request
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "ankit11",
    "password": "12"
}'  http://localhost:8080/users/login

### Example Response
{"responsestatus":"LOGIN_FAILED","token":null}

## 3. API for Homepage
Here token is passed and validated if token is valid user details are returned

### Example Request
curl -H "Content-Type: application/json" -X POST -d '{
    "api_token": "0ab65092-367b-487e-a967-2186f41b79ba" 
}'  http://localhost:8080/users/Home

### Example Response
{"id":6,"username":"ankit11","password":"1234"}
