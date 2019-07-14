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

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 */
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/readings")
class ReadingRestController {

	private static final Logger log = LoggerFactory.getLogger(ReadingRestController.class);

	
	@Autowired
	private ReadingRepository readings;

	@Autowired
	private SensorRepository sensors;	

	@Autowired
	private MessageTask messages;
	
	@PreAuthorize(value = "true")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Reading> addReading(@RequestBody @Valid Reading reading, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (reading == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Reading>(headers, HttpStatus.BAD_REQUEST);
		}
		this.readings.save(reading);
		// Update sensor state, if changed.
		Sensor s = sensors.findById(reading.getSensor());
		log.info("Sensor located {}", s);
		log.info("Sensor state {}", s.isNormal());

		boolean state = s.isNormal();
		s.setNormal(s.getUpper() >= reading.getHumidity()
				&& s.getLower() >= reading.getHumidity());
		if (state != s.isNormal()) {
			// Send notification
			messages.notifySensor(s);
		}
		this.sensors.save(s);
		
		headers.setLocation(ucBuilder.path("/readings/{id}").buildAndExpand(reading.getId()).toUri());
		return new ResponseEntity<Reading>(reading, headers, HttpStatus.CREATED);
	}

}
