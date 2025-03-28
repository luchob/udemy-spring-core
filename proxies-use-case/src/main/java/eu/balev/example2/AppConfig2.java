package eu.balev.example2;

import java.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class AppConfig2 {

  @Bean
  public CalculatorImpl exampleCalculator() {
    return new CalculatorImpl();
  }

  @Bean
  public CacheManager cacheManager() {

    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(List.of(
        new ConcurrentMapCache("calculateNumber")
    ));

    return cacheManager;
  }
}
