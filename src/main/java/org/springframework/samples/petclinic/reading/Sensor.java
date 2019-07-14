package org.springframework.samples.petclinic.reading;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.owner.PetType;

@Entity
@Table(name = "sensors")
public class Sensor extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}	
	
}
