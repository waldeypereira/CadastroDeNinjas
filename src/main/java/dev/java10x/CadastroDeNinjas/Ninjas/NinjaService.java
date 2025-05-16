package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        return ninjaRepository.findAll()
                .stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // Listar ninja por ID
    public Optional<NinjaDTO> listarNinjasPorId(Long id) {
        return ninjaRepository.findById(id)
                .map(ninjaMapper::map);
    }

    // Criar ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        ninjaModel = ninjaRepository.save(ninjaModel);
        return ninjaMapper.map(ninjaModel);
    }

    // Deletar ninja
    public boolean deletarNinjaPorId(Long id) {
        if (!ninjaRepository.existsById(id)) {
            return false;
        }
        ninjaRepository.deleteById(id);
        return true;
    }

    // Atualizar ninja
    public Optional<NinjaDTO> atualizarNinjaPorId(Long id, NinjaDTO ninjaDTO) {
        if (!ninjaRepository.existsById(id)) {
            return Optional.empty();
        }
        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        ninjaModel.setId(id);
        ninjaModel = ninjaRepository.save(ninjaModel);
        return Optional.of(ninjaMapper.map(ninjaModel));
    }
}
