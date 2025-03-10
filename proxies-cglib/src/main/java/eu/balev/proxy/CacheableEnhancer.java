package eu.balev.proxy;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CacheableEnhancer<T> {

  private final T realObject;
  private final Map<String, Object> caches = new HashMap<>();

  public CacheableEnhancer(T realObject) {
    this.realObject = realObject;
  }

  @SuppressWarnings("unchecked")
  public T enhance() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(realObject.getClass());
    enhancer.setCallback(createInterceptor());
    return (T)enhancer.create();
  }

  private MethodInterceptor createInterceptor() {

    return (obj, method, args, proxy) -> {

      Cacheable cacheable = realObject.getClass().
          getMethod(method.getName(), method.getParameterTypes()).
          getAnnotation(Cacheable.class);

      if (cacheable != null) {
        String cacheId = cacheable.value();
        if (caches.containsKey(cacheId)) {
          return caches.get(cacheId);
        } else {
          Object result = method.invoke(realObject, args);
          caches.put(cacheId, result);
          return result;
        }
      } else {
        return method.invoke(realObject, args);
      }
    };

  }
}
