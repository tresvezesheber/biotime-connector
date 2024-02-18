package br.dev.hebio.biotimeconnector;

import br.dev.hebio.biotimeconnector.service.ViewService;
import br.dev.hebio.biotimeconnector.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BiotimeConnectorApplication implements CommandLineRunner {

    @Autowired
    private DatabaseConnection databaseConnection;

    @Autowired
    private ViewService viewService;

    public static void main(String[] args) {
        SpringApplication.run(BiotimeConnectorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Reading data from Cartoes table...");
        databaseConnection.readDataFromCartoesTable();
        System.out.println("Reading data from Colaboradores view...");
        System.out.println(viewService.listarColaboradoresAdmitidos());
    }
}
