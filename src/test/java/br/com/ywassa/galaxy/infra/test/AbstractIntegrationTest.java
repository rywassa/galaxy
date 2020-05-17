package br.com.ywassa.galaxy.infra.test;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Transactional
public abstract class AbstractIntegrationTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = CustomPostgresqlContainer.getInstance();
}