# :cat2: Manual Deploying Tomcat Server :cat2:

This `README.md` file guides how to deploy a Tomcat Server manually

## :bookmark_tabs: Table of Contents
- [What is Tomcat Server?](#octocat-what-is-tomcat-server)
- [Download Apache Tomcat](#link-download-apache-tomcat)
- [Check for Java Runtime Environment and Set Up](#heavy_check_mark-check-for-java-runtime-environment-and-set-up)
  - [Check for Java Runtime Environment](#triangular_flag_on_postcheck-for-java-runtime-environment)
  - [Set up](#computer-set-up)
- [Manual Deploying Tomcat Server](#running_woman-manual-deploying-tomcat-server)
- [Deploy your Web Application](#rocket-deploy-your-web-application)
- [Undeploy your Web Application](#hourglass_flowing_sand-undeploy-your-web-application)
- [Learn more](#closed_book-learn-more)
- [License & Copyright](#grimacing-license--copyright)

## :octocat: What is Tomcat Server?

## :link: Download Apache Tomcat
:point_right: Go to [Apache Tomcat](https://tomcat.apache.org/index.html) to find the version that suitable for your computer and download

:point_right: Or
- Apache Tomcat Version 8.5.29 - Windows 32 bits: https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.29/bin/apache-tomcat-8.5.29-windows-x86.zip
- Apache Tomcat Version 8.5.29 - Windows 64 bits: https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.29/bin/apache-tomcat-8.5.29-windows-x86.zip

## :heavy_check_mark: Check for Java Runtime Environment and Set Up
*For Window OS*
### :triangular_flag_on_post:	Check for Java Runtime Environment: 
- Press `Window + R`
- Type `cmd`
- Type `javac` then press Enter, `java` then press Enter, and `keytool` then press Enter
### :computer: Set up:
**1. JAVA_HOME:**
- In search box on desktop, type `environment` and choose `Edit the system environment variables`
- The `System Properties` window is opented
- In tab Advanced, click `Environment Variables...` button
- The `Environment Variables` window is opented
- In `System variables`, click `New...` button
- In `Variable Name`, type `JAVA_HOME`
- In `Variable Value`:
  - First, in File Explorer, find location of `jdk folder`
    - Ex: C:\Program Files (x86)\Java\jdk1.8.0_301
  - Second, paste the url in the Variable Value
- Finally, click OK

**2. CLASSPATH:**
- Click `New...` to create new environment variable in `System variables`
- Variable Name: `CLASSPATH`
- Variable Value: 
```
.;[above-url-to-jdk-folder]\lib;[above-url-to-jdk-folder]\jre\lib
```

**3. Path:**
- In System Variables, click one left mouse to anywwhere
- `Press P`
- The window displays Path
- Click `Path` then click `Edit...`
- Click `New`
- Type `[above-url-to-jdk-folder]\bin`

**4. CATALINA_HOME:**
- Remember to extract folder Apache Tomcat that you downloaded before
- Click `New...` to create new environment variable in `System variables`
- Variable Name: `CATALINA_HOME`
- Variable Value: `[url-to-extracted-apache-tomcat-folder]`

## :running_woman: Manual Deploying Tomcat Server
- Open `cmd`
- Type in turn: (press enter after one line)
```
cd [url-to-extracted-apache-tomcat-folder]\bin
d: 
startup
```
- Open Internet Explorer
- Type `http://localhost:port/` with port find out after start up server Tomcat in line
```
datetime INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-portnumber"]
```
- Your browser will display a cat

## :rocket: Deploy your Web Application
- Find the `.war` file then paste in `webapps` folder in folder Apache Tomcat in your computer
  - Ex: [MVC2.war](https://github.com/tienhuynh-tn/java-web-application-development-prj301/blob/master/MVC2/dist/MVC2.war)
- Go back to your browser then type more: `your-project-name`
- Your project will run automatically

## :hourglass_flowing_sand: Undeploy your Web Application
- Go to `webapps` folder
- Choose your `project-folder` and `project-name.war`, then delete
- Go back to your cmd, type `shutdown`

## :closed_book: Learn More
- [Triển khai Deploy ứng dụng web trên server Tomcat độc lập với Netbeans](http://www.kieutrongkhanh.net/2016/08/trien-khai-deploy-ung-dung-web-tren.html) - Author: Kieu Trong Khanh

## :grimacing: License & Copyright
&copy; 2021 Tien Huynh tienhuynh-tn Licensed under the [MIT LICENSE](https://github.com/tienhuynh-tn/java-web-application-development-prj301/blob/master/LICENSE).

> :love_you_gesture: Feel free to use my repository and star it if you find something interesting :love_you_gesture:



