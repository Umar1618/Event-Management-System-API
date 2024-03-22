package com.gyangrove.repository;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyangrove.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	Page<Event> findByDateBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable);

	int countByDateBetween(LocalDate fromDate, LocalDate toDate);

}
