# MySpotifyReview


docker-compose up
It launches the angular web server, the mysql server, the  Spring Boot server and an instance of PHPMyAdmin to monitor the databases entries.

Connect to the angular Web Interface on http://localhost:8081/

Create an account a&nd LOgin to upload the historic files you'll have gathered from  https://www.spotify.com/us/account/privacy/

Upload your files. Files are being parsed bu the Spring Boot server and information is stored inside the MySQL Server
![image](https://github.com/adubois-dev/Statify/assets/126862970/95361f26-5c0a-42ba-b92b-c380cf4ffc22)

One done you have the two following pages restoring parsed informations :
- http://localhost:8081/assessment
- ![image](https://github.com/adubois-dev/Statify/assets/126862970/c579a981-bcc3-43d4-b36c-4ccc5f83226f)
- And http://localhost:8081/top50
- ![image](https://github.com/adubois-dev/Statify/assets/126862970/8d0d1ab7-4a86-4ad9-a523-04673fad6a37)

  All the data stored in the database can be monitored followed and used with the PHPMyAdmin Interface available at http://localhost:8084/
