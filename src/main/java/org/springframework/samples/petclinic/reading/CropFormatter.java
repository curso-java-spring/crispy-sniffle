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

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**

 */
@Component
public class CropFormatter implements Formatter<Crop> {

	private final SensorRepository sensors;

	@Autowired
	public CropFormatter(SensorRepository sensors) {
		this.sensors = sensors;
	}

	@Override
	public String print(Crop crop, Locale locale) {
		return crop.getName();
	}

	@Override
	public Crop parse(String text, Locale locale) throws ParseException {
		Collection<Crop> findPetTypes = this.sensors.findCrops();
		for (Crop type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
