package vdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vdg.model.domain.PruebaDeVidaMultiple;
import vdg.repository.PruebaDeVidaMultipleRepository;

import java.util.Optional;

@RestController
@RequestMapping("/PruebaDeVidaMultiple")
public class PruebaDeVidaMultipleController {

    @Autowired
    private PruebaDeVidaMultipleRepository pruebaDeVidaMultipleRepository;

    @PostMapping
    public ResponseEntity<?> guardarPruebasDeVidaMultiple(@RequestBody PruebaDeVidaMultiple pruebaDeVidaMultiple) {
        try {
            return ResponseEntity.ok(pruebaDeVidaMultipleRepository.save(pruebaDeVidaMultiple));
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar las pruebas de vida múltiples: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPruebaDeVidaMultipleById(@PathVariable("id") Long id) {
        Optional<PruebaDeVidaMultiple> pruebaDeVidaOptional = pruebaDeVidaMultipleRepository.findById(id);
        if (pruebaDeVidaOptional.isPresent()) {
            PruebaDeVidaMultiple pruebaDeVida = pruebaDeVidaOptional.get();
            return ResponseEntity.ok(pruebaDeVida);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPruebaDeVidaMultiple(@PathVariable("id") Long id, @RequestBody PruebaDeVidaMultiple pruebaDeVidaMultiple) {
        try {
            pruebaDeVidaMultiple.setIdPruebaDeVidaMultiple(id);
            PruebaDeVidaMultiple updatedPrueba = pruebaDeVidaMultipleRepository.save(pruebaDeVidaMultiple);
            return ResponseEntity.ok(updatedPrueba);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la prueba de vida múltiple: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPruebaDeVidaMultiple(@PathVariable("id") Long id) {
        try {
            pruebaDeVidaMultipleRepository.deleteById(id);
            return ResponseEntity.ok("Prueba de vida múltiple eliminada correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la prueba de vida múltiple: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
