<h1>Instruction for using CodeCoverage</h1>

In command line run:
```
cd CodeCoverage
# these lines will create a JavaAgent jar file.
mvn clean install
```
```
# copy JavaAgent jar file to target project folder.
cd target
cp JavaAgent-1.0-SNAPSHOT.jar [destination]
```

Get into the project folder which you want to test, then edit its pom.xml.
Add follwing lines in pom.xml:

```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <configuration>
    <argLine>-javaagent:./JavaAgent-1.0-SNAPSHOT.jar
    </argLine>
   <properties>
    <property>
      <name>listener</name>
      <value>cs6367Project.JUnitListener</value>
    </property>
   </properties>
  </configuration>
</plugin>
```
Modify JUnit version to 4.13:
```
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13</version>
  <scope>test</scope>
</dependency>
```
Get into the target project folder, and run:
```
mvn clean test
# Trace log would be created in folder "6367TraceLog".
# Infer log would be created in folder "6367InferLog".
```

*Note:*

If you come across a compilation failure which asks you to use the 1.6 or higher version of maven compiler, add the 
following lines in pom.xml.

    <maven.compiler.compilerVersion>1.6</maven.compiler.compilerVersion>
    <maven.compiler.source>1.6</maven.compiler.source>
    <maven.compiler.target>1.6</maven.compiler.target>
