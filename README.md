# HomeCarOwnership (Springboot)
## How to run
In order to run this, the following steps are needed.<br/>
1)Create a database call HomeCarOwnership<br/>
2)Modify the content of HomeCarOwnership\src\main\resources\application.properties so that it refers to your db
3)This springboot will create tables as the picture below : <br/>
![image](https://github.com/user-attachments/assets/956b750c-c5f3-49cf-aab7-38a27efc5eb9)

4)Using eclipse, Right Click on your pom.xml -> Run As -> Maven Install <br/>
5)A .war file will be generated under /target folder <br/>
6)Start the application by putting the .war file in your server or use command line "java -jar <YOUR_WAR_NAME>.war" <br/>

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






