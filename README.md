###Установка сторонней jar :
```
mvn install:install-file -Dfile=mycollection/lib/ropes.jar -DgroupId=org.ahmadsoft -DartifactId=ropes -Dversion=1.2.5 -Dpackaging=jar
```
###Запуск benchmark :
```
java -jat /benchmark/target/benchmarks.jar
```
