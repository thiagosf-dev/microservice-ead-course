package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Table(name = "TB_COURSES")
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CourseModel
		implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID courseId;

	@Column(length = 150, nullable = false)
	private String name;

	@Column(length = 255, nullable = false)
	private String description;

	@Column
	private String imageUrl;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime creationDate;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime lastUpdateDate;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private CourseStatus CourseStatus;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private com.ead.course.enums.CourseLevel CourseLevel;

	@Column(nullable = false)
	private UUID userInstructor;

}
