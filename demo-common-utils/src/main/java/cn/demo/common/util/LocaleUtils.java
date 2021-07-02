package cn.demo.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化转换工具
 */
@Component
public class LocaleUtils {

  private static MessageSource messageSource;

  public LocaleUtils(MessageSource messageSource) {

    LocaleUtils.messageSource = messageSource;
  }

  /**
   * 根据key查询值
   */
  public static String getMessage(String msgKey) {

    return getMessage(msgKey, null);
  }

  public static String getMessage(String msgKey, Object[] args) {

    try {
      return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
    } catch (Exception e) {
      return msgKey;
    }
  }

}
