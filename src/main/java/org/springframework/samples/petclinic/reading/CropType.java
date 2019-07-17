/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.reading;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

/**
 * The <code>Crop</code> class represents a crop type. Each crop has and ideal
 * lower and upper bound humidity values.
 * 
 * Humidity is represented as a percentage relative humidity between 0% and
 * 100%.
 * 
 * A crop type has also an id and a name.
 * 
 * After a transaction, a constraint is <code> lower <= upper </code>.
 * 
 * @author marco.mangan@gmail.com
 * 
 * @see https://en.wikipedia.org/wiki/Relative_humidity
 * 
 */
@Entity
@Table(name = "crops")
public class CropType extends NamedEntity implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/** Crop lower bound humidity. */
	private int lower;

	/** Crop upper bound humidity. */
	private int upper;

	/**
	 * Returns relative humidity ideal lower bound as a percentage.
	 * 
	 * @return a value between 0 and 100
	 */
	public int getLower() {
		return lower;
	}

	/**
	 * Sets relative humidity ideal lower bound as a percentage.
	 * 
	 * @param lower
	 *            a value between 0 and 100
	 */
	public void setLower(int lower) {
		assert lower >= 0;
		assert lower <= 100;
		this.lower = lower;
	}

	/**
	 * Returns relative humidity ideal upper bound as a percentage.
	 * 
	 * @return a value between 0 and 100
	 */
	public int getUpper() {
		return upper;
	}

	/**
	 * Set relative humidity ideal upper bound as a percentage.
	 * 
	 * @param upper
	 *            a value between 0 and 100
	 */
	public void setUpper(int upper) {
		assert upper >= 0;
		assert upper <= 100;
		this.upper = upper;
	}

	
	
}
