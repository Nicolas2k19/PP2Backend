package vdg.model.rutinas;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import py4j.ClientServer;
import py4j.GatewayServer;
import py4j.Py4JNetworkException;
import vdg.model.domain.ConfiguracionLSTM;
import vdg.model.domain.CoordenadasPersona;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.UbicacionesEntrenamiento;

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

    public ProcessBuilder crearProceso(ConfiguracionLSTM config){
        System.out.println(this.interprete);
        System.out.println("inicio2.py");
        List<String> command = new ArrayList<>();
        command.add(this.interprete);
        command.add("inicio2.py");
        command.add(config.getInput_length()+"");
        command.add(config.getOutput()+"");
        command.add(config.getDistanciaPermitida()+"");
        command.add(config.getNunits()+"");
        command.add(config.getEpochs()+"");
        command.add(config.getBatch_size()+"");
        command.add(config.getPathDatos()+"");
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        this.procesos.add(processBuilder);
        this.indices.add(this.indices.size());
        return processBuilder;
    }

    public void iniciarProceso() throws Exception{
        if(this.indices.size() == 0) throw new Exception("No hay procesos para iniciar, por favor agregue una nuevo identificador");

        ProcessBuilder processBuilder = this.procesos.get(this.procesos.size() == 1 ? 0 : this.procesos.size() - 1);
        processBuilder.redirectOutput(new File("outputNuevo.log" + LocalDate.now().toString()));
        processBuilder.redirectError(new File("error.log" + LocalDate.now().toString()));

        try {
            Process process = processBuilder.start();
            System.out.println("El script de Python se está ejecutando en segundo plano. La salida se está redirigiendo a output.log.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void entrenar(List<UbicacionesEntrenamiento> ubicaciones) {
    	 ClientServer clientServer = new ClientServer(null);
         PythonMethods hello = (PythonMethods) clientServer.getPythonServerEntryPoint(new Class[] { PythonMethods.class });
         try { 
        	 System.out.println("SOY LA LISTA--------------------------------------------------------------------------------------------");
        	 System.out.println(hello.entrenar(ubicaciones));
            
         } catch (Exception e) {
             e.printStackTrace();
         }
         clientServer.shutdown();
  }
    

    public boolean predecir(List<CoordenadasPersona> ubicaciones) {
        ClientServer clientServer = new ClientServer(null);
        PythonMethods modelo = (PythonMethods) clientServer.getPythonServerEntryPoint(new Class[] { PythonMethods.class });
        Boolean resultado = modelo.predecir(ubicaciones);
        clientServer.shutdown();
        return resultado;
    }
}