<h1>Instruction for using CodeCoverage</h1>

In command line run:
```
cd CodeCoverage
# these lines will create a JavaAgent jar file.
mvn clean 
mvn install
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

Get into the target project folder, and run:
```
mvn test
# Coverage log would be created in folder "coverageLog".
```

*Note:*

If you come across a compilation failure which asks you to use the 1.6 or higher version of maven compiler, add the 
following lines in pom.xml.

    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
