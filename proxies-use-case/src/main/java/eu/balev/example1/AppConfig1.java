package eu.balev.example1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AppConfig1 {
  @Bean
  public Example1Bean example1Bean() {
    System.out.println("Creating Example1Bean");
    return new Example1Bean();
  }
}
