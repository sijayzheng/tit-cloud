package cn.sijay.common.web.handler;

import cn.sijay.common.core.entity.Res;
import cn.sijay.common.core.exception.BaseException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <strong>GlobalExceptionHandler</strong>
 * <p>
 * GlobalExceptionHandler
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Res<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String msg = fieldError != null ? fieldError.getDefaultMessage() : "参数校验异常";
        log.error("参数校验异常:{}", msg);
        return Res.failure(msg);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public Res<Void> handleBaseException(BaseException e) {
        log.error("通用内部异常:{}", e.getMessage());
        return Res.failure(e.getMessage());
    }

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
    public Res<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库中已存在记录'{}'", e.getMessage());
        return Res.failure("数据库中已存在该记录");
    }

    /**
     * Mybatis系统异常 通用处理
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MyBatisSystemException.class)
    public Res<Void> handleCannotFindDataSourceException(MyBatisSystemException e) {
        String message = e.getMessage();
        if ("CannotFindDataSourceException".contains(message)) {
            log.error(" 未找到数据源");
            return Res.failure("未找到数据源");
        }
        log.error(" Mybatis系统异常", e);
        return Res.failure(message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MysqlDataTruncation.class)
    public Res<Void> handleDataTruncation(MysqlDataTruncation e) {
        log.error("数据超长：{}", e.getMessage());
        return Res.failure("数据超长");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Res<String> handleException(Exception e) {
        log.error("捕获到异常:{},异常信息:{}", e.getClass(), e.getMessage());
        return Res.failure(e.getMessage());
    }
}
