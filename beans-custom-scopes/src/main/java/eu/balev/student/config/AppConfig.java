package eu.balev.student.config;

import eu.balev.student.util.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

  @Bean
  @Scope("thread")
  public StopWatch stopWatch() {
    return new StopWatch();
  }
}
