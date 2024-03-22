package com.gyangrove.service;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gyangrove.dto.EventResponseDTO;
import com.gyangrove.utility.ResponseStructure;

public interface EventService {

	public ResponseEntity<ResponseStructure<List<EventResponseDTO>>> findEvent(double latitude, double longitude, String date, int pageNumber);
	
	ResponseEntity<String> readCSV(MultipartFile csvFile) throws IOException;

}
