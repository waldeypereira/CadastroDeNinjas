package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // Listar Missões
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // Listar missão por ID
    @GetMapping("listar/{id}")
    public MissoesDTO listarMissoesPorId(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }

    // Criar missão
    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoesDTO) {
        return missoesService.criarMissao(missoesDTO);
    }

    // Deletar missão
    @DeleteMapping("deletar/{id}")
    public String deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
        return "Missão com ID " + id + " foi deletada com sucesso.";
    }

    // Atualizar missão
    @PutMapping("atualizar/{id}")
    public MissoesDTO atualizarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO) {
        return missoesService.atualizarMissaoPorId(id, missoesDTO);
    }

}