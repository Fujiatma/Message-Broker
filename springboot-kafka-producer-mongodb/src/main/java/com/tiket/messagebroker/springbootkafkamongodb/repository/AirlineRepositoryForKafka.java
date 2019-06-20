package com.tiket.messagebroker.springbootkafkamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiket.messagebroker.springbootkafkamongodb.model.Airline;

public interface AirlineRepositoryForKafka extends MongoRepository<Airline, Integer> {
	   public Airline findById(int id);

}
