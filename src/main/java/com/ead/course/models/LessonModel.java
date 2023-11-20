package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Entity
@Table(name = "TB_LESSONS")
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LessonModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID lessonId;

	@Column(length = 150, nullable = false)
	private String title;

	@Column(length = 255, nullable = false)
	private String description;

	@Column(nullable = false)
	private String videoUrl;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime creationDate;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private ModuleModel module;

}
