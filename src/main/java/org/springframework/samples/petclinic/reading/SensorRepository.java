package org.springframework.samples.petclinic.reading;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface SensorRepository extends Repository<Sensor, Integer> {

	@Query("SELECT sensor FROM Sensor sensor")
	@Transactional(readOnly = true)
	Collection<Sensor> findTroubled();
	
	Sensor findById(int id);
	
	Collection<Sensor> findAll();

	@Query("SELECT crop FROM Crop crop ORDER BY crop.name")
	@Transactional(readOnly = true)
	Collection<Crop> findCrops();

	void save(Sensor s);
}
