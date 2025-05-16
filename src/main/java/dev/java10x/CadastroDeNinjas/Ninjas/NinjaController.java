package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public ResponseEntity<String> boasVindas() {
        return ResponseEntity.ok("Essa é minha primeira mensagem nessa rota!");
    }

    // Criar ninja
    @PostMapping("/criar")
    public ResponseEntity<NinjaDTO> criar(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return new ResponseEntity<>(novoNinja, HttpStatus.CREATED);
    }

    // Listar todos os ninjas
    @GetMapping("/listar")
    public ResponseEntity<?> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        if (ninjas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum ninja encontrado.");
        }

        return ResponseEntity.ok(ninjas);
    }



    // Listar ninja por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        Optional<NinjaDTO> ninjaOpt = ninjaService.listarNinjasPorId(id);
        if (ninjaOpt.isPresent()) {
            return ResponseEntity.ok(ninjaOpt.get()); // Retorna JSON automaticamente
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum ninja encontrado com o ID " + id); // Retorna String
        }
    }

    // Deletar ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        boolean deletado = ninjaService.deletarNinjaPorId(id);
        if (deletado) {
            return ResponseEntity.ok("Ninja com ID " + id + " foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado.");
        }
    }

    // Atualizar ninja
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        Optional<NinjaDTO> atualizado = ninjaService.atualizarNinjaPorId(id, ninja);
        if (atualizado.isPresent()) {
            return ResponseEntity.ok(atualizado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "mensagem", "Ninja com ID " + id + " não encontrado.",
                            "status", HttpStatus.NOT_FOUND.value()
                    ));
        }
    }

}
