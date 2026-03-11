package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import init.service.EstudiantesService;

@RestController
public class EstudiantesController {
	@Autowired
	EstudiantesService estudiantesService;

}
