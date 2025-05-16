package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota!";
    }

    // Criar ninja (Create)
    @PostMapping("/criar")
    public NinjaDTO criar(@RequestBody NinjaDTO ninja) {
        return ninjaService.criarNinja(ninja);
    }

    // Listar todos os ninjas (Read All)
    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Listar ninja por ID (Read One)
    @GetMapping("/listar/{id}")
    public Optional<NinjaDTO> listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    // Deletar ninja (Delete)
    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "Ninja com ID " + id + " foi deletado com sucesso.";
    }

    // Atualizar ninja (Update)
    @PutMapping("/atualizar/{id}")
    public NinjaDTO atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        return ninjaService.atualizarNinjaPorId(id, ninja);
    }
}
