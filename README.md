# forsrc-springboot-gwt

* build
```shell
mvn clean package -DskipTests=true
```
* run forsrc-gwt-ui
  * http://127.0.0.1:8888/
    * password: forsrc@gmail.com/forsrc
```shell
cd forsrc-gwt-ui
mvn clean package -DskipTests=true gwt:run
```

* run forsrc-springboot-gwt-authorization
  * http://127.0.0.1:9999/uaa/
```shell
cd forsrc-springboot-gwt-authorization
mvn clean package -DskipTests=true spring-boot:run
```

* run forsrc-springboot-gwt-resource
  * http://127.0.0.1:7777/api/
```shell
cd forsrc-springboot-gwt-resource
mvn clean package -DskipTests=true spring-boot:run
```

* run forsrc-springboot-gwt-ui
  * http://127.0.0.1:8080/
    * password: forsrc@gmail.com/forsrc
```shell
cd forsrc-springboot-gwt-ui
mvn clean package -DskipTests=true spring-boot:run
```
