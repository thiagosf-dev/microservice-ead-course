package com.ead.course.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;

@Service
public class CourserServiceImpl
		implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	LessonRepository lessonRepository;

	@Transactional
	@Override
	public void delete(CourseModel courseModel) {
		// 1 verificar quais módulos pertencem ao curso
		// 2 verificar quais lissões pertencem a cada módulo de cada curso
		// 3 deletar as lissões de cada módulo
		// 4 deletar os módulos de cada curso
		// 5 deletar o curso
		List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());

		if (!moduleModelList.isEmpty()) {
			for (ModuleModel module : moduleModelList) {
				List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());

				if (!lessonModelList.isEmpty()) {
					lessonRepository.deleteAll(lessonModelList);
				}
			}

			moduleRepository.deleteAll(moduleModelList);
		}

		courseRepository.delete(courseModel);
	}

	@Override
	public CourseModel save(CourseModel courseModel) {
		return courseRepository.save(courseModel);
	}

	@Override
	public Optional<CourseModel> findById(UUID courseId) {
		return courseRepository.findById(courseId);
	}

	@Override
	public List<CourseModel> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Page<CourseModel> findAllWithSpecification(Specification<CourseModel> courseSpec, Pageable pageable) {
		return courseRepository.findAll(courseSpec, pageable);
	}

}
