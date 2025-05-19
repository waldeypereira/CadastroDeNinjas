package dev.java10x.CadastroDeNinjas.Ninjas;

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
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(
            summary = "Endpoint de boas-vindas",
            description = "Retorna uma mensagem simples de boas-vindas."
    )
    @ApiResponse(responseCode = "200", description = "Mensagem de boas-vindas retornada com sucesso.")
    public ResponseEntity<String> boasVindas() {
        return ResponseEntity.ok("Essa é minha primeira mensagem nessa rota!");
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar um novo ninja",
            description = "Cria um novo ninja com os dados fornecidos no corpo da requisição."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    })
    public ResponseEntity<NinjaDTO> criar(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return new ResponseEntity<>(novoNinja, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar todos os ninjas",
            description = "Retorna uma lista com todos os ninjas cadastrados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso.",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum ninja encontrado.")
    })
    public ResponseEntity<?> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        if (ninjas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum ninja encontrado.");
        }
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Listar ninja por ID",
            description = "Retorna os dados de um ninja específico com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "ID do ninja a ser buscado", example = "1")
            @PathVariable Long id) {
        Optional<NinjaDTO> ninjaOpt = ninjaService.listarNinjasPorId(id);
        if (ninjaOpt.isPresent()) {
            return ResponseEntity.ok(ninjaOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum ninja encontrado com o ID " + id);
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar ninja por ID",
            description = "Remove um ninja existente com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<String> deletar(
            @Parameter(description = "ID do ninja a ser deletado", example = "1")
            @PathVariable Long id) {
        boolean deletado = ninjaService.deletarNinjaPorId(id);
        if (deletado) {
            return ResponseEntity.ok("Ninja com ID " + id + " foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado.");
        }
    }

    @PutMapping("atualizar/{id}")
    @Operation(
            summary = "Atualizar ninja por ID",
            description = "Atualiza os dados de um ninja existente com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição.")
    })
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "ID do ninja a ser atualizado", example = "1")
            @PathVariable Long id,
            @RequestBody NinjaDTO ninja) {
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
