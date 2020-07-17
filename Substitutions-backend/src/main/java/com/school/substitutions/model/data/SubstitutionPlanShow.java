package com.school.substitutions.model.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SubstitutionPlanShow extends Show {

	@Temporal(TemporalType.DATE)
	private Date date;

}
