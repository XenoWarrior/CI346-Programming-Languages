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
