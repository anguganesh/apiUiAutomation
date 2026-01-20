uiApiAutomation project details

Packages Details :

com.ui.api.automation.common.enums ===> Used to maintain hardcoded values in this file (ie) empty string ("") or Numbers (1,2,3) etc and WebElement attributes like value, name etc
com.ui.api.automation.configuration ===> Browser configuration and Allure Reports configurations are maintained
com.ui.api.automation.model ===> Converted all pages into corresponding POJO classes. Instead of transferring data from feature files, read data from yaml and convert input data as POJO objects. This POJO object (Java Object) will be used for providing inputs in page classes during execution.
com.ui.api.automation.pages ===> Maintain all pages in this package
com.ui.api.automation.rerun ===> Maintain all the files related to re-execute failed testcases automatically during runtime execution.
com.ui.api.automation.runner ===> Able to run our project using JUnit and TestNG. So Both runner files are kept here.
com.ui.api.automation.steps ===> Used to maintain all step defn files
com.ui.api.automation.yamlDetails ===> To maintain location of all yaml files.
com.ui.automation.helpers ===> YamlHelper is used to read data from yaml files. VisibilityHelper is used to maintain explicit wait code for WebElements. To perform common operations like click(), sendKeys() etc, we kept commonFunctions class separately (To avoid code duplication, maintain code looks simple)
allure.properties ===> To Maintain allure report under target directory

Features Directory ===> Maintain all the feature files under Feature directory

Screenshot Directory ==> To maintain failure testcases screenshots during execution

YamlData Directory ==> Maintain all testdata as yaml files. All yaml files are maintained under YamlData directory
Configure Lombok :

In this framework, we have used lombok to generate getter setter methods for reading yaml data.
1. Download lombok from https://projectlombok.org/download
2. Double click on lambok.jar file
3. Configure location of eclipse
4. Lombok installation will be successful
5. Update Maven project
6. Restart Eclipse
7. Lombok will be configured successfully
Configure Maven :

 1. download latest maven from https://maven.apache.org/download.cgi
 2. Unzip and copy to C: Drive
 3. Configure MVN_HOME in environment variables
 4. Configure %MVN_HOME%\bin in PATH variables
 5. type as mvn -version
 6. Able to view maven version successfully
POM Configuration Details Parallel Execution:

     JUnit :
        We can run feature files into Parallel execution, NOT scenarios. 
        configure threadCount attribute as N to take N feature files at a time
     TestNG :
        We can run scenarios into Parallel execution
        configure threadCount attribute as N to take N feature files at a time   
POM Configuration Details for Rerun:

      JUnit :
        configure rerunFailingTestsCount attribute to reexecute failure testcases
        N times
     TestNG :
        We configured listeners in testng.xml file to reexecute failed testcases.
Execution Reports :

   We are generating Allure Report. We have used allure-cucumber7-jvm dependencty to generate
   reports. Mentioned allure report listeners under plugin attribute in JUnit and TestNG Runner    
   files.
Procedure to view Report

   1. Download allure zip file from https://github.com/allure-framework/allure2/releases
   2. Unzip into C:\ drive
   3. Configure ALLURE_HOME in environment variables
   4. Configure %ALLURE_HOME%\bin in PATH variables
   5. type allure -version command in command prompt
   6. Able to view allure version successfully
   7. Navigate to uiApiAutomation directory
   8. execute  mvn clean install command
   9. type   allure serve target\AllureReport
   10. Able to view Allure Report successfully      
Running project using Junit and TestNG

JUnit :
    1. comment TestNG surefire plugin completely
    2. Navigate to project in command prompt
    3. Run mvn clean install
   
TestNG :
    1. comment JUnit surefire plugin completely
    2. Navigate to project in command prompt
    3. Run mvn clean install  
    
Below mentioned steps to implement
   1. Cucumber HTML Report
   2. Cucumber Json Report
   3. Allure Report
   4. Extent Report
   5. Enable Rerun for Junit / TestNG
   