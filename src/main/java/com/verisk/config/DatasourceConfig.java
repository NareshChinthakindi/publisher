/**
 * 
 */
package com.verisk.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Naresh
 *
 */
@Configuration
public class DatasourceConfig {

	
	@Bean(name = "dsSlave")
    @ConfigurationProperties(prefix="spring.ds_items")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dsMaster")
    @Primary
    @ConfigurationProperties(prefix="spring.ds_users")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcSlave")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("dsSlave") DataSource dsSlave) {
        return new JdbcTemplate(dsSlave);
    }

    @Bean(name = "jdbcMaster")
    public JdbcTemplate masterJdbcTemplate( @Qualifier("dsMaster") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }
}
