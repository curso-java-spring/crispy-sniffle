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

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 */
public interface ReadingRepository extends Repository<Reading, Integer> {

	@Query("SELECT reading FROM Reading reading WHERE reading.sensor = :sensor_id")
	@Transactional(readOnly = true)
	Collection<Reading> findBySensor(@Param("sensor_id") int sensor_id);

	@Transactional(readOnly = true)
	Collection<Reading> findAll();

	void save(@Valid Reading reading);

}
