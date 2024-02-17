package br.dev.hebio.biotimeconnector.util;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                System.out.println(row.getString("Nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
