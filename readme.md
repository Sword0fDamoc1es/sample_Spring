## README
-------------
this is a document showing the design of this sample project.  
### target:
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.
 
•	Solve using Spring Boot.  
•	Create a RESTful endpoint.   
•	Make up a data set to best demonstrate your solution.  
•	Check solution into GitHub.  

---------------
## Main

### repository layer:
Both CustomerReposity.java and TransactionRepository.java are repository layer. Since database varies from different environment, I create these files, but not connecting to a real database. The whole project works on a fake DB, which i generated in service layer.  

### service layer:
 CustomerService.java is a service layer file. It contains methods calculating reward of one transaction and the total reward of a List of Transactions.  
 As mentioned above, it contains a Map structure as a fake DB. Its K-V pair is Customer - List < Trancation >.
 It also contains a backdoor method, setUpFixedFakeData, to setUp fixed fake data through API request.

### entity
We can also call these files pojo, since we are not connected to real DB. We create Customer class and Transaction class. Both files use Lombok to auto-generate getter and setter.
For Customer, we select Long as data type for id and rewards, preventing overflow. Each Customer has a list of transactions, which meets the demands.  
For Transaction, we also select Long as data type for id and amount.

### controller
This is the controller layer. It contains CustomerController.java as controller from customer requests. Currently it has two getter methods: 
1. getRewards() with a pathvariable as customerId would fetch the rewards of a specific client.
2. initializeFixedFakeDB() would call a backdoor method to create a fixed, non-random DB, helping us to adjust value when doing basic API testing.
3. this controller layer validates input, it contribute with response files and exception file to solve exceptions and return formatted response.

### exception
This is the folder for all exceptions, currently we have numericFormatExceptions, which would be the case when invalid customerId as input. It would response a format responseEnityty which we designed.

### response
This is the folder for different response. Solving different API request needs different formatted response and all these formats lay here. Currently we only have CustomerResponse, to return a response according to the result of operations from Service Layer.

### util
This is a folder for functional operations. It currently contains create randomName, date. It is convenient to use function from this folder in all parts of the project.

-----------------
## Test

### fakeDataBaseTest
this is the test folder for the fake DB we created in service layer. Since we already created classes in Entity folder using Lombok, we don't need to check the initialization of seperate parameters, Lombok has done the job for us.   
Tests mainly focus on if the fake DB is contructed successfully and fake data can be insert succussfully.

### customerServiceTest
this is the test folder for customerService. Here we insert random customer and correspondingly random transactions. We need to test:
1. The reward calculation method for each transaction works fine.
2. The reward calculation method for total transaction works fine.

-----------------
## application.property
This file currently only contains the key of backdoor.

-----------------
## TODO
1. calculating rewards is not efficient right now. We can use rewards parameter in Customer to cache previous rewards and return rewards directly if no updates to previous transactions. This avoids recalculations.
2. More Validations are needed for further development. 
3. Time perious calculation is needed if includes demands like calculation for rewards of a customer in a period of time.
4. Transaction service layer is needed if includes demands like operations work independently on Transaction fiedls.
5. AOP can be added for logging and mocking.
