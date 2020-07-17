package com.school.substitutions.model.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.school.substitutions.model.data.helper.ShowTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

	@Id
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ShowTypes type;

	
	
}
