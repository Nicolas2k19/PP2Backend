package vdg.model.rutinas;

import java.util.List;

import vdg.model.domain.Ubicacion;
import vdg.model.domain.UbicacionesEntrenamiento;

public interface PythonMethods {
		
	 public List<UbicacionesEntrenamiento> entrenar(List<UbicacionesEntrenamiento> ubicaciones);

	 public String predecir();
}



