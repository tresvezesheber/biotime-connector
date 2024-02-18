package br.dev.hebio.biotimeconnector.repository;

import br.dev.hebio.biotimeconnector.model.colaborador.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    Colaborador findByMatricula(String matricula);
}
