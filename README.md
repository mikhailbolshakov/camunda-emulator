Run NATS server
````
cd $HOME/work/nats/usr/local/bin/
./nats-server -DV
````

Build
````
mvn clean compile assembly:single
````

Run
````
java -jar ./target/CamundaUserTaskEmulator-jar-with-dependencies.jar 
````


