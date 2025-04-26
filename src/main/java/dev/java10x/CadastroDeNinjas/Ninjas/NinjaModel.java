package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Transforma uma classe em uma entidade do BD
// Java Persistence API - JPA
@Entity
@Table(name = "tb_cadastro")

// Construtores Lombok e Data Get e Setters
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {

    @Id
    // ID por Numeros sequenciais
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "nome")
    private String nome;

    // Nao pode ter dados repetidos anottation column


    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "idade")
    private int idade;

    @Column(name = "img_url")
    private String imgUrl;

    // @ManyToOne Um ninja tem uma única missao
    @ManyToOne
    // Criar uma nova coluna para a chave estrangeira
    @JoinColumn(name = "missoes_id") // Foreign Key ou chave estrangeira para conectar duas tabelas diferentes
    private MissoesModel missoes;
}
