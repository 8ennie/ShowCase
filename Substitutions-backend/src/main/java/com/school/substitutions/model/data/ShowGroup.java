package com.school.substitutions.model.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowGroup {

	@Id
	private Long id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "showGroup")
	List<Monitor> monitors;
	
}
