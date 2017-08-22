# daniels-jar

How to build the project:

mvn clean install
or 
via Eclipse IDE: right click on pom -> Run As -> Maven Build



How to run the project;

1. Run the file named Server (located in FraudDetection module) either from cmd or some IDE. You should see "Server ready" in console.
  Running the server will also start rmiregistry tool so that RMI calls may come from transaction generator.
  If RMI does not start, to to target folder of the FraudDetection project ( ex: D:/foo/FraudDetection/target/classes) and run: start rmiregistry in cmd.

2. Run the file named Client (located in TransactionGenerator module) either from cmd or some IDE - this will ask you to specify how many 
transactions should be generated, and by which kind (normal, potential fraud, fraud).

3. Run the file named MainWindow (located in FraudDetectionUI module) either from cmd or some IDE. You should see a frame with some buttons.

Enjoy!
