package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/criar")
    public String mostrarFormularioCriar(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "criarMissao";
    }

    @PostMapping("/criar")
    public String criarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão criada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/atualizar/{id}")
    public String mostrarFormularioAtualizar(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        model.addAttribute("missao", missao);
        return "atualizarMissao";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMissao(@PathVariable Long id, @ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.atualizarMissaoPorId(id, missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão atualizada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        missoesService.deletarMissaoPorId(id);
        redirectAttributes.addFlashAttribute("mensagem", "Missão deletada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String detalhesMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        model.addAttribute("missao", missao);
        return "detalhesMissao";
    }
}
