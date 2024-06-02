package vdg.model.rutinas;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import vdg.model.domain.Ubicacion;


@Component
public class IniciarScript {

	
	@Value("${interprete}")
	String interprete;
	@Value("${path}")
	String path;
	List<ProcessBuilder> procesos; 
	List<Integer> indices;
	
	
	public IniciarScript() {
		this.procesos = new ArrayList<ProcessBuilder>();
		this.indices = new ArrayList<Integer>();
	}
	
	
//	INPUT_LENGTH = 25    # Hiperparámetro
//			OUTPUT_LENGTH = 1    # Modelo uni-step
//			DISTANCIA_PERMITIDA = 3 
//			#nunits
//			#epochs
////			#batch_size
	
	public ProcessBuilder crearProceso(ConfiguracionLSTM config){
		 System.out.println(this.interprete);
		 System.out.println("inicio.py");
		 List<String> command = new ArrayList<>();
	     command.add(this.interprete);
	     command.add("inicio.py");
	     command.add(config.input_length+"");
	     command.add(config.OUTPUT_LENGTH+"");
	     command.add(config.distanciaPermitida+"");
	     command.add(config.nunits+"");
	     command.add(config.epochs+"");
	     command.add(config.batch_size+"");
	     command.add(config.pathDatos+"");
	     ProcessBuilder processBuilder = new ProcessBuilder(command);
	     this.procesos.add(processBuilder);
	     this.indices.add(this.indices.size());
	     return processBuilder;
	}
	
	public void iniciarProceso() throws Exception{
		
	
		if(this.indices.size()==0) throw new Exception("No hay procesos para iniciar, por favor agregue una nuevo identificador");
		
		ProcessBuilder processBuilder = this.procesos.get(this.procesos.size()==1 ? 0 : this.procesos.size()-1 );
		
		processBuilder.redirectOutput(new File("output.log"+LocalDate.now().toString()));
		processBuilder.redirectError(new File("error.log"+LocalDate.now().toString()));
		
		
		 try {
	            Process process = this.procesos.get(this.procesos.size()==1 ? 0 : this.procesos.size()-1 ).start();
	            System.out.println("El script de Python se está ejecutando en segundo plano. La salida se está redirigiendo a output.log.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
	}
	
	
}
