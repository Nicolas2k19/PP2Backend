package vdg.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConfigLSTM")
public class ConfiguracionLSTM {

		@Id
		@Column
		int idConfig;
		@Column
		int input_length;
		@Column
		int	OUTPUT_LENGTH;
		@Column
		int	distanciaPermitida; 
		@Column
		int nunits;
		@Column
		int epochs;
		@Column
		int batch_size;
		@Column
		String pathDatos;
		@Column
		int idPersona;
		public ConfiguracionLSTM(
				int input_length, 
				int oUTPUT_LENGTH, 
				int distanciaPermitida, 
				int nunits, 
				int epochs,
				int batch_size,
				String pathDatos,
				int idPersona) {
			this.input_length = input_length;
			this.OUTPUT_LENGTH = oUTPUT_LENGTH;
			this.distanciaPermitida = distanciaPermitida;
			this.nunits = nunits;
			this.epochs = epochs;
			this.batch_size = batch_size;
			this.pathDatos = pathDatos;
			this.idPersona = idPersona;
		}
		
		
		public int getInput_length() {
			return input_length;
		}
		public void setInput_length(int input_length) {
			this.input_length = input_length;
		}
		public int getOUTPUT_LENGTH() {
			return OUTPUT_LENGTH;
		}
		public void setOUTPUT_LENGTH(int oUTPUT_LENGTH) {
			OUTPUT_LENGTH = oUTPUT_LENGTH;
		}
		public int getDistanciaPermitida() {
			return distanciaPermitida;
		}
		public void setDistanciaPermitida(int distanciaPermitida) {
			this.distanciaPermitida = distanciaPermitida;
		}
		public int getNunits() {
			return nunits;
		}
		public void setNunits(int nunits) {
			this.nunits = nunits;
		}
		public int getEpochs() {
			return epochs;
		}
		public void setEpochs(int epochs) {
			this.epochs = epochs;
		}
		public int getBatch_size() {
			return batch_size;
		}
		public void setBatch_size(int batch_size) {
			this.batch_size = batch_size;
		}
		public String getPathDatos() {
			return pathDatos;
		}
		public void setPathDatos(String pathDatos) {
			this.pathDatos = pathDatos;
		}
		public int getIdPersona() {
			return idPersona;
		}
		public void setIdPersona(int idPersona) {
			this.idPersona = idPersona;
		}
		
		
	
		
		
	
}
