package br.dev.hebio.biotimeconnector.schedules;

import br.dev.hebio.biotimeconnector.model.colaborador.Colaborador;
import br.dev.hebio.biotimeconnector.model.colaborador.ColaboradorDadosView;
import br.dev.hebio.biotimeconnector.repository.ColaboradorRepository;
import br.dev.hebio.biotimeconnector.service.ColaboradorService;
import br.dev.hebio.biotimeconnector.service.ViewService;
import br.dev.hebio.biotimeconnector.util.HashUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledTasks {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @PostConstruct
    public void importarColaboradoresNaPrimeiraExecucao() {
        if (colaboradorRepository.count() > 0) {
            return;
        }

        List<ColaboradorDadosView> colaboradoresView = viewService.listarColaboradoresAdmitidos();
        for (ColaboradorDadosView dadosView : colaboradoresView) {
            Colaborador colaborador = new Colaborador(dadosView);
            colaborador.setHash(HashUtil.calculateHash(dadosView));
            colaboradorRepository.save(colaborador);
        }
    }

    @Scheduled(fixedRate = 300000) // 300000ms = 5 minutos
    public void verificarEAtualizarDados() {
        List<ColaboradorDadosView> colaboradoresView = viewService.listarColaboradoresAdmitidos();
        for (ColaboradorDadosView dadosView : colaboradoresView) {
            colaboradorService.verificarEAtualizarDados(dadosView);
        }
    }
}