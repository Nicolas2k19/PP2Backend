package vdg.model.rutinas;

public class ConfiguracionLSTM {

	

		int input_length;
		int	OUTPUT_LENGTH;
		int	distanciaPermitida; 
		int nunits;
		int epochs;
		int batch_size;
		String pathDatos;
		public ConfiguracionLSTM(
				int input_length, 
				int oUTPUT_LENGTH, 
				int distanciaPermitida, 
				int nunits, 
				int epochs,
				int batch_size,
				String pathDatos) {
	
			this.input_length = input_length;
			this.OUTPUT_LENGTH = oUTPUT_LENGTH;
			this.distanciaPermitida = distanciaPermitida;
			this.nunits = nunits;
			this.epochs = epochs;
			this.batch_size = batch_size;
			this.pathDatos = pathDatos;
		}
	
		
		
	
}
