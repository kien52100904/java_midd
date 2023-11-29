# java_midd

## Software development principles, patterns and practices:
#### 1. Principles
I apply SOLID principles to ensure flexibility and maintainability of the application. This includes breaking down the application into separate classes with specific functions, ensuring independence between modules, and allowing for scalability.

#### 2. Patterns
I use the Model-View-Controller (MVC) design pattern to separate the classes and reduce dependencies between them. The Model is responsible for data and data processing logic, the View displays the data to the user, and the Controller controls the execution process.

#### 3. Practices
I have used the Agile methodology to develop applications. This allows us to optimize the development process, quickly respond to changing requirements, and work effectively with the development team.

## Entity-relationship diagram

<img src="[https://i.pinimg.com/564x/cc/6a/a1/cc6aa16a5af6ddbca84457225cabe6b4.jpg](https://i.imgur.com/8rRFY3R.png)">

## The code structure
    .
    ├── ...
    ├── src                    
    │   ├── main              
    │   │   ├── java              
    │   │   │   ├── com.middterm.project            
    │   │   │   │   ├── config
    │   │   │   │   ├── controller
    │   │   │   │   ├── model
    │   │   │   │   ├── repository
    │   │   │   │   ├── service
    │   │   │   │   └── ...
    │   │   │   └── ... 
    │   │   ├── resources
    │   │   │   ├── templates
    │   │   │   ├── application.properties
    │   │   │   └── ...
    │   │   └── ..
    │   └── ..
    └── ... 

    
The "model" directory contains classes representing data entities, where these objects are defined and processed.
The "repository" directory contains interfaces or classes for accessing and interacting with the database. 

The "service" directory contains classes that handle the business logic of the application. Service classes link to the Repository class to interact with the database, or link to other APIs to obtain information. After obtaining information, Service classes process the data and return results to the Controller class.

The "controller" directory contains controller classes in the application, where user requests are processed. These classes specify the URL of the request and call the corresponding methods in the Service classes to process the request.

## All required steps in order to get the application run on a local computer

Database: Create a new database whose name is middterm, then import file middterm_book, middterm_cart_item and middterm_user into it.
Import project into Eclipse/IntelliJ, then go to the file application and start running it (port is 8080, can change if necessary)
Go to browser and type 'http://localhost:8080' and start experience the app.

## Login information available for app

Admin:
  email: phamhoangtrungkien2411@gmail.com
  password: kien@2003
User:
  email: user1@gmail.com
  password: 123456
  
  
