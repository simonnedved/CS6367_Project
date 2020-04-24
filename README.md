# CS6367_Project
This is the course project of CS6367.

---

<h2>Dependency:</h2>

JDK: Java SE Development Kit 9 -- [Download JDK](https://www.oracle.com/java/technologies/javase/javase9-archive-downloads.html)

ASM: asm-all-6.0_BETA -- [ASM-6.0_Beta](https://github.com/simonnedved/CS6367_Project/tree/master/ASM/lib)

JUnit: junit 4.13

Java Agent: Included in JDK 9

JUnit Listener: Included in JDK 9

Maven: apache-maven-3.6.3-bin.zip -- [Download Maven](https://maven.apache.org/download.cgi)



---

<h3>Phase-1 (Due: March 13th)</h2>

Use ASM byte-code manipulation framework to build an automated coverage collection tool that can capture the statement coverage for the program under test. Then, apply your tool to 10 real-world Java projects (>1000 lines of code) with JUnit tests (>50 tests) from GitHub to collect the statement coverage for its JUnit tests. 

Note that your tool should (1) use Java Agent to perform on-the-fly code instrumentation, (2) be able to store the coverage for each test method in the file system, and (3) be integrated with the Maven build system so that your tool can be triggered by simply typing “mvn test” after changing the pom.xml file of the project under test. More implementation details are shown in the appendix. 
