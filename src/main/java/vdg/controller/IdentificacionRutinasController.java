package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.ConfiguracionLSTM;
import vdg.model.domain.Ubicacion;
import vdg.model.notificacionesTerceros.TelegramNotificador;
import vdg.model.rutinas.IniciarScript;
import vdg.repository.UbicacionRepository;



@RestController
@RequestMapping("/IdentificacionRutinas")
@CrossOrigin
public class IdentificacionRutinasController {
	
	
	  @Autowired
	  IniciarScript iniciadorScript;
	  
	  @Autowired
	  UbicacionRepository ubicacion;
	  
	  @Autowired
	   private  TelegramNotificador telegramNotificador;

	   @PostMapping("crearIdentificador")
	    public ResponseEntity<Void> crearIdentificadorRutinas(@RequestBody ConfiguracionLSTM config) throws Exception {
		  	this.iniciadorScript.crearProceso(config);
		  	this.iniciadorScript.iniciarProceso();
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   
	   
	   @GetMapping("identificarRutina")
	    public ResponseEntity<Void> identificar(int idPersona) throws Exception {
	        List<Ubicacion> ubicaciones = this.ubicacion.findAllByIdPersona(idPersona);
		  	if(this.iniciadorScript.predecir(ubicaciones )) {
		  		telegramNotificador.enviarMensaje((long) 770684292, "Alerta el agresor a abandonado su rutina normal");
		  		
		  	}
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   
	  
	   
	   
	   private String pasarString(List<Ubicacion> ubicaciones) {
		   
		   String ubicacionesAString = "[";

		   for(Ubicacion ubicacion : ubicaciones) {
			   ubicacionesAString+=ubicacion.toString()+",";
		   }
		   return ubicacionesAString;
		   
	   }
	
	
}
