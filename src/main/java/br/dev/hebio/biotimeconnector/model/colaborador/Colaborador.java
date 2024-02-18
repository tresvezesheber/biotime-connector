package br.dev.hebio.biotimeconnector.model.colaborador;

import java.time.LocalDateTime;

public class Colaborador {

    private Long id;

    private String matricula;

    private String nome;

    private String cpf;

    private char situacao;

    private LocalDateTime dataAdmissao;

    private LocalDateTime dataDemissao;
}
