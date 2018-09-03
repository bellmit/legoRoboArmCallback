mvn install:install-file -Dfile=network-provisioner.jar -DgroupId=com.oracle.iot -DartifactId=network-provisioner -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=bootstrapper.jar -DgroupId=com.oracle.iot -DartifactId=bootstrapper -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=derby-10.11.1.1.jar -DgroupId=com.oracle.iot -DartifactId=derby -Dversion=10.11.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=device-library.jar -DgroupId=com.oracle.iot -DartifactId=device-library -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=enterprise-library.jar -DgroupId=com.oracle.iot -DartifactId=enterprise-library -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=json-20160212.jar -DgroupId=com.oracle.iot -DartifactId=json-20160212 -Dversion=1.0 -Dpackaging=jar