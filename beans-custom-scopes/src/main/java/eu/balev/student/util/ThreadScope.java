package eu.balev.student.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope {

  private ThreadLocal<Map<String, Object>> objectCache =
      ThreadLocal.withInitial(HashMap::new);

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    Object ret = objectCache.get().get(name);
    if (ret == null) {
      ret = objectFactory.getObject();
      objectCache.get().put(name, ret);
    }
    return ret;
  }

  @Override
  public Object remove(String name) {
    return objectCache.get().remove(name);
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {

  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }
}
