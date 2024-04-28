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
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import vdg.model.domain.Comisaria;
import vdg.model.domain.Juzgado;
import vdg.repository.ComisariaRepository;
import vdg.repository.JuzgadoRepository;


@Component
public class TelegramNotificador extends TelegramLongPollingBot{

	@Autowired
	ComisariaRepository comisariaRepo;
	
	@Autowired
	JuzgadoRepository juzgadoRepository;
	
	private final String NOHAYCOMANDO = "Por favor, volve a ingresar el comando con un parámetro";
	private final String NUEVACOMISARIA = "Se ha agregado con éxito a la comisaria ";
	
	private final String NOHAYCOMISARIA = "La comisaria enviada no existe";
	
	BotSession session;
	
	
	public TelegramNotificador() throws TelegramApiException {
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		this.session = botsApi.registerBot(this);
	}
	
	
	@Override
	public void onUpdateReceived(Update update) {
		 Message msg = update.getMessage();
		 User user = msg.getFrom();
		 
		 if(msg.isCommand()&&msg.getText().subSequence(0, 8).equals("/juzgado")) {
				
				if(msg.getText().length()==8) {
					enviarMensaje(user.getId(), this.NOHAYCOMANDO);
					return;
				}
				String nombreJuzgadoIngresadaPorParametro = (String) msg.getText().subSequence(9, msg.getText().length());
				Juzgado juzgado = this.juzgadoRepository.findBynombre(nombreJuzgadoIngresadaPorParametro);
				System.out.println(nombreJuzgadoIngresadaPorParametro);
				if(juzgado!=null) {
					juzgado.setIdJuzgadoTelegram(user.getId());
					this.juzgadoRepository.save(juzgado);
					System.out.println("ESTOY");
					enviarMensaje(user.getId(),"Se ha agregado un nuevo juzgado "+ juzgado.getNombre());
					return;
				}
				enviarMensaje(user.getId(), "El juzgado no existe");
				return;
				
		 }
		 
		 
		 
		 
		 
		 		 		 
		 if(msg.isCommand()&&msg.getText().subSequence(0, 6).equals("/start")) {
				
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
					System.out.println("ESTOY");
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
