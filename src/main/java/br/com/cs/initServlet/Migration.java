package br.com.cs.initServlet;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class Migration {
	private static Logger logger = Logger.getLogger(Migration.class);

	public static void migrate() throws FlywayException {
		logger.info("Iniciando migração-------------------------------");

		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:mysql://localhost/db_controle_servico",	"root", "myh0m0l0gsql");

		flyway.migrate();

		logger.info("Migração concluída----------------------------");
	}

}