package vdg.controller;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.FotoPruebaDeVida;
import vdg.model.domain.FotoPruebaDeVida2;
import vdg.repository.FotoPruebaDeVidaRepository;

@RestController
@RequestMapping("/FotoPruebaDeVida")
@CrossOrigin
public class FotoPruebaDeVidaController {

	@Autowired
	private FotoPruebaDeVidaRepository fotoPruebaDeVidaRepo;

	// ESTE METODO DEVUELVE EL STRING DE LA FOTO CON EL ENCABEZADO PARA INDICAR AL
	// FRONT QUE ES UNA IMAGEN
	@GetMapping("/getFotoPruebaDeVida/{idPruebaDeVida}")
	public FotoPruebaDeVida2 getProbando(@PathVariable("idPruebaDeVida") int idPruebaDeVida) {
		FotoPruebaDeVida foto = fotoPruebaDeVidaRepo.findByIdPruebaDeVida(idPruebaDeVida);
		FotoPruebaDeVida2 foto2 = new FotoPruebaDeVida2();
		if(foto == null)
			return null;
		foto2.setFoto("data:image/png;base64," + Base64.getEncoder().encodeToString(foto.getFoto()));
		foto2.setIdFoto(foto.getIdFoto());
		foto2.setIdPruebaDeVida(idPruebaDeVida);
		return foto2;
	}
	   @PostMapping("/{idPruebaDeVida}")
	    public Map<String, Object> agregarFotoPruebaDeVida(
	            @PathVariable("idPruebaDeVida") int idPruebaDeVida,
	            @RequestBody Map<String, Object> requestBody) {

	        String foto = (String) requestBody.get("foto");
	        String accionRealizada = (String) requestBody.get("accionRealizada");

	        // Decodificar la foto desde Base64
	        byte[] decodedByte = Base64.getDecoder().decode(foto.split(",")[1]);

	        // Crear y guardar la nueva instancia de FotoPruebaDeVida
	        FotoPruebaDeVida fotoPruebaDeVida = new FotoPruebaDeVida();
	        fotoPruebaDeVida.setIdPruebaDeVida(idPruebaDeVida);
	        fotoPruebaDeVida.setFoto(decodedByte);
	        fotoPruebaDeVida.setAccionRealizada(accionRealizada);
	        
	        fotoPruebaDeVidaRepo.save(fotoPruebaDeVida);
	        
	        return validarRostro(idPruebaDeVida);
	    }

	    public Map<String, Object> validarRostro(int idPruebaDeVida) {
	        Map<String, Object> result = new HashMap<>();
	        try {
	            // Obtener la foto de la prueba de vida desde la base de datos
	            FotoPruebaDeVida foto = fotoPruebaDeVidaRepo.findByIdPruebaDeVida(idPruebaDeVida);
	            if (foto == null) {
	                result.put("success", false);
	                result.put("message", "No se encontró la foto de la prueba de vida.");
	                return result;
	            }

	            // Convertir la foto a formato base64 para pasarla como argumento al script de Python
	            String fotoBase64 = Base64.getEncoder().encodeToString(foto.getFoto());

	            // Decodificar la imagen base64 a bytes
	            byte[] fotoBytes = Base64.getDecoder().decode(fotoBase64);

	            // Ruta donde guardar la imagen .png
	            String outputPath = "C:/Users/matia/Desktop/HeadPoseEstimate/input_image.png";

	            // Escribir los bytes decodificados en un archivo .png
	            Path path = Paths.get(outputPath);
	            Files.write(path, fotoBytes);

	            // Ruta del script de Python y la imagen de entrada
	            String scriptPath = "C:/Users/matia/Desktop/HeadPoseEstimate/facial_recognition.py";
	            String folderReference = "C:/Users/matia/Desktop/HeadPoseEstimate/maty";
	            String imagePath = outputPath; // Ruta de la imagen .png guardada

	            // Comando para ejecutar el script de Python con la imagen como argumento
	            String command = "py " + scriptPath + " " + folderReference + " " + imagePath;

	            // Crear un proceso para ejecutar el comando
	            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
	            processBuilder.redirectErrorStream(true); // Redirigir errores al flujo de salida

	            // Iniciar el proceso
	            Process process = processBuilder.start();

	            // Leer la salida del proceso (opcional)
	            InputStream inputStream = process.getInputStream();
	            String output = new BufferedReader(new InputStreamReader(inputStream))
	                                 .lines().collect(Collectors.joining("\n"));

	            System.out.println(output);

	            // Esperar a que el proceso termine
	            int exitCode = process.waitFor();

	            // Comprobar el código de salida del proceso
	            if (exitCode == 0) {
	                if ("True".equalsIgnoreCase(output.trim())) {
	                    result.put("success", true);
	                    result.put("message", "La validación de rostro fue exitosa.");
	                } else {
	                    result.put("success", false);
	                    result.put("message", "La validación de rostro falló.");
	                }
	            } else {
	                result.put("success", false);
	                result.put("message", "Error al ejecutar el script de validación de rostro.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            result.put("success", false);
	            result.put("message", "Excepción en la validación de rostro: " + e.getMessage());
	        }
	        return result;
	    }
	}