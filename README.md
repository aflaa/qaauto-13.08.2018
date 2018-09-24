Environment setup:
1.	Download and install Java/JDK. http://www.oracle.com/technetwork/java/javase/downloads/index.html 
2.	Download and install Intelij Idea, Community version. https://www.jetbrains.com/idea/download/#section=windows 
3.	Create new java project with Maven. Project name should have the same name as ArtifactId.
4.	Find and copy selenium maven settings to pom.xml in new tag Dependencies. https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java 
5.	Download chromedriver.exe and copy it to System32 (or create Path in variables). https://chromedriver.storage.googleapis.com/index.html?path=2.41/ 
6.  Add Dependancy for Test NG https://mvnrepository.com/artifact/org.testng/testng
7.  Download and install GIT from https://gitforwindows.org/. Check Path variable with GIT.
8.	Port 8080 should be opened (use VPN).
9.	Download windows Jenkins
10.	Download maven https://maven.apache.org/download.cgi
11.	To start tests use Terminal tab start xml file, used in POM:  mvn clean install -DsuiteXmlFile=TestNG.xml
12.	Add plugin to POM
