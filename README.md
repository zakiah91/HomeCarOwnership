# HomeCarOwnership (Springboot)
<i>Summary</i>: This app is use to view owner's profile and register the home-car ownership. <br/>
For example, if the user give the owner's id, the server will reponse the owner's profile that consist of : owner's detail, house number and car details. <br/>
The user also can register new house owner and car. <br/>

## Assumption
1. An owner will have at least one house. <br/>
2. A house can have no car or one car or multiple cars. <br/>

## ER Diagram
![image](https://github.com/user-attachments/assets/6bd57aeb-9920-42d0-86a4-cbffabe7eb83)


## Code structure
The code structure is as below : <br/>
![image](https://github.com/user-attachments/assets/d1b8ccad-89a6-4cb7-81fc-01164d2fb006)


## How to run
In order to run this, the following steps are needed.<br/>
1)Create a database call HomeCarOwnership<br/>
2)Modify the content of HomeCarOwnership\src\main\resources\application.properties so that it refers to your db
3)This springboot will create tables as the picture below : <br/>
![image](https://github.com/user-attachments/assets/956b750c-c5f3-49cf-aab7-38a27efc5eb9)

4)Using eclipse, Right Click on your pom.xml -> Run As -> Maven Install <br/>
5)A .war file will be generated under /target folder <br/>
6)Start the application by putting the .war file in your server or use command line "java -jar <YOUR_WAR_NAME>.war" <br/>

## App operations
1. When you run the app, first it will initialize the tables in the db with the following data:
   ![image](https://github.com/user-attachments/assets/39b68d0a-e503-4460-8c6f-257a191c797c)
2. After you can start to test it using Swagger UI. <br/>
In general there are 4 REST API created. they are: <br/>
a) <i>/HomeCarOwnership/profile </i>: return user's profile in JSON when given a user id. If it encounter error during the checking of the POST data, it will return ERR <br/>
b) <i>/HomeCarOwnership/registerOwner </i>: add owner's details to the Owner table and House table. If it encounter error during the checking of the POST data, it will return ERR. Else, it will return OK <br/>
c) <i>/HomeCarOwnership/registerCars </i> : add car details to the Car table based on the given owner's id and house id. If it encounter error during the checking of the POST data, it will return ERR.Else, it will return OK <br/>
d) <i>/HomeCarOwnership </i>: A test just to know server has run <br/>

## How to test using Swagger UI
1) After you have run the .war file, go to http://localhost:8080/swagger-ui/index.html
2) Insert the following in the request body: <br/>
     a) <i>POST /HomeCarOwnership/profile  :</i> <br/>
          &emsp;&emsp;i)For <b>OK</b> case : <b>{"oid" : "o01"}</b>  OR  <b>{"oid": "o02"}</b>  --> It will response user profile in Json <br/>
          &emsp;&emsp;ii)For <b>NG</b> case : other than above, or <b>{ "uid" : "u01"}</b>  --> It will response ERR <br/>

     b) <i>POST /HomeCarOwnership/registerOwner : </i><br/>
          &emsp;&emsp;i)For <b>OK</b> case : example, <b>{"oid":"o03","age":50,"name":"owner3","housenumber":"A10-03"}</b>   --> It will response OK, this indicate data has been inserted to db <br/>
          &emsp;&emsp;ii)For <b>NG</b> case : example, the number of JSON keys is not enough  <b>{"age":50,"name":"owner3","housenumber":"A10-03"}</b>  -->  It will response ERR <br/>

     c) <i>POST /HomeCarOwnership/registerCars : </i><br/>
          &emsp;&emsp;i)For <b>OK</b> case : example, <b>{"oid":"o03","housenumber":"A10-03","cars":[{"platenumber":"ABC 9078","colour":"green","type":"toyota"},{"platenumber":"UAC 9040","colour":"black","type":"wira"}]}</b>   --> It will response OK, this indicate data has been inserted to db <br/>
          &emsp;&emsp;ii)For <b>NG</b> case : example, house number is not correct <b>{"oid":"o03","housenumber":"A10-02","cars":[{"platenumber":"ABC 9078","colour":"green","type":"toyota"},{"platenumber":"UAC 9040","colour":"black","type":"wira"}]}</b> -->  It will response ERR <br/>

     d) <i>GET /HomeCarOwnership : </i><br/>
          &emsp;&emsp;i)For <b>OK</b> case : It will response "Server has started" <br/>






