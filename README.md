# legoRoboArmCallback
Java code used to communicate a Raspberry Pi robo arm with Oracle IoT Cloud and Oracle Asset Monitoring.

## INSTALL JAVA AND MAVEN (Raspbian has Java SE by default)

sudo apt-get install maven

## INSTALL LIBS

cd libs
sh mvn_install.sh 

## START THE LISTENER

cd iot-gateway
mvn spring-boot:run

Tested with Raspberry Pi 3 model B with Raspbian. 
