package cz.siemens.inventory.configuration;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
		basePackages = "cz.siemens.inventory.dao"
)
public class PersistenceApplicationContext {

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setJpaVendorAdapter(this.jpaVendorAdapter());
		em.setPackagesToScan("cz.siemens.inventory.entity");

		final Map<String, Object> properties = new HashMap<>();
		properties.put(Environment.GLOBALLY_QUOTED_IDENTIFIERS, true);
		properties.put(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, false);

		return em;
	}
}