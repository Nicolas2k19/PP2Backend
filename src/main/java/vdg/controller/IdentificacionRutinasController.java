package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Ubicacion;
import vdg.model.rutinas.ConfiguracionLSTM;
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

	   @PostMapping("crearIdentificador")
	    public ResponseEntity<Void> crearIdentificadorRutinas() throws Exception {
		  	this.iniciadorScript.crearProceso(crearConfig(25,1,3,128,80,256,"./datos/route_2022-01-31_6.27pm.gpx"));
		  	this.iniciadorScript.iniciarProceso();
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   
	   
	   @GetMapping("identificarRutina")
	    public ResponseEntity<Void> identificar() throws Exception {
	        List<Ubicacion> ubicaciones = this.ubicacion.findAllByIdPersona(1);
		  	this.iniciadorScript.predecir(ubicaciones );
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   
	   
	   private ConfiguracionLSTM crearConfig(int input_length, 
				int oUTPUT_LENGTH, 
				int distanciaPermitida, 
				int nunits, 
				int epochs,
				int batch_size,
				String pathDatos) {
		
		   return new ConfiguracionLSTM(input_length,oUTPUT_LENGTH,distanciaPermitida,nunits,epochs,batch_size,pathDatos);
	   }
	   
	   
	   private String pasarString(List<Ubicacion> ubicaciones) {
		   
		   String ubicacionesAString = "[";

		   for(Ubicacion ubicacion : ubicaciones) {
			   ubicacionesAString+=ubicacion.toString()+",";
		   }
		   return ubicacionesAString;
		   
	   }
	
	
}
