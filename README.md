# Overall Framework Features
This is a Selenium Hybrid Framework which is: 
- Written in **JAVA**
- Implemented using **TestNG**
- Build Tool - Maven
- Implemented Page Object Model Design Pattern
- WebDriver Manager - Auto Download of required drivers

### Browsers Supported
- Mozilla  Firefox
- Google Chrome
- Microsoft Edge Chromium

### Headless Support
- Firefox Headless
- Chrome Headless

### Platform Support
- Windows
- Linux
- Macintosh
---

### Reporting
- [Extent Report](https://www.extentreports.com/docs/versions/4/java/index.html)
Navigate to ./Reports and open the report you want
---

# Prerequisite:
1. [Selenium WebDriver](http://www.seleniumhq.org/) 
2.  [Java 17 / above 11](https://www.java.com/en/download/)
3. [Maven](https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/) 

# How to use the Framework
### 1. USING MAVEN
```sh
$ git clone <https:https://github.com/akshay3799-g/Selenium_Java_TestNG>
```
```sh
$ mvn clean test
```
```
### Browser Setup
- Navigate to *EntrataSeleniumTest\src\main\resources\application.properties* change *BrowserType* in the application.properties or use Maven to invoke different browsers
```
```sh
$ mvn clean test -DBrowserType=Chrome #Chrome
$ mvn clean test -DBrowserType=Chrome_Headless #Chrome Headless
$ mvn clean test -DBrowserType=Firefox #Mozilla  Firefox
$ mvn clean test -DBrowserType=Edge #Microsoft Edge
$ mvn clean test -DBrowserType=Mobile #Mobile Resolution
$ mvn clean test -DBrowserType=Mobile_Headless #Mobile Resolution - Headless
$ mvn clean test -DBrowserType=iPadMini #iPadMini Resolution
$ mvn clean test -DBrowserType=iPadMini_Headless #iPadMini Resolution - Headless
$ mvn clean test -DBrowserType=iPadPro #iPadPro Resolution
$ mvn clean test -DBrowserType=iPadPro_Headless #iPadPro  Resolution - Headless
```
To run all test
1. Go to root folder
2. In testng.xml update thread count if want to run parallel test
3. we can run testng.xml file with mentioned classes or can run below command
```sh
$ mvn verify
```

### 2. USING IDE(e.g. Eclipse or IntelliJ IDEA)
a. Import branch of this repo in Your preferred IDE
b. Download all Dependencies using Maven Update
c. In the Root folder of the project there is TestNG Suites, so
- Run "TestNG.xml" if you want to run whole Framework
---

### Report Generation
```sh
$ mvn site
```

### Folder Structure
- **src** - It contains 2 folder main and Test.
- **main** - It contains 2 folder java and resources.
- **main.java** - This folder contains base, config and utils folder.
- **main.java.base** - It contains classes for setup of browser and initialize the setup.
- **main.java.config** - It contains common method to reuse and class of Listeners and Reporter.
- **main.java.utils** - It contains util classes like date and time generator, random value generator and generic method and Element class which contains methods to handle browser and selenium methods.
- **main.resources** - It contains properties file, logger and application properties.
- **test** - It contains 3 folder under java folder.
- **test.java.entrata_tests** - It contains test cases, each class represent as test case.
- **test.java.pageFactory** - It contains class to intiliaze page, so to use any method from any page we have to extend this class only.
- **test.java.pages** - It contains locators and method to perform actions, each class contains element and method for performing action on page.
- **some rest of the folders are:** Import_Export for auto download driver and if using any file from application to download inside this folder.
- **Logs** - auto generate and store logs of test.
- **Reports** - auto generate and store extent html report.
- **screenshots** - if test failed capture screenshot and store here then use in report as well.
- **.github** - it contains workflow folder with yml file which will auto trigger github action when code push to main branch
 
**~~NOTE~~:** 
- Seems like page loading in this website is slow, so if test fails due to page loading, please increase it from Element class with static varible 'waitInSeconds', and from ExplicitWaitng class from method 'waitForPageLoaded()'.
- Generated extent html report contains screenshot of failed test cases, in case if not able to see it then please open report from file explorer becuase if tries to open from IDE directly it may load from localhost and screenshot will not be visible.

