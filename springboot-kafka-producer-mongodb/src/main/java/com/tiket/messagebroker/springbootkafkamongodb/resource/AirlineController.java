package com.tiket.messagebroker.springbootkafkamongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.springbootkafkamongodb.model.Airline;
import com.tiket.messagebroker.springbootkafkamongodb.repository.AirlineRepository;

@RestController
@RequestMapping("kafka")
public class AirlineController {
	
	@Autowired
	private AirlineRepository repository;
	
	//send Object
	@Autowired
	private KafkaTemplate<String, Airline> kafkaTemplate;
	
	//send String
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
	private static final String TOPIC = "Airline_Topic";
	
	@PostMapping("/addAirline")
	public String saveAirline(@RequestBody Airline airline) {
		repository.save(airline);
		return "Added airline with id : "+ airline.getId();
	}
	
	@GetMapping("/findAllAirlines")
	public List<Airline> getAirlines(){
		return repository.findAll();
	}
	
	//publish Object
//	@PostMapping("/publish/airline")
//	public String post(@RequestBody Airline airlinePost) {
//		kafkaTemplate.send(TOPIC, airlinePost);
//		
//		return "Published Successfully";
//	}
	
	
	//manually HARDCODE
	@GetMapping("/publish/{id}")
	public String post(@PathVariable("id") final int id) {
		 
		kafkaTemplate.send(TOPIC, new Airline(id, "AR-SR123", "Susi Air", "Inactive"));
		return "Published Successfully"; 
	}
	
	
	//testProduceMessage
//	@GetMapping("/publish/{message}")
//	public String post(@PathVariable("message") final String message) {
//		 
//		kafkaTemplate.send(TOPIC, message);
//		return "Published Successfully"; 
//	}
//	


}
