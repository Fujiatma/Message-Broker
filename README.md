# Message-Broker-Producer
build on spring boot, kafka, mongodb

# Kafka-Publisher
Apache kafka Publisher on Message Broker using SpringBoot

# Start zookeeper.start bat file like below
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# Start Kafka Server
.\bin\windows\kafka-server-start.bat .\config\server.properties

# Create Topic
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Airline_Topic

# Produce A Message
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic Airline_Topic

# Consume Message
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic Airline_Topic
