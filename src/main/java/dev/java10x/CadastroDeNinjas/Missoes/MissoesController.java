package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar todas as missões",
            description = "Retorna uma lista com todas as missões cadastradas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma missão encontrada.")
    })
    public ResponseEntity<?> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        if (missoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma missão encontrada.");
        }
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Listar missão por ID",
            description = "Retorna os dados de uma missão específica com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    public ResponseEntity<?> listarMissoesPorId(
            @Parameter(description = "ID da missão a ser buscada", example = "1")
            @PathVariable Long id) {
        Optional<MissoesDTO> missaoOpt = Optional.ofNullable(missoesService.listarMissoesPorId(id));
        if (missaoOpt.isPresent()) {
            return ResponseEntity.ok(missaoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada.");
        }
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar uma nova missão",
            description = "Cria uma nova missão com os dados fornecidos no corpo da requisição."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    })
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoesDTO);
        return new ResponseEntity<>(novaMissao, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar missão por ID",
            description = "Remove uma missão existente com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    public ResponseEntity<String> deletarMissaoPorId(
            @Parameter(description = "ID da missão a ser deletada", example = "1")
            @PathVariable Long id) {
        try {
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão com ID " + id + " foi deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada.");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(
            summary = "Atualizar missão por ID",
            description = "Atualiza os dados de uma missão específica com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição.")
    })
    public ResponseEntity<?> atualizarMissaoPorId(
            @Parameter(description = "ID da missão a ser atualizada", example = "1")
            @PathVariable Long id,
            @RequestBody MissoesDTO missoesDTO) {
        Optional<MissoesDTO> atualizada = Optional.ofNullable(missoesService.atualizarMissaoPorId(id, missoesDTO));
        if (atualizada.isPresent()) {
            return ResponseEntity.ok(atualizada.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "mensagem", "Missão com ID " + id + " não encontrada.",
                            "status", HttpStatus.NOT_FOUND.value()
                    ));
        }
    }
}
