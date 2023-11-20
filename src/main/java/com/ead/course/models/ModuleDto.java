package com.ead.course.models;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ModuleDto {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

}
