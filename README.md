# IBM-currency-exchange-backend
IBM technical task - currency exchange web application server side. 

Frontend here: https://github.com/TMikna/IBM-currency-exchange-frontend



As I was chasing the task deadline, the application is not yet perfect, there are plenty of parts which could be improved: exceptions and errors handling, commenting, code structure etc., especially in front-end. 
I've used Spring Boot for backend and HTML + CSS + js with a drop of bootstrap for front-end. I also wrote 2 simple unit tests. I have tried to upload a database to IBM cloud, however I bumped into a configuration problem I failed to solve.
Currency rates are taken from Bank of Lithuania (Lietuvos bankas) web services (https://www.lb.lt/webservices/FxRates/)

Basic backend working principles:
When backend gets a request for currecy rate, it checks for it in database. If required retes exists in db, it returns them, otherwise makes request to Lietuvos Banks API, gets newest rates of all currencies, stores them in DB and return required rate to frontend. At the same time backend if accepting POST request about user activity and logs it in another DB.
