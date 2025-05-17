package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Listar todos
    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    // Formulário de criação
    @GetMapping("/criar")
    public String mostrarFormularioCriar(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "criarNinja";
    }

    // Submeter criação
    @PostMapping("/criar")
    public String criarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja criado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    // Formulário de atualização
    @GetMapping("/atualizar/{id}")
    public String mostrarFormularioAtualizar(@PathVariable Long id, Model model) {
        Optional<NinjaDTO> ninjaOpt = ninjaService.listarNinjasPorId(id);
        if (ninjaOpt.isPresent()) {
            model.addAttribute("ninja", ninjaOpt.get());
            return "atualizarNinja";
        } else {
            return "redirect:/ninjas/ui/listar?erro=NinjaNaoEncontrado";
        }
    }

    // Submeter atualização
    @PostMapping("/atualizar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.atualizarNinjaPorId(id, ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    // Deletar
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deletado = ninjaService.deletarNinjaPorId(id);
        if (deletado) {
            redirectAttributes.addFlashAttribute("mensagem", "Ninja deletado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Ninja não encontrado.");
        }
        return "redirect:/ninjas/ui/listar";
    }

    // Listar ninja por ID
    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        Optional<NinjaDTO> ninjaOpt = ninjaService.listarNinjasPorId(id);
        if (ninjaOpt.isPresent()) {
            model.addAttribute("ninja", ninjaOpt.get());
            return "detalhesNinja";
        } else {
            return "redirect:/ninjas/ui/listar?erro=NinjaNaoEncontrado";
        }
    }

}
