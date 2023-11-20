package com.ead.course.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.course.models.CourseModel;

public interface CourseService {

	List<CourseModel> findAll();

	Optional<CourseModel> findById(UUID courseId);

	CourseModel save(CourseModel courseModel);

	void delete(CourseModel courseModel);

}
