package org.springframework.samples.petclinic.reading;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface SensorRepository extends Repository<Sensor, Integer> {

	Collection<Sensor> findAll();

	@Query("SELECT crop FROM Crop crop ORDER BY crop.name")
	@Transactional(readOnly = true)
	Collection<Crop> findCrops();

}
