package org.springframework.samples.petclinic.reading;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

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
    private boolean normal;

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}	
	
	public int getUpper() {
		return crop.getUpper();
	}
	
	public int getLower() {
		return crop.getLower();
	}

	public boolean isNormal() {
		return normal;
	}

	public void setNormal(boolean normal) {
		this.normal = normal;
	}


}
