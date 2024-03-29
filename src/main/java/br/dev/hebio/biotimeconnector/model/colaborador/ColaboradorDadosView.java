package br.dev.hebio.biotimeconnector.model.colaborador;

import java.time.LocalDateTime;

public record ColaboradorDadosView(
        String matricula,
        String nome,
        String cpf,
        char situacao,
        LocalDateTime dataAdmissao,
        LocalDateTime dataDemissao
) {
}
