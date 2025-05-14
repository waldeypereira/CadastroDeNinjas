package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // Listar todos os meus ninjas

    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }

    // Listar todos os meus ninjas por ID

    public Optional<NinjaModel> listarNinjasPorId(Long id) {
        return ninjaRepository.findById(id);
    }

    // Criar ninja
    public NinjaModel criarNinja(NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }





}
