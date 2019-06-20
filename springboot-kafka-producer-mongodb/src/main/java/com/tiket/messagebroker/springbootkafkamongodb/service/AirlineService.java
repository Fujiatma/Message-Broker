package com.tiket.messagebroker.springbootkafkamongodb.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiket.messagebroker.springbootkafkamongodb.model.Airline;
import com.tiket.messagebroker.springbootkafkamongodb.model.AirlineUpdatePayLoad;
import com.tiket.messagebroker.springbootkafkamongodb.repository.AirlineDao;


@Service
public class AirlineService { 
	@Autowired
	private AirlineDao airlineDao;
	
	public Collection<Airline> getAirlines(){
		   return airlineDao.getAirlines();
	   }

	public Airline createAirline(Airline airline) {
		return airlineDao.createAirline(airline);
		
	}

	public Optional<Airline> getAirlineById(int id) {
		return airlineDao.getAirlineById(id);
	}

	public Optional<Airline> deleteAirlineById(int id) {
		return airlineDao.deleteAirlineById(id);
	}

	public Optional<Airline> updateAirlineById(int id, AirlineUpdatePayLoad airlineUpdatePayLoad) {
		return airlineDao.updateAirlineById(id, airlineUpdatePayLoad);
	}


	
}
