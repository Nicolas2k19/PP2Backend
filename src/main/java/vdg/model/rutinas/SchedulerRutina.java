package vdg.model.rutinas;

import java.util.List;
import java.util.TimerTask;

import vdg.controller.IdentificacionRutinasController;
import vdg.model.domain.CoordenadasPersona;
import vdg.model.domain.Persona;
import vdg.model.notificacionesTerceros.TelegramNotificador;
import vdg.repository.ConfigMensajeRepository;

public class SchedulerRutina extends TimerTask {
	
	IdentificacionRutinasController identificacion;
	private  TelegramNotificador telegramNotificador;
	ConfigMensajeRepository repository;
	Persona idPersona;
	
	@Override
	public void run() {
		try {
			System.out.println("Funcionando");
			this.identificacion.identificar(idPersona,telegramNotificador,this.repository);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public IdentificacionRutinasController getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(IdentificacionRutinasController identificacion) {
		this.identificacion = identificacion;
	}

	public Persona getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Persona idPersona) {
		this.idPersona = idPersona;
	}

	public TelegramNotificador getTelegramNotificador() {
		return telegramNotificador;
	}

	public void setTelegramNotificador(TelegramNotificador telegramNotificador) {
		this.telegramNotificador = telegramNotificador;
	}

	public ConfigMensajeRepository getRepository() {
		return repository;
	}

	public void setRepository(ConfigMensajeRepository repository) {
		this.repository = repository;
	}
	
	
}
