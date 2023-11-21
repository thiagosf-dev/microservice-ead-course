package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	private CourseStatus courseStatus;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private com.ead.course.enums.CourseLevel courseLevel;

	@Column(nullable = false)
	private UUID userInstructor;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<ModuleModel> models;

}
