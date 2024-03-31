package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Comisaria;
import vdg.repository.ComisariaRepository;

@RestController
@RequestMapping("/Comisaria")
@CrossOrigin
public class ComisariaController {
	
	@Autowired
	ComisariaRepository comisariaRepository;
	
	@PostMapping("nuevaComisaria")
	public int agregar(@RequestBody Comisaria comisaria) {
		try {
			comisariaRepository.save(comisaria);
			return 200;
		}
		catch(Exception e){
			return 400;
		}
		
	}
	
	@GetMapping("ObtenerComisarias")
	public List<Comisaria> agregar() {
		return comisariaRepository.findAll();
		
	}
	
	
	
	
}
