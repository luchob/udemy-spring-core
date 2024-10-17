package eu.balev;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class Main {

  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(Greeter.class);

    enhancer.setCallback((MethodInterceptor) (obj, method, arguments, proxy) -> {
      if ("sayHello".equals(method.getName())) {
        return "Hello Tom!";
      } else {
        return proxy.invokeSuper(obj, args);
      }});

    Greeter greeter = new Greeter();
    System.out.println(greeter.sayHello("Peter"));

  }

}
