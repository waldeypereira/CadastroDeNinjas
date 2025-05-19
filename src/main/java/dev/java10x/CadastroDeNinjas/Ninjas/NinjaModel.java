package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Transforma uma classe numa entidade do BD
// Java Persistence API - JPA
@Entity
@Table(name = "tb_cadastro")

// Construtores Lombok e Data Get e Setters
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "missoes")
public class NinjaModel {
    /**
     * Unique identifier for the Ninja entity.
     * Automatically generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the ninja.
     */
    @Column(name = "nome")
    private String nome;

    /**
     * Unique email address of the ninja.
     * Must be unique across all ninja records.
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Age of the ninja.
     */
    @Column(name = "idade")
    private int idade;

    /**
     * URL of the ninja's profile image.
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * Rank or classification of the ninja.
     */
    @Column(name = "rank")
    private String rank;

    /**
     * Mission associated with the ninja.
     * Represents a many-to-one relationship between ninjas and missions.
     */
    @ManyToOne
    @JoinColumn(name = "missoes_id")
    private MissoesModel missoes;}
