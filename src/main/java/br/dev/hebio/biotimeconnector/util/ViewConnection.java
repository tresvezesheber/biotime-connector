package br.dev.hebio.biotimeconnector.util;

import br.dev.hebio.biotimeconnector.model.colaborador.ColaboradorDadosView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewConnection {

    @Value("${sql.view.host}")
    private String viewHost;

    @Value("${sql.view.database}")
    private String viewDatabase;

    @Value("${sql.view.username}")
    private String viewUsername;

    @Value("${sql.view.password}")
    private String viewPassword;


    public List<ColaboradorDadosView> executeQuery(String query) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = DriverManager.getConnection(viewHost + ";database=" + viewDatabase + ";encrypt=false", viewUsername, viewPassword);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<ColaboradorDadosView> colaboradores = new ArrayList<>();
        while (rs.next()) {
            ColaboradorDadosView colaborador = new ColaboradorDadosView(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("situacao").charAt(0),
                    rs.getTimestamp("dataAdmissao").toLocalDateTime(),
                    rs.getTimestamp("dataDemissao").toLocalDateTime()
            );
            colaboradores.add(colaborador);
        }

        return colaboradores;
    }
}