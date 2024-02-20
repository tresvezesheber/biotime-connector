package br.dev.hebio.biotimeconnector.service;

import br.dev.hebio.biotimeconnector.model.colaborador.Colaborador;
import br.dev.hebio.biotimeconnector.model.colaborador.ColaboradorDadosView;
import br.dev.hebio.biotimeconnector.model.colaborador.SyncStatus;
import br.dev.hebio.biotimeconnector.repository.ColaboradorRepository;
import br.dev.hebio.biotimeconnector.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public void verificarEAtualizarDados(ColaboradorDadosView dadosView) {
        Colaborador colaboradorAtual = colaboradorRepository.findByMatricula(dadosView.matricula());
        String hash = HashUtil.calculateHash(dadosView);

        if (colaboradorAtual == null) {
            // O colaborador não existe no banco de dados local, então criamos um novo colaborador e o salvamos.
            Colaborador novoColaborador = new Colaborador(dadosView);
            novoColaborador.setSyncStatus(SyncStatus.CRIAR);
            novoColaborador.setHash(hash);
            colaboradorRepository.save(novoColaborador);
        } else if (!hash.equals(colaboradorAtual.getHash())) {
            // Os dados do colaborador foram alterados, então atualizamos o colaborador no banco de dados local.
            colaboradorAtual.setMatricula(dadosView.matricula());
            colaboradorAtual.setNome(dadosView.nome());
            colaboradorAtual.setCpf(dadosView.cpf());
            colaboradorAtual.setSituacao(dadosView.situacao());
            colaboradorAtual.setDataAdmissao(dadosView.dataAdmissao());
            colaboradorAtual.setDataDemissao(dadosView.dataDemissao());
            colaboradorAtual.setSyncStatus(SyncStatus.ATUALIZAR);
            colaboradorAtual.setHash(hash);
            colaboradorRepository.save(colaboradorAtual);
        }
    }

    public static String defineAcesso(char colaboradorSituacao) {
        if (!(colaboradorSituacao == 'A')) {
            return "1";
        }
        return "0";
    }
}