package com.plancha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plancha.dto.CalendarList;
import com.plancha.dto.CalendarRange;
import com.plancha.dto.PlanchaCalendar;
import com.plancha.dto.entity.Assignment;
import com.plancha.dto.entity.CalendarItem;
import com.plancha.repositories.CalendarItemRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarController {

	@Autowired
	private CalendarItemRepository calendarItemRepository;
	
	@GetMapping(value = "/calendarItem", produces = "application/json")
	public List<CalendarItem> getCalendarItems() {
		return calendarItemRepository.findAll();
	}
	
	@PostMapping(value = "/calendar", consumes = "application/json", produces = "application/json")
	public CalendarList getCalendarByDate(@RequestBody CalendarRange calendarRange) {
		return new CalendarList(calendarRange);
	}		
	
	@PostMapping(value = "/calendarItem", consumes = "application/json", produces = "application/json")
	public CalendarItem postCalendarItem(@RequestBody CalendarItem calendarItem) {
		return calendarItemRepository.save(calendarItem);
	}	
	
	@PostMapping(value = "/calendar/{idResource}", consumes = "application/json", produces = "application/json")
	public PlanchaCalendar postCalendarByResource(@PathVariable("idResource") long idResource) {
		return null;
	}	
		
}
