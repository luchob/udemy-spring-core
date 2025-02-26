package eu.balev.example3;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BeanB {

  private final BeanA beanA;

  public BeanB(@Lazy BeanA beanA) {
    System.out.println("Bean A:" + beanA.getClass());
    this.beanA = beanA;
  }
}
