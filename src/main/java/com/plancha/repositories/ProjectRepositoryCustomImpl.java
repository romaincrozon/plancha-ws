package com.plancha.repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.plancha.dto.entity.CalendarItem;
import com.plancha.dto.entity.Project;
import com.plancha.dto.entity.ResourceCalendar;
import com.plancha.dto.entity.SubProject;
import com.plancha.dto.entity.Task;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Project> findProjectByDate(Calendar begin, Calendar end) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Project> query = cb.createQuery(Project.class);
		
		Root<Project> fromProject = query.from(Project.class);
		Root<SubProject> fromSubproject = query.from(SubProject.class);
		Root<Task> fromTask = query.from(Task.class);
		Root<ResourceCalendar> fromResourceCalendar = query.from(ResourceCalendar.class);
		Root<CalendarItem> fromCalendarItem = query.from(CalendarItem.class);

	    Join<CalendarItem, ResourceCalendar> resourceCalendarJoin = fromCalendarItem.join("resourceCalendar");
	    Join<ResourceCalendar, Task> taskJoin = fromResourceCalendar.join("task");
	    Join<Task, SubProject> subProjectJoin = fromTask.join("subProject");
	    Join<SubProject, Project> projectJoin = fromSubproject.join("project");
	    
	    TypedQuery<Project> typedQuery = entityManager.createQuery(query

	    		.select(fromProject)
	            .where(cb.and(
	                    cb.greaterThanOrEqualTo(fromCalendarItem.get("calendar"), begin),
	                    cb.lessThanOrEqualTo(fromCalendarItem.get("calendar"), end),
	                    cb.equal(fromCalendarItem.get("resourceCalendar"), resourceCalendarJoin),
	                    cb.equal(fromResourceCalendar.get("task"), taskJoin),
	                    cb.equal(fromTask.get("subProject"), subProjectJoin),
	                    cb.equal(fromSubproject.get("project"), projectJoin)
	            ))
//	            .orderBy(cb.asc(fromCalendarItem.get("calendar")))
	            .distinct(true)
	    );
		return typedQuery.getResultList();
	}

//	@Override
//	public List<Project> findAllUsersByPredicates(Collection<java.util.function.Predicate<User>> predicates) {
//		List<User> allUsers = entityManager.createQuery("select u from User u", User.class).getResultList();
//		Stream<User> allUsersStream = allUsers.stream();
//		for (java.util.function.Predicate<User> predicate : predicates) {
//			allUsersStream = allUsersStream.filter(predicate);
//		}
//
//		return allUsersStream.collect(Collectors.toList());
//	}
}
