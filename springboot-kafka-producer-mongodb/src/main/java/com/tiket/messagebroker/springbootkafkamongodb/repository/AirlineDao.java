package com.tiket.messagebroker.springbootkafkamongodb.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiket.messagebroker.springbootkafkamongodb.model.Airline;
import com.tiket.messagebroker.springbootkafkamongodb.model.AirlineUpdatePayLoad;

@Component
public class AirlineDao {
	@Autowired
	private AirlineRepository airlineRepository;
	
	public Collection<Airline> getAirlines(){
		return airlineRepository.findAll();
	}

	public Airline createAirline(Airline airline) {
		return airlineRepository.insert(airline);
	}

	public Optional<Airline> getAirlineById(int id) {
		return airlineRepository.findById(id);
	}
	
	public Optional<Airline> deleteAirlineById(int id) {
		Optional<Airline> airline = airlineRepository.findById(id);
		airline.ifPresent(plane -> airlineRepository.delete(plane));
		return airline;
	}

	public Optional<Airline> updateAirlineById(int id, AirlineUpdatePayLoad airlineUpdatePayLoad) {
		Optional<Airline> airline = airlineRepository.findById(id);
		airline.ifPresent(plane -> plane.setStatus(airlineUpdatePayLoad.getStatus()));
		airline.ifPresent(plane -> airlineRepository.save(plane));
		return airline;
	}
}
