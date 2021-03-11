package tech.przybysz.pms.locationsservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "tech.przybysz.pms.locationsservice")
@EnableTransactionManagement
public class ApplicationConfiguration {
}
