package br.dev.hebio.biotimeconnector.service;

import br.dev.hebio.biotimeconnector.util.ViewConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ViewService {

    @Autowired
    private ViewConnection viewConnect;


    public List<?> listarColaboradoresAdmitidos() {
        List<?> result = null;

        try {
            result = viewConnect.executeQuery("SELECT DISTINCT CHAPA AS matricula, name_social AS nome, CPF AS cpf, CODSITUACAO AS situacao, DATAADMISSAO AS dataAdmissao, DATADEMISSAO AS dataDemissao FROM V_GTI_Funcionarios WHERE CODSITUACAO = 'A';");
        } catch (IndexOutOfBoundsException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
