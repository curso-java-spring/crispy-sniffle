package org.springframework.samples.petclinic.reading;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageTask {

	private static final int INTERVAL_IN_MINUTES = 5;

	private static final Logger log = LoggerFactory.getLogger(MessageTask.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private SensorRepository sensors;

	//Scheduled(fixedRate = INTERVAL_IN_MINUTES * 1000)
	public void reportCurrentTime() {
		Collection<Sensor> troubled = sensors.findAll();
		for (Sensor s : troubled) {
			log.info("The time is now {}", dateFormat.format(new Date()));
			log.info("Reading is {}", s);			
		}

		// WHAT
		// select readings bellow or above sensor
		// last n readings?
		// sensors silent for too long?

		// WHO
		// where to send notification?

		// WHERE
		// log notifications?
		// notifications table?

		// HOW
		// Firebase
	}
	
	public void notifySensor(Sensor sensor) {
		log.info("Sending notification {}", dateFormat.format(new Date()));
		log.info("Sensor {}", sensor);
	}
}
