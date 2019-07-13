package org.springframework.samples.petclinic.reading;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensorController {
	private final SensorRepository sensors;

	public SensorController(SensorRepository sensors) {
		super();
		this.sensors = sensors;
	}

	@GetMapping("/sensors")
	public String processFindForm(Model model) {
		Collection<Sensor> sensors = this.sensors.findAll();
		model.addAttribute("sensors", sensors);

		return "sensors/sensorsList";
	}	
}
