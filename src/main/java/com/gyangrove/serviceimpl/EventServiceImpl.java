package com.gyangrove.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.gyangrove.dto.Distance;
import com.gyangrove.dto.EventResponseDTO;
import com.gyangrove.dto.Weather;
import com.gyangrove.exception.EventsNotFoundException;
import com.gyangrove.exception.ExternalApiException;
import com.gyangrove.model.Event;
import com.gyangrove.repository.EventRepository;
import com.gyangrove.service.EventService;
import com.gyangrove.utility.ResponseStructure;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Value("${distance.calculator.url}")
	private String distanceCalculatorUrl;
	@Value("${weather.calculator.url}")
	private String weatherCalculatorUrl;
	@Value("${api.base-url}")
	private String baseUrl;

	public ResponseEntity<ResponseStructure<List<EventResponseDTO>>> findEvent(double latitude, double longitude,
			String date, int pageNumber) {
		LocalDate fromDate = LocalDate.parse(date);
		LocalDate toDate = fromDate.plusDays(14);

		Pageable page = PageRequest.of(pageNumber - 1, 10, Sort.by("date").ascending());
		Page<Event> events = eventRepository.findByDateBetween(fromDate, toDate, page);

		if (events.isEmpty()) {
			throw new EventsNotFoundException("Events Object Not Found");
		}

		List<CompletableFuture<EventResponseDTO>> eventResponseFutures = events.stream()
				.map(event -> CompletableFuture.supplyAsync(() -> createEventResponseDTO(event, latitude, longitude)))
				.collect(Collectors.toList());

		CompletableFuture<Void> allFutures = CompletableFuture
				.allOf(eventResponseFutures.toArray(new CompletableFuture[0]));

		CompletableFuture<List<EventResponseDTO>> allResponsesFuture = allFutures.thenApply(
				future -> eventResponseFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()));

		List<EventResponseDTO> eventResponses = allResponsesFuture.join();

		int totalEvents = eventRepository.countByDateBetween(fromDate, toDate);
		int totalPages = (int) Math.ceil((double) totalEvents / 10);

		ResponseStructure<List<EventResponseDTO>> responseStructure = new ResponseStructure<>();
		responseStructure.setEvents(eventResponses);
		responseStructure.setPageSize(10);
		responseStructure.setTotalEvents(totalEvents);
		responseStructure.setPage(pageNumber);
		responseStructure.setTotalPages(totalPages);
		return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
	}

	private EventResponseDTO createEventResponseDTO(Event event, double latitude, double longitude) {
		EventResponseDTO dto = new EventResponseDTO();
		dto.setCity_name(event.getCityName());
		dto.setDate(event.getDate());
		dto.setEvent_name(event.getEventName());
		dto.setWeather(getWeatherDetails(event.getCityName(), event.getDate().toString()));
		dto.setDistance_km(
				Double.parseDouble(getDistanceDetails(latitude, longitude, event.getLatitude(), event.getLongitude())));
		return dto;
	}

	private String getWeatherDetails(String city, String date) {
		String url = baseUrl + weatherCalculatorUrl + "&city=" + city + "&date=" + date;
		return fetchData(url, Weather.class).getWeather();
	}

	private String getDistanceDetails(double latitude1, double longitude1, double latitude2, double longitude2) {
		String url = baseUrl + distanceCalculatorUrl + "&latitude1=" + latitude1 + "&longitude1=" + longitude1
				+ "&latitude2=" + latitude2 + "&longitude2=" + longitude2;
		return fetchData(url, Distance.class).getDistance();
	}

	private <T> T fetchData(String url, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseType);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {
			throw new ExternalApiException("Failed to fetch data from external API: " + url);
		}
	}

	@Override
	public ResponseEntity<String> readCSV(MultipartFile csvFile) throws IOException {
		InputStream inputStream = csvFile.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
		List<CSVRecord> records = csvParser.getRecords();
		for (CSVRecord record : records) {
			if (record != records.get(0)) {
				Event e = new Event();
				e.setEventName(record.get(0));
				e.setCityName(record.get(1));
				e.setDate(LocalDate.parse(record.get(2)));
				e.setTime(record.get(3));
				e.setLatitude(Double.parseDouble(record.get(4)));
				e.setLongitude(Double.parseDouble(record.get(5)));
				eventRepository.save(e);
			}
		}
		csvParser.close();
		return new ResponseEntity<String>("All CSV Data Transfered To Database", HttpStatus.OK);
	}

}
