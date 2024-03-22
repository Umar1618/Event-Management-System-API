package com.gyangrove.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gyangrove.dto.EventResponseDTO;
import com.gyangrove.service.EventService;
import com.gyangrove.utility.ResponseStructure;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
		
	@GetMapping("/events/find")
	public ResponseEntity<ResponseStructure<List<EventResponseDTO>>> find(@RequestParam double latitude, @RequestParam double longitude, @RequestParam String date, @RequestParam int pageNumber) {
	    return eventService.findEvent(latitude, longitude, date, pageNumber);
	}
	
	@PostMapping("/events/read/csv")
	public ResponseEntity<String> readCSV(@RequestParam MultipartFile csvFile) throws IOException{
		return eventService.readCSV(csvFile);
	}
}
