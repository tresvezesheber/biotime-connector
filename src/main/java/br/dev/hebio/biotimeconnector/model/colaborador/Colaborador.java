package br.dev.hebio.biotimeconnector.model.colaborador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;

    private String nome;

    private String cpf;

    private char situacao;

    private LocalDateTime dataAdmissao;

    private LocalDateTime dataDemissao;

    private String hash;

    public Colaborador(ColaboradorDadosView colaboradorDadosView) {
        this.matricula = colaboradorDadosView.matricula();
        this.nome = colaboradorDadosView.nome();
        this.cpf = colaboradorDadosView.cpf();
        this.situacao = colaboradorDadosView.situacao();
        this.dataAdmissao = colaboradorDadosView.dataAdmissao();
        this.dataDemissao = colaboradorDadosView.dataDemissao();
    }
}
