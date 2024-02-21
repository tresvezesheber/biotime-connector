package br.dev.hebio.biotimeconnector.util;

import br.dev.hebio.biotimeconnector.model.colaborador.CartaoColaborador;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Component
public class DatabaseConnection {

    @Value("${mdb.db.path}")
    private String dbPath;

    public boolean checkDatabaseFileExists() {
        return Files.exists(Paths.get(dbPath));
    }

    public void readDataFromCartoesTable() {
        try {
            if (!checkDatabaseFileExists()) {
                System.out.println("Database file not found: " + dbPath);
                return;
            }

            Database db = DatabaseBuilder.open(new File(dbPath));
            Table table = db.getTable("Cartoes");

            for (Row row : table) {
                System.out.println(
                        row.getString("Codigo") +
                                " | " +
                                row.getString("Nome") +
                                " | " +
                                row.getString("Mensagem") +
                                " | " +
                                row.getString("Via") +
                                " | " +
                                row.getString("Senha") +
                                " | " +
                                row.getBoolean("JornadaUnica") +
                                " | " +
                                row.getString("Jornada") +
                                " | " +
                                row.getString("Acesso") +
                                " | " +
                                row.getString("NumCartao") +
                                " | " +
                                row.getString("NumRG") +
                                " | " +
                                row.getBoolean("Visitante") +
                                " | " +
                                row.getLocalDateTime("DataInicioValidade") +
                                " | " +
                                row.getLocalDateTime("DataFinalValidade")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeDataToCartoesTable() {
        try {
            if (!checkDatabaseFileExists()) {
                System.out.println("Database file not found: " + dbPath);
                return;
            }

            Database db = DatabaseBuilder.open(new File(dbPath));
            Table table = db.getTable("Cartoes");

            table.addRow(
                    "094552", // Codigo
                    "Heber dos S. S. de Araujo Lima", // Nome
                    "0", // Mensagem
                    "1", // Via
                    "125007", // Senha
                    true, // JornadaUnica
                    "10", // Jornada
                    "0", // Acesso
                    "094552", // NumCartao
                    "121125007", // NumRG
                    false, // Visitante
                    LocalDateTime.now(), // DataInicioValidade
                    LocalDateTime.now().plusDays(30) // DataFinalValidade
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insereDadosNaTabelaCartoes(CartaoColaborador cartaoColaborador) {
        try {
            if (!checkDatabaseFileExists()) {
                System.out.println("Database file not found: " + dbPath);
                return;
            }

            Database db = DatabaseBuilder.open(new File(dbPath));
            Table table = db.getTable("Cartoes");

            table.addRow(
                    cartaoColaborador.codigo(), // Codigo
                    cartaoColaborador.nome(), // Nome
                    cartaoColaborador.mensagem(), // Mensagem
                    cartaoColaborador.via(), // Via
                    cartaoColaborador.senha(), // Senha
                    cartaoColaborador.jornadaUnica(), // JornadaUnica
                    cartaoColaborador.jornada(), // Jornada
                    cartaoColaborador.acesso(), // Acesso
                    cartaoColaborador.numCartao(), // NumCartao
                    cartaoColaborador.numRG(), // NumRG
                    cartaoColaborador.visitante(), // Visitante
                    cartaoColaborador.dataInicioValidade(), // DataInicioValidade
                    cartaoColaborador.dataFimValidade() // DataFinalValidade
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizaDadosNaTabelaCartoes(CartaoColaborador cartaoColaborador) {
        try {
            if (!checkDatabaseFileExists()) {
                System.out.println("Database file not found: " + dbPath);
                return;
            }

            Database db = DatabaseBuilder.open(new File(dbPath));
            Table table = db.getTable("Cartoes");

            for (Row row : table) {
                if (row.getString("Codigo").equals(cartaoColaborador.codigo())) {
                    row.put("Nome", cartaoColaborador.nome());
                    row.put("Mensagem", cartaoColaborador.mensagem());
                    row.put("Via", cartaoColaborador.via());
                    row.put("Senha", cartaoColaborador.senha());
                    row.put("JornadaUnica", cartaoColaborador.jornadaUnica());
                    row.put("Jornada", cartaoColaborador.jornada());
                    row.put("Acesso", cartaoColaborador.acesso());
                    row.put("NumCartao", cartaoColaborador.numCartao());
                    row.put("NumRG", cartaoColaborador.numRG());
                    row.put("Visitante", cartaoColaborador.visitante());
                    row.put("DataInicioValidade", cartaoColaborador.dataInicioValidade());
                    row.put("DataFinalValidade", cartaoColaborador.dataFimValidade());
                    table.updateRow(row);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}