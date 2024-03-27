package vdg.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
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
	public FotoPruebaDeVida agregarFotoPruebaDeVida(@PathVariable("idPruebaDeVida") int idPruebaDeVida,
			@RequestBody String foto) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(foto.split(",")[1]);
		FotoPruebaDeVida fotoPruebaDeVida = new FotoPruebaDeVida();
		fotoPruebaDeVida.setIdPruebaDeVida(idPruebaDeVida);
		fotoPruebaDeVida.setFoto(decodedByte);

		return fotoPruebaDeVidaRepo.save(fotoPruebaDeVida);
	}

}
