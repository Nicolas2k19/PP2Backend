package vdg.model.notificacionesTerceros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import vdg.model.domain.Comisaria;
import vdg.repository.ComisariaRepository;


@Component
public class TelegramNotificador extends TelegramLongPollingBot{

	@Autowired
	ComisariaRepository comisariaRepo;
	
	private final String NOHAYCOMANDO = "Por favor, volve a ingresar el comando con un parametro";
	private final String NUEVACOMISARIA = "Se ha agregado con éxito a la comisaria ";
	
	private final String NOHAYCOMISARIA = "La comisaria enviada no existe";
	
	@Override
	public void onUpdateReceived(Update update) {
		 Message msg = update.getMessage();
		 User user = msg.getFrom();
		 
		 if(msg.getText().subSequence(0, 6).equals("/start")) {
				
				if(msg.getText().length()==6) {
					enviarMensaje(user.getId(), this.NOHAYCOMANDO);
					return;
				}
				
				String nombreComisariaIngresadaPorParametro = (String) msg.getText().subSequence(7, msg.getText().length());
				Comisaria comisaria = comisariaRepo.findBynombre(nombreComisariaIngresadaPorParametro.toUpperCase());
				
				System.out.println(nombreComisariaIngresadaPorParametro);
				
				if(comisaria!=null) {
					comisaria.setIdComisariaTelegram(user.getId());
					comisariaRepo.save(comisaria);
					enviarMensaje(user.getId(), this.NUEVACOMISARIA+ comisaria.getNombre());
					return;
				}
				
				enviarMensaje(user.getId(), this.NOHAYCOMISARIA);
			
				
		 }
		
		
	}

	@Override
	public String getBotUsername() {
		return "restriccion_bot";
	}

	@Override
	public String getBotToken() {
		return "6953167933:AAG7RZPkIoIIReGbbRIkaL2RxEVaidcQO5U";
	}

	public void enviarMensaje(Long id,String mensaje) {		
		SendMessage sm = SendMessage.builder()
                .chatId(id.toString()) 
                .text(mensaje).build();    
		try {
		    execute(sm);                        
			} 
		catch (TelegramApiException e) {
		    throw new RuntimeException(e);      
			
		}
	
	}
	
	

}
