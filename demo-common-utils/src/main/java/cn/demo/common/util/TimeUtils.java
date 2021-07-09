package cn.demo.common.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-11 13:57
 */
@Slf4j
public class TimeUtils {

  public static final DateTimeFormatter formatterDD = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss");
  public static final DateTimeFormatter formatterDD1 = DateTimeFormatter
      .ofPattern("yyyyMMddHHmmss");
  public static final DateTimeFormatter formatterDD2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter formatterDD3 = DateTimeFormatter.ofPattern("yyyyMMdd");
  public static final DateTimeFormatter formatterDD4 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
  public static final DateTimeFormatter formatterDD5 = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm");
  public static final DateTimeFormatter formatterDD6 = DateTimeFormatter.ofPattern("HHmm");
  public static final DateTimeFormatter formatterDD7 = DateTimeFormatter.ofPattern("HH:mm");
  public static final DateTimeFormatter formatterDD8 = DateTimeFormatter
      .ofPattern("EEE, d MMM yyyy HH:mm:ss");
  public static final DateTimeFormatter formatterDD9 = DateTimeFormatter.ofPattern("M月d日");
  public static final DateTimeFormatter formatterDD10 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
  public static final DateTimeFormatter formatterDD11 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
  public static final DateTimeFormatter formatterDD12 = DateTimeFormatter.ofPattern("HHmmss");
  public static final DateTimeFormatter formatterDD13 = DateTimeFormatter.ofPattern("Hmmss");
  public static final DateTimeFormatter formatterDD14 = DateTimeFormatter.ofPattern("Hmm");
  public static final DateTimeFormatter formatterDD15 = DateTimeFormatter
      .ofPattern("yyyyMMdd Hmmss");


  public static LocalDateTime date2LocalDateTime(Date date) {
    //new Date();
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  public static String formatLocalDateTime(LocalDateTime date, DateTimeFormatter formatter) {

    try {
      return formatter.format(date);
    } catch (Exception e) {
      log.error("format Exception", e);
      return null;
    }
  }

  public static Long localDateTime2Second(LocalDateTime date) {

    return date.toEpochSecond(ZoneOffset.of("+8"));
  }

  public static Long localDateTime2MilliSecond(LocalDateTime date) {

    return date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
  }

  public static String date2String(Date date, DateTimeFormatter formatter) {

    if (date == null) {
      return "";
    } else {
      try {
        LocalDateTime dateTime = date2LocalDateTime(date);
        return formatLocalDateTime(dateTime, formatter);
      } catch (Exception var3) {
        log.error("TimeUtils | date2String : 解析时间出错", var3);
        return "";
      }
    }
  }

  public static String localDateTime2String(LocalDateTime dateTime, DateTimeFormatter formatter) {

    if (dateTime == null) {
      return "";
    } else {
      try {
        return dateTime.format(formatter);
      } catch (Exception var3) {
        log.error("TimeUtils | localDateTime2String : 解析时间出错", var3);
        return "";
      }
    }
  }

  public static String localDateT2String(LocalDate dateTime, DateTimeFormatter formatter) {

    if (dateTime == null) {
      return "";
    } else {
      try {
        return dateTime.format(formatter);
      } catch (Exception var3) {
        log.error("TimeUtils | localDateTime2String : 解析时间出错", var3);
        return "";
      }
    }
  }

  public static Date String2Date(String dateStr) {

    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    LocalDateTime localDateTime = parseLocalDateTime(dateStr, formatterDD);
    return convertToDate(localDateTime);
  }

  public static String DateString2DateString(String date,
                                             DateTimeFormatter originalDateTimeFormatter, DateTimeFormatter afterDateTimeFormatter) {

    Date date1 = string2Date(date, originalDateTimeFormatter);
    return date2String(date1, afterDateTimeFormatter);
  }

  //只解析日期
  public static Date string2Date(String dateStr, DateTimeFormatter dateTimeFormatter) {

    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
    return convertToDate(localDate);
  }

  public static LocalDate string2LocalDate(String dateStr, DateTimeFormatter dateTimeFormatter) {

    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    return LocalDate.parse(dateStr, dateTimeFormatter);
  }

  public static LocalTime string2LocalTime(String timeStr, DateTimeFormatter dateTimeFormatter) {

    if (StringUtils.isBlank(timeStr)) {
      return null;
    }
    return LocalTime.parse(timeStr, dateTimeFormatter);
  }

  public static LocalDateTime string2LocalDateTime(String dateStr, DateTimeFormatter dateTimeFormatter) {

    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    return LocalDateTime.parse(dateStr, dateTimeFormatter);
  }

  public static LocalDate dateToLocalDate(Date date) {

    Instant instant = date.toInstant();
    ZoneId zone = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    LocalDate localDate = localDateTime.toLocalDate();

    return localDate;
  }

  public static Date localDateToDate(LocalDate localDate) {

    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
    Date date = Date.from(zdt.toInstant());

    return date;
  }

  public static Date localDateTime2Date(LocalDateTime localDateTime) {

    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zdt = localDateTime.atZone(zoneId);
    Date date = Date.from(zdt.toInstant());
    return date;
  }


  public static Date convertToDate(LocalDate source) {

    return Date.from(source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDateTime parseLocalDateTime(String dateStr, DateTimeFormatter formatter) {

    try {
      return LocalDateTime.parse(dateStr, formatter);
    } catch (Exception e) {
      log.error("parse Exception, dateStr:{}", dateStr, e);
      return null;
    }
  }

  public static Date convertToDate(LocalDateTime source) {

    return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date currentTimeAddMin(int min) {

    LocalDateTime time = LocalDateTime.now();
    LocalDateTime newTime = time.plusMinutes(min);
    Instant instant = newTime.atZone(ZoneId.systemDefault()).toInstant();
    return Date.from(instant);
  }

  public static Date timeAddMin(Long timemills, int min) {

    Instant instant = Instant.ofEpochMilli(timemills);
    LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    LocalDateTime dateAdded = date.plusMinutes(min);
    return Date.from(dateAdded.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date timeAddHour(Long timemills, int hour) {

    Instant instant = Instant.ofEpochMilli(timemills);
    LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    LocalDateTime dateAdded = date.plusHours(hour);
    return Date.from(dateAdded.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static long getDateDiff(Date startDate, Date endDate) {

    return (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
  }

  public static String getGMT(Date d) {

    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    return sdf.format(d);
  }

  public static String normalizeSegmentTime(String time) {

    time = time.replace(":", "");
    time = time.substring(0, 2) + ":" + time.substring(2);
    return time;
  }

  public static Date addDays(Date date, Integer days) {

    if (date == null) {
      return null;
    }
    try {
      Instant instant = date.toInstant();
      ZoneId zone = ZoneId.systemDefault();
      LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
      LocalDate localDate = localDateTime.toLocalDate();
      LocalDate result = localDate.plusDays(days);

      Instant instantDate = result.atStartOfDay().atZone(zone).toInstant();
      Date dateResult = Date.from(instantDate);

      return dateResult;
    } catch (Exception e) {
      log.error("日期转换异常:", e);
      return null;
    }
  }

  public static String localDateToStr(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {

    return localDate.format(dateTimeFormatter);
  }


  public static Date dateFormat(Date date) {

    try {
      SimpleDateFormat df = new SimpleDateFormat("HH:mm");
      return df.parse(df.format(new Date()));
    } catch (Exception e) {
      return null;
    }
  }


  public static boolean isSomeTimeInterval(LocalTime nowTime, LocalTime beginTime, LocalTime endTime) {

    return nowTime.isAfter(beginTime) && nowTime.isBefore(endTime) ? true : false;
  }

  public static String getFirstDayOfMonth(String curDate) {

    LocalDate localDate = string2LocalDate(curDate, formatterDD2);
    LocalDate firstday = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    return localDateT2String(firstday, formatterDD2);
  }

  public static String getLastDayOfMonth(String curDate) {

    LocalDate localDate = string2LocalDate(curDate, formatterDD2);
    LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
    return localDateT2String(lastDay, formatterDD2);
  }

  public static void main(String[] args) {

    LocalTime beginTime = LocalTime.of(9, 15);
    LocalTime endTime = LocalTime.of(11, 31);

    System.out.println(isSomeTimeInterval(LocalTime.now(), beginTime, endTime));
  }

}
