# Inventory Management System

This project took advantage of various technologies such as Google Cloud Platform (GCP), MySQL database, SonarQube, Nexus, Java, JUnit and Mockito to create an inventory management system. The project used Java as its code base, and was packaged using a Continuous Integration (CI) pipeline tool, Jenkins.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

1. Clone/fork the repo unto your computer, alternatively you could judt download the project and open it in your IDE.
2. Open the project as a Maven projectin your IDE.
3. Change the hardcoded MySQL links `jdbc:mysql://35.240.38.154:3306/` in the ims.java, CustomerDaoMysql.java, OrderDaoMysql.java and ProductDaoMysql.java to the link of your own MySQL server.

### Prerequisites

In order for this project to run, the computer needs to have java installed and the project needs to be built using maven. 

A MySQL instance has to be set up and then linked as stated in number 3 above.

Git has to also be installed unto the computer so that the project can be cloned or forked.

## Testing

The tests were written using Mockito and Junit. These test covered a good majority of the program that could be tested this inclused the controllers, the services and the domain classes. 

## Deployment

After the project has been forked and opened in the IDE. Go to the project folder which the project is located and open the command window in the folder. You should then be pointing to the location where the project is in your command window. Preform the following command

`mvn clean install`

The output should be as follows:

![](Project%20Documents/build%20success.JPG)

After this is done, the jar file can now be run using the following command in the command line:

`java -jar target/DavidWilliams-SoftwareMarch16-jar-with-dependencies.jar`

The program should then prompt you to enter the username and password for the database and that's it! It is up and running.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jenkins](https://www.jenkins.io/) - CI Pipeline Tool
* [Eclipse](https://www.eclipse.org/) - IDE
* [Java](https://www.java.com/en/download/) - Code Base
* [Trello](https://trello.com/) - Project Planning Kanban Board
* [JUnit](https://junit.org/junit5/) - Testing Tool
* [Mockito](https://site.mockito.org/) - Testing Tool
* [GCP](https://cloud.google.com/) - Cloud Host
* [SonarQube](https://www.sonarqube.org/) - Static Analysis Tool
* [Nexus](https://www.sonatype.com/product-nexus-repository) - Artefact Repository

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* 
* **David Williams**

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments
* **Chris Perrins** [christophperrins](https://github.com/christophperrins) for providing the initial code base.
* **Nicholas Johnson** [nickrstewarttds](https://github.com/nickrstewarttds) for training in the fundamentals used in this project.

