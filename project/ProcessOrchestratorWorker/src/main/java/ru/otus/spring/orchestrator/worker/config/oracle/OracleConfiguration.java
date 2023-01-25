package ru.otus.spring.orchestrator.worker.config.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.spring.orchestrator.worker.service.oracle.OracleService;
import ru.otus.spring.orchestrator.worker.service.postgres.PostgresService;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:persistence.properties"})
@EnableJpaRepositories(
        basePackages = "ru.otus.spring.orchestrator.worker.service.oracle",
        entityManagerFactoryRef = "oracleEntityManager",
        transactionManagerRef = "oracleTransactionManager")
public class OracleConfiguration {

    @Autowired
    private Environment env;

    public OracleConfiguration() {
        super();
    }

    //

    @Bean
    @Primary
    @Qualifier("oracleEntityManager")
    public LocalContainerEntityManagerFactoryBean oracleEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(oracleDataSource());
        em.setPackagesToScan("ru.otus.spring.orchestrator.worker.service.oracle");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public OracleService oracleService(){
        return new OracleService(oracleEntityManager().createNativeEntityManager(null));
    }

        @Primary
        @Bean
        @ConfigurationProperties(prefix="spring.datasource")
        public DataSource oracleDataSource() {
            return DataSourceBuilder.create().build();
        }

    @Bean
    @Primary
    public PlatformTransactionManager oracleTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(oracleEntityManager().getObject());
        return transactionManager;
    }
        // userEntityManager bean

        // userTransactionManager bean

}
