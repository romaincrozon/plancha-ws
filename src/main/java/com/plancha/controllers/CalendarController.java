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
import com.plancha.dto.DayItem;
import com.plancha.dto.PlanchaCalendar;
import com.plancha.dto.WeekItem;
import com.plancha.dto.entity.CalendarItem;
import com.plancha.dto.entity.ResourceCalendar;
import com.plancha.repositories.CalendarItemRepository;
import com.plancha.repositories.ResourceCalendarRepository;
import com.plancha.utils.CalendarUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarController {

	@Autowired
	private CalendarItemRepository calendarItemRepository;
	
	@Autowired
	private ResourceCalendarRepository resourceCalendarRepository;

	@GetMapping(value = "/calendarItem", produces = "application/json")
	public List<CalendarItem> getCalendarItems() {
		return calendarItemRepository.findAll();
	}
	
	@PostMapping(value = "/calendarItemsByDate", produces = "application/json")
	public List<DayItem> getCalendarItemsByDate(@RequestBody CalendarRange calendarRange) {
		return calendarItemRepository.findCalendarItemsByDate(calendarRange.getStartDate(), calendarRange.getEndDate());
	}

	@PostMapping(value = "/calendar", consumes = "application/json", produces = "application/json")
	public CalendarList getCalendarByDate(@RequestBody CalendarRange calendarRange) {
		return new CalendarList(calendarRange, false);
	}		
	
	@PostMapping(value = "/calendar/week", consumes = "application/json", produces = "application/json")
	public CalendarList getCalendarWeekByDate(@RequestBody CalendarRange calendarRange) {
		return new CalendarList(calendarRange, true);
	}		
	
	@PostMapping(value = "/calendar/nextweeks/{nbWeeks}", consumes = "application/json", produces = "application/json")
	public CalendarList getCalendarByDate(@PathVariable int nbWeeks, @RequestBody CalendarRange calendarRange) {
//		System.out.println("toto" + new CalendarRange(nbWeeks, calendarRange.getStartDate()));
		return new CalendarList(new CalendarRange(nbWeeks, calendarRange.getStartDate()), false);
	} 
	
	@PostMapping(value = "/dailyCalendarItem", consumes = "application/json", produces = "application/json")
	public CalendarItem postResourceCalendar(@RequestBody DayItem dayItem) {
		if (dayItem.getId() != null) {
			CalendarItem calendarItem = calendarItemRepository.findById(dayItem.getId()).orElse(null);
			if (calendarItem != null) {
				calendarItem.setValue(dayItem.getValue());
				return calendarItemRepository.save(calendarItem);
			}
		}
		ResourceCalendar resourceCalendar = resourceCalendarRepository.findById(dayItem.getResourceCalendarId()).orElse(null);
		if (resourceCalendar != null) {
			CalendarItem calendarItem = dayItem.toCalendarItem(resourceCalendar);
			return calendarItemRepository.save(calendarItem);
		}
		return null;
	}
	
	@PostMapping(value = "/weeklyCalendarItem", consumes = "application/json", produces = "application/json")
	public List<CalendarItem> postResourceCalendarWeek(@RequestBody WeekItem weekItem) {
		if (!weekItem.getCalendars().isEmpty()) {
			weekItem.setCalendars(CalendarUtils.getWeekFromCalendar(weekItem.getCalendars().get(0)));
			ResourceCalendar resourceCalendar = resourceCalendarRepository.findById(weekItem.getResourceCalendarId()).orElse(null);
			if (resourceCalendar != null) {
				List<CalendarItem> calendarItems = weekItem.toCalendarItem(resourceCalendar);
				return calendarItemRepository.saveAll(calendarItems);
			}
		}
		return null;
	}	
	
	@PostMapping(value = "/calendar/{idResource}", consumes = "application/json", produces = "application/json")
	public PlanchaCalendar postCalendarByResource(@PathVariable("idResource") Long idResource) {
		return null;
	}	
		
}
