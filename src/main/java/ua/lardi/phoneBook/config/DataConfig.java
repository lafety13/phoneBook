package ua.lardi.phoneBook.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

  @Bean
  @Profile("default")
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/phonebook");
    dataSource.setUsername("root");
    dataSource.setPassword("");
    dataSource.setInitialSize(10);
    dataSource.setMaxTotal(70);
    dataSource.setMaxIdle(30);
    return dataSource;
  }

  @Bean
  @Profile("mysql")
  public DataSource emptyDataSource() {
    return new BasicDataSource();
  }

}
