Requires SQLServer jdbc-connector :

mvn install:install-file -Dfile=<path-to-file> -DgroupId=com.microsoft -DartifactId=sql-jdbc-connetor -Dversion=1.0.0.RELEASE -Dpackaging=jar

$ mvn install:install-file -Dfile=sqljdbc.jar -DgroupId=com.microsoft -Dartifac
tId=sql-jdbc-connector -Dversion=1.0.0.RELEASE -Dpackaging=jar