package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Listar todas as missões (com DTO)
    public List<MissoesDTO> listarMissoes() {
        return missoesRepository.findAll()
                .stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // Listar missão por ID (com DTO)
    public MissoesDTO listarMissoesPorId(Long id) {
        MissoesModel missoesModel = missoesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão com ID " + id + " não encontrada."));
        return missoesMapper.map(missoesModel);
    }

    // Criar missão (com DTO)
    public MissoesDTO criarMissao(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        MissoesModel salva = missoesRepository.save(missoesModel);
        return missoesMapper.map(salva);
    }

    // Deletar missão
    public void deletarMissaoPorId(Long id) {
        if (!missoesRepository.existsById(id)) {
            throw new RuntimeException("Missão com ID " + id + " não encontrada.");
        }
        missoesRepository.deleteById(id);
    }

    // Atualizar missão (com DTO)
    public MissoesDTO atualizarMissaoPorId(Long id, MissoesDTO missoesDTO) {
        if (!missoesRepository.existsById(id)) {
            throw new RuntimeException("Missão com ID " + id + " não encontrada.");
        }
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel.setId(id);
        MissoesModel atualizada = missoesRepository.save(missoesModel);
        return missoesMapper.map(atualizada);
    }

}