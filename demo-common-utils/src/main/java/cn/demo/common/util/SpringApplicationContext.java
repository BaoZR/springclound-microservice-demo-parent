package cn.demo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/22 14:25
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    if (SpringApplicationContext.applicationContext == null) {
      SpringApplicationContext.applicationContext = applicationContext;
    }
  }

  public static ApplicationContext getApplicationContext() {

    return applicationContext;
  }

  public static Object getBean(String name) {

    return getApplicationContext().getBean(name);
  }

  public static <T> T getBean(Class<T> clazz) {

    return getApplicationContext().getBean(clazz);
  }

}
