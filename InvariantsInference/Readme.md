<h1>Instruction for using InvariantsInference</h1>

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
        <executions>
          <execution>
            <id>plain</id>
            <configuration>
              <includes>
                <include>**/*Test.java</include>
              </includes>
              <runOrder>random</runOrder>
            </configuration>
          </execution>
          <!-- <execution> <id>security-manager-test</id> <phase>integration-test</phase> <goals> <goal>test</goal> </goals> <configuration>
            <includes> <include>**/*Test.java</include> </includes> <argLine>-Djava.security.manager -Djava.security.policy=${basedir}/src/test/resources/java.policy</argLine>
            </configuration> </execution> -->
        </executions>
      <configuration><argLine>-javaagent:/Users/jnc350/Desktop/z/CS6367_Project/InvariantsInference/target/JavaAgent-1.0-SNAPSHOT.jar</argLine><properties><property><name>listener</name><value>cs6367Project.JUnitListener</value></property></properties></configuration></plugin>
```
Add JUnit Vintage engine to:
```
<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
    <version>5.6.1</version>
    <scope>test</scope>
</dependency>
```
Get into the target project folder, and run:
```
mvn test
# Coverage log would be created in folder "coverageLog".
```


