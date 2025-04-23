package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;

// Transforma uma classe em uma entidade do BD
// Java Persistence API - JPA
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    // ID por Numeros sequenciais
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    Long id;
    String nome;
    String email;
    int idade;
=======
    private Long id;
    private String nome;
    private String email;
    private int idade;
>>>>>>> 3e655cc (entity class created in database from NinjaModel)

    // @ManyToOne Um ninja tem uma única missao
    @ManyToOne
    // Criar uma nova coluna para a chave estrangeira
    @JoinColumn(name = "missoes_id") // Foreign Key ou chave estrangeira para conectar duas tabelas diferentes
    private MissoesModel missoes;


    // No args constructor
    public NinjaModel() {
    }

    // All args constructor
    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
