package org.springframework.samples.petclinic.product;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "products")
public class Product extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	
}
