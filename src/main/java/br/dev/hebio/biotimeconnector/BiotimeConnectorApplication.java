package br.dev.hebio.biotimeconnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.dev.hebio.biotimeconnector.util.DatabaseConnection;

@SpringBootApplication
public class BiotimeConnectorApplication implements CommandLineRunner {

	@Autowired
	private DatabaseConnection databaseConnection;

	public static void main(String[] args) {
		SpringApplication.run(BiotimeConnectorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Reading data from Cartoes table...");
		databaseConnection.readDataFromCartoesTable();
	}
}
