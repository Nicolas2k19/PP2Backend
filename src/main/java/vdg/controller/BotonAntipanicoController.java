package vdg.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.spring.web.json.Json;
import vdg.model.domain.BotonAntipanico;
import vdg.model.domain.Contacto;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;
import vdg.model.email.EmailGateway;
import vdg.model.notificacionesTerceros.CuerpoMensaje;
import vdg.model.notificacionesTerceros.CuerpoNotificacion;
import vdg.model.notificacionesTerceros.WpNotificador;
import vdg.repository.BotonAntipanicoRepository;
import vdg.repository.ContactoRepository;
//import vdg.repository.NotificacionRepository;

@RestController
@RequestMapping("/BotonAntipanico")
@CrossOrigin
public class BotonAntipanicoController {

	@Autowired
	private BotonAntipanicoRepository botonAntipanicoRepo;
	@Autowired
	private ContactoRepository contactoRepo;
	@Autowired
	private UsuarioController usuarioController;
	@Autowired
	private PersonaController personaController;
	
	@Autowired
	private WpNotificador  wpNotificador;
	
	@Value("${servicioMensajeria}")
    private String mensajeria;
	
	@Value("${preview_url}")
    private Boolean preview_url;
	@Value("${recipient_type}")
	private String recipient_type;
	
	
	//@Autowired
	//private NotificacionRepository notificacionRepo;
	
	@PostMapping("/{emailDamnificada}")
	public BotonAntipanico alertar(@RequestBody BotonAntipanico botonAntipanico, @PathVariable("emailDamnificada") String emailDamnificada) {
		
		//TRAIGO LOS OBJETOS NECESARIOS
		Usuario usuario = usuarioController.findByEmail(emailDamnificada);
		Persona damnificada = personaController.getByIdUsuario(usuario.getIdUsuario());
		//GENERO TIMESTAMP Y LO SETEO
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		botonAntipanico.setFecha(ahoraStamp);
		
		botonAntipanico.setIdDamnificada(damnificada.getIdPersona());
		//ENVIO ALERTAS A LOS CONTACTOS DE LA DAMNIFICADA
		enviarMail(damnificada, botonAntipanico);
		return botonAntipanicoRepo.save(botonAntipanico);
	}
	
	
	@PostMapping("/alertarPolicia")
	/**Envia un mensaje a la api de Whatsapp
	 *@Returns CuerpoNotificacion
	 ***/
	public String alertarPolicia() throws Exception {
		return this.wpNotificador.notificar(configurarCuerpo());
	}

	/**Configura el cuerpo a enviar a la api de Whatsapp
	 *@Returns CuerpoNotificacion
	 ***/
	private CuerpoNotificacion configurarCuerpo() throws Exception {
		CuerpoNotificacion cuerpo  = new CuerpoNotificacion();
		CuerpoMensaje cuerpoMensaje = new CuerpoMensaje();
		cuerpo.setMessaging_product(this.mensajeria);
		cuerpo.setTo("541166375768");
		cuerpo.setPreview_url(this.preview_url);
		cuerpo.setRecipient_type(this.recipient_type);
		cuerpoMensaje.setBody("Este es mi primer mensaje desde java");
		cuerpo.setCuerpoMensaje(cuerpoMensaje);
		return cuerpo;
	}
	
	
	
	public void enviarMail(Persona damnificada, BotonAntipanico botonAntipanico) {
		
		String asunto = "Botón antipánico activado";
		
		String mensaje = damnificada.getApellido() + ", " + damnificada.getNombre() + 
				" activó el botón antipánico en lat: " + botonAntipanico.getLatitud() +
				", long: " + botonAntipanico.getLongitud() + ". A las: " + botonAntipanico.getFecha();

		for(Contacto contacto: contactoRepo.findByIdDamnificada(damnificada.getIdPersona())) {
			EmailGateway.enviarMail(contacto.getEmail(), mensaje, asunto);			
		}
	}
	
	public void generarInicidencia(Persona damnificada, BotonAntipanico botonAntipanico) {
		//GENERO INCIDENCIA PERO TODAVIA NO LA GUARDO
		Incidencia incidencia = new Incidencia();
		incidencia.setFecha(botonAntipanico.getFecha());
		//GENERO NOTIFICACION Y LA GUARDO PARA QUE LA VEA EL USER
//		Notificacion notificacion = GeneradorNotificaciones.generarNotificacion(incidencia, restriccion, damnificada, victimario, usuario);
//		notificacionRepo.save(notificacion);
		
	}
	
	

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public HttpHeaders HttpHeaders() {
		HttpHeaders header = new HttpHeaders();
	    return header ;
	}
	
	
	
}
