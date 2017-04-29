# Programming Languages, Concurrency and Client Server Computing (Assignment 2)

## Setup
This guide gives instructions on setting up this project on `Windows`.

* Install [apache-maven-3.3.9-bin.zip](http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.zip), download the .zip and follow the instructions found [here](https://maven.apache.org/install.html).
* * Java: `JAVA_HOME` should point to `C:\Program Files\Java\jdk-version`.
* * Maven: `PATH` should have `;path/to/apache-maven-3.3.9/` appended to the end.
* Intall [NodeJS + NPM](https://nodejs.org/en/) to get access to extra packages like Webpack, Bootstrap and ReactJS.
* Clone this repository using SourceTree or GitHub Client using the repository url.
* * Git Shell can also be used: `git clone https://github.com/XenoWarrior/ci346-Programming-Languages.git`

## Installation
If all is setup correctly, you should be able to run `mvn -v` and see `Apache Maven 3.3.9 {...}` returned instead of `mvn is not recognized as an internal or external command, program or batch file.`
You can now setup the project using `mvn install` and `npm install -g` (remove `-g` if you do not want a global installation)

## Running
The project should compile and run by simply running `mvn spring-boot:run`.
Maven has been configured to automatically run `npm install` and `webpack`.

## Runtime
When running the project for the first time, I assume you have already configured an SQL database.

Default:
* username: root
* password: usbw
* database: ci346_employees
* port: 3306

If all these conditions are met, you should be able to click the "Create Table" debug button to automatically insert a table.
Followed by "Add Test Employees" to populate the database with some employee records.

## Copyright
This project includes the following third-party packages:
* MIT
* * [MaterialiseCSS](http://materializecss.com/), MIT
* * [Babel Core](https://github.com/babel/babel), MIT
* * [Babel Loader](https://github.com/babel/babel), MIT
* * [Babel Preset ES2015](https://github.com/babel/babel/tree/master/packages/babel-preset-es2015), MIT
* * [Babel Preset React](https://github.com/babel/babel/tree/master/packages/babel-preset-react), MIT
* * [jQuery](https://www.npmjs.com/package/jquery), MIT
* * [Webpack](https://www.npmjs.com/package/webpack), MIT

* GNU
* * [MySQL Connector Java](https://mvnrepository.com/artifact/mysql/mysql-connector-java), GNU

* Apache 2.0
* * [Apache Maven](https://maven.apache.org/), APACHE LICENSE, VERSION 2.0
* * [Spring Boot](https://github.com/spring-projects/spring-boot), APACHE LICENSE, VERSION 2.0
* * [Spring Framework](https://github.com/spring-projects/spring-framework), APACHE LICENSE, VERSION 2.0
* * [Google GSON](https://github.com/google/gson), APACHE LICENSE, VERSION 2.0

* * Other
* [NodeJS + NPM](https://nodejs.org/), [License](https://github.com/nodejs/node/blob/master/LICENSE)

* BSD
* * [ReactJS](https://facebook.github.io/react/), [BSD-3-Clause](https://spdx.org/licenses/BSD-3-Clause)
