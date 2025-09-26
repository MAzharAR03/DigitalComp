To run the program, ensure that JDK 21 or above is installed and PATH environment variable points to JDK 21 (To see how to do that refer to here: https://www.java.com/en/download/help/path.html)
Download all code in one directory 
Download the sqlite3 jdbc driver which can be found here: https://github.com/xerial/sqlite-jdbc#download

To compile and run the program navigate to the directory where the java files have been downloaded in command prompt
Type: "java *.java"
Followed by: "java -classpath ".;sqlite-jdbc-3.50.3.0.jar" Main "(3.50.3.0 must be replaced with the version of the sqlite3 jdbc driver)
