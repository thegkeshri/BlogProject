package com.project.spring.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer catId;
	
	@NotBlank
	@Size(min=4)
	private String catTitle;
	
	@NotBlank
	@Size(min=10)
	private String catDesc;

}
