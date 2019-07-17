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

	@Query("SELECT cropType FROM CropType cropType ORDER BY cropType.name")
	@Transactional(readOnly = true)
	Collection<CropType> findCrops();

	void save(Sensor s);
}
