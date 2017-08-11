package ua.lardi.phoneBook.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

  @Bean
  @Profile("mysql")
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl(System.getProperty("url"));
    dataSource.setUsername(System.getProperty("username"));
    dataSource.setPassword(System.getProperty("password"));
    dataSource.setInitialSize(10);
    dataSource.setMaxTotal(70);
    dataSource.setMaxIdle(30);
    return dataSource;
  }

  @Bean
  @Profile("default")
  public DataSource emptyDataSource() {
    return new BasicDataSource();
  }

}
