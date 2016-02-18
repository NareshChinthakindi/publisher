/**
 * 
 */
package com.verisk.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Naresh
 *
 */
@Repository
public class SqlServiceTest {

	@Autowired
    @Qualifier("jdbcSlave")
    private JdbcTemplate jdbcTemplate;

    public String getHelloMessage() {
        String firstName = jdbcTemplate.queryForObject("select firstname from employee where oid =1",String.class);
        System.out.println(firstName);
        return "Hello";
    }
}
