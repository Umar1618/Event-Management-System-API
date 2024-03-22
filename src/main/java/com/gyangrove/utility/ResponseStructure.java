package com.gyangrove.utility;

import java.util.List;
import org.springframework.stereotype.Component;
import com.gyangrove.dto.EventResponseDTO;

@Component
public class ResponseStructure<T> {

	private List<EventResponseDTO> events;
	private int page;
	private int pageSize;
	private int totalEvents;
	private int totalPages;
	
	public List<EventResponseDTO> getEvents() {
		return events;
	}
	public void setEvents(List<EventResponseDTO> events) {
		this.events = events;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalEvents() {
		return totalEvents;
	}
	public void setTotalEvents(int totalEvents) {
		this.totalEvents = totalEvents;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}