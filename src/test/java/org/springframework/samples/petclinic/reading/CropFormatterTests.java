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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.owner.PetType;

/**
 * Test class for {@link CropFormatter}
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CropFormatterTests {

	@Mock
	private SensorRepository sensors;

	private CropFormatter petTypeFormatter;

	@Before
	public void setup() {
		this.petTypeFormatter = new CropFormatter(sensors);
	}

	@Test
	public void testPrint() {
		CropType petType = new CropType();
		petType.setName("Morango");
		String petTypeName = this.petTypeFormatter.print(petType, Locale.ENGLISH);
		assertThat(petTypeName).isEqualTo("Morango");
	}

	@Test
	public void shouldParse() throws ParseException {
		given(this.sensors.findCrops()).willReturn(makePetTypes());
		CropType petType = petTypeFormatter.parse("Morango", Locale.ENGLISH);
		assertThat(petType.getName()).isEqualTo("Morango");
	}

	@Test(expected = ParseException.class)
	public void shouldThrowParseException() throws ParseException {
		given(this.sensors.findCrops()).willReturn(makePetTypes());
		petTypeFormatter.parse("Fish", Locale.ENGLISH);
	}

	/**
	 * Helper method to produce some sample pet types just for test purpose
	 *
	 * @return {@link Collection} of {@link PetType}
	 */
	private List<CropType> makePetTypes() {
		List<CropType> petTypes = new ArrayList<>();
		petTypes.add(new CropType() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				setName("Lavanda");
			}
		});
		petTypes.add(new CropType() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				setName("Morango");
			}
		});
		return petTypes;
	}

}
