package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Juzgado;
import vdg.repository.JuzgadoRepository;


@RestController
@RequestMapping("/Juzgado")
@CrossOrigin
public class JuzgadoController {
	
	@Autowired
	JuzgadoRepository juzgadoRepository;
	
	@PostMapping("nuevaComisaria")
	public int agregar(@RequestBody Juzgado juzgado) {
		try {
			juzgadoRepository.save(juzgado);
			return 200;
		}
		catch(Exception e){
			return 400;
		}
		
	}
	
	@GetMapping("ObtenerJuzgados")
	public List<Juzgado> agregar() {
		return juzgadoRepository.findAll();
		
	}
	
	
	
	
}
