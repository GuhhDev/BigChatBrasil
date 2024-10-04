package br.com.bigchatbrasil.dtos;

import br.com.bigchatbrasil.enums.Plano;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlterarPlanoRequestDTO {

    private Long id;

    @NotNull(message = "O plano não pode ser nulo.")
    private Plano plano;
}
