package ru.otus.spring.orchestrator.worker.config.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import ru.otus.spring.orchestrator.worker.service.postgres.PostgresService;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:persistence.properties"})
@EnableJpaRepositories(
        basePackages = "ru.otus.spring.orchestrator.worker.service.postgres.PostgresService",
        basePackageClasses = ru.otus.spring.orchestrator.worker.service.postgres.PostgresService.class,
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager")
public class PostgresConfiguration {

        @Autowired
        private Environment env;

        public PostgresConfiguration() {
            super();
        }
        @Bean
        public PostgresService postgresService(){
            return new PostgresService(postgresEntityManager().getNativeEntityManagerFactory().createEntityManager());
        }

        @Bean
        @Qualifier("postgresEntityManager")
        public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
            final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(postgresDataSource());
            em.setPackagesToScan("ru.otus.spring.orchestrator.worker.service.postgres");

            final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            final HashMap<String, Object> properties = new HashMap<String, Object>();
            properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
            properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
            em.setJpaPropertyMap(properties);

            return em;
        }

        @Bean
        @ConfigurationProperties(prefix="spring.second-datasource")
        public DataSource postgresDataSource() {
            return DataSourceBuilder.create().build();
        }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        final JdbcTransactionManager jdbcTransactionManager = new JdbcTransactionManager();
        jdbcTransactionManager.setDataSource(postgresDataSource());
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return jdbcTransactionManager;
    }

//    @Bean
//    public TransactionManager postgresTransactionManager2(){
//            final JdbcTransactionManager jdbcTransactionManager = new JdbcTransactionManager(postgresDataSource());
//            return jdbcTransactionManager;
//    }

        // productEntityManager bean

        // productTransactionManager bean

}
