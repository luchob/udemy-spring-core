package eu.balev.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheableInvocationHandler implements InvocationHandler {

  private final Object realObject;
  private final Map<String, Object> caches = new HashMap<>();

  public CacheableInvocationHandler(Object realObject) {
    this.realObject = realObject;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
  }
}
