package eu.balev.example3;

import org.springframework.stereotype.Component;

@Component
public class BeanA {

  private final BeanB beanB;

  public BeanA(BeanB beanB) {
    System.out.println("Bean B:" + beanB.getClass());
    this.beanB = beanB;
  }
}
