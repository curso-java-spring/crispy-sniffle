package org.springframework.samples.petclinic.reading;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "sensors")
public class Sensor extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
