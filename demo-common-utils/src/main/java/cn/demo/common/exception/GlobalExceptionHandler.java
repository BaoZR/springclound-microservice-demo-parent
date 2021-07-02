package cn.demo.common.exception;

import cn.demo.common.model.base.BaseCode;
import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.common.util.LocaleUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/20 10:00
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * 统一处理自定义异常BaseException
   *
   * @param e
   * @return
   * @throws Exception
   */
  @ExceptionHandler(value = BaseException.class)
  @ResponseBody
  public ResponseResult jsonErrorHandler(BaseException e) throws Exception {

    return ResponseResult.fail(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseResult IllegalArgumentException(IllegalArgumentException ex) {

    log.error("请求参数异常", ex);
    return ResponseResult.fail(BaseCode.VALID_ERROR.getCode());
  }

  /**
   * 未知异常
   *
   * @param:
   * @return:
   * @author: moc
   * @date: 2019-05-05 13:58
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseResult exceptionErrorHandler(Exception e) throws Exception {

    log.error("========Exception msg: ", e);
    return ResponseResult.fail(BaseCode.FAIL.getCode());
  }

  /**
   * 参数校验统一异常处理
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseResult handleBindException(MethodArgumentNotValidException ex) {

    FieldError fieldError = ex.getBindingResult().getFieldError();
    log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
    return ResponseResult.fail(BaseCode.VALID_ERROR.getCode(), LocaleUtils.getMessage(fieldError.getDefaultMessage()));
  }

//    //运行时异常
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseResult runtimeExceptionHandler(RuntimeException ex) {
//        return ResponseResult.fail(BaseCode.INTERNAL_SERVER_EXCEPTION);
//    }

//    //空指针异常
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseResult nullPointerExceptionHandler(NullPointerException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //类型转换异常
//    @ExceptionHandler(ClassCastException.class)
//    public ResponseResult classCastExceptionHandler(ClassCastException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //IO异常
//    @ExceptionHandler(IOException.class)
//    public ResponseResult iOExceptionHandler(IOException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //未知方法异常
//    @ExceptionHandler(NoSuchMethodException.class)
//    public ResponseResult noSuchMethodExceptionHandler(NoSuchMethodException e) {
//        return ResponseResult.fail(BaseCode.HTTP_METHOD_NOT_SUPPORT.getCode(),e.getMessage());
//    }
//
//    //数组越界异常
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    public ResponseResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //400错误
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public ResponseResult requestNotReadable(HttpMessageNotReadableException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //400错误
//    @ExceptionHandler({TypeMismatchException.class})
//    public ResponseResult requestTypeMismatch(TypeMismatchException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //400错误
//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    public ResponseResult requestMissingServletRequest(MissingServletRequestParameterException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //405错误
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    public ResponseResult request405(HttpRequestMethodNotSupportedException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //406错误
//    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
//    public ResponseResult request406(HttpMediaTypeNotAcceptableException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //500错误
//    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
//    public ResponseResult server500(RuntimeException ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
//
//    //栈溢出
//    @ExceptionHandler({StackOverflowError.class})
//    public ResponseResult requestStackOverflow(StackOverflowError ex) {
//        return ResponseResult.fail(ScheduleStatusConstant.NETWORK_ERROR);
//    }
}
