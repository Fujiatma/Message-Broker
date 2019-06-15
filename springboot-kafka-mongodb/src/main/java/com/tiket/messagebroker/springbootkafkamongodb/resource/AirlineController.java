package com.tiket.messagebroker.springbootkafkamongodb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.springbootkafkamongodb.model.Airline;
import com.tiket.messagebroker.springbootkafkamongodb.repository.AirlineRepository;

@RestController
public class AirlineController {
	
	@Autowired
	private AirlineRepository repository;
	
	@PostMapping("/addAirline")
	public String saveAirline(@RequestBody Airline airline) {
		repository.save(airline);
		return "Added airline with id : "+ airline.getId();
	}
	
	

}
