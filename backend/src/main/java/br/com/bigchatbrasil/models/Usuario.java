package br.com.bigchatbrasil.models;

import br.com.bigchatbrasil.enums.Papel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Papel papel;
}