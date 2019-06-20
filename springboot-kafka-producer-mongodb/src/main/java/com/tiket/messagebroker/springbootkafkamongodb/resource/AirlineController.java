package com.tiket.messagebroker.springbootkafkamongodb.resource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.springbootkafkamongodb.model.Airline;
import com.tiket.messagebroker.springbootkafkamongodb.model.AirlineUpdatePayLoad;
import com.tiket.messagebroker.springbootkafkamongodb.repository.AirlineRepository;
import com.tiket.messagebroker.springbootkafkamongodb.repository.AirlineRepositoryForKafka;
import com.tiket.messagebroker.springbootkafkamongodb.service.AirlineService;

@Component
@RestController
@RequestMapping("kafka")
public class AirlineController {
	
	@Autowired
	private AirlineRepositoryForKafka repository;
	
	@Autowired
	private AirlineService airlineService;
	
	//send Object
	@Autowired
	private KafkaTemplate<String, Airline> kafkaTemplate;
	
	private static final String TOPIC = "Airline_Topic";
	
	@PostMapping("/addAirline")
	public String saveAirline(@RequestBody Airline airline) {
		try {
			airlineService.createAirline(airline);
        } catch (Exception e) {
        	System.out.println(airline.getId()+" Has Been Exists");
            e.getMessage();
        }
		return "Added airline with id : "+ airline.getId();
	}
	
	@GetMapping("/findAllAirlines")
	public Collection<Airline> getAirlines(){
		return airlineService.getAirlines();
	}
	
	@GetMapping("/findById/{id}") 
	public Optional<Airline> getAirlineById(@PathVariable("id") int id){
		return airlineService.getAirlineById(id);
	}
	
	@DeleteMapping("/deleteById/{id}") 
	public Optional<Airline> deleteAirlineById(@PathVariable("id") int id){
		return airlineService.deleteAirlineById(id);
	}
	
	
	@PutMapping("/updateById/{id}") 
	public Optional<Airline> updateAirlineById(@PathVariable("id") int id, @RequestBody AirlineUpdatePayLoad airlineUpdatePayLoad){
		return airlineService.updateAirlineById(id, airlineUpdatePayLoad);
	}
	
	//publish Object to Kafka
	@PostMapping("/publish/{id}")
	public String post(@PathVariable("id") final int id, @RequestBody Airline airline) {
		kafkaTemplate.send(TOPIC, repository.findById(id));
		
		return "Published Successfully"; 
	}
	
	


}
