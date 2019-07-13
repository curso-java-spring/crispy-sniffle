package org.springframework.samples.petclinic.reading;

import java.util.Collection;

import org.springframework.data.repository.Repository;

public interface SensorRepository extends Repository<Sensor, Integer> {

	Collection<Sensor> findAll();

}
