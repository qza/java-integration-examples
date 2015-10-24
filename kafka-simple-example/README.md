Kafka basic Examples
====================

Basic example with Apache Kafka.

Kafka server should be previously started and "testtopic" should be previously created.

Download Kafka from [official download page](http://kafka.apache.org/downloads.html)

Checkout the bin folder of Kafka installation for required scripts.

If you are running on windows, [this guide](http://www.alternatestack.com/uncategorized/running-kafka-on-windows/) can help you overcoming obstacles when starting Kafka server. 

Run with: 

mvn exec:java -Dexec.mainClass=org.qza.integration.kafka.BasicProducer -Dexec.args="testtopic testmessage"