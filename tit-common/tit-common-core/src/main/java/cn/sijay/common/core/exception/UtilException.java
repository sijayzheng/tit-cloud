package cn.sijay.common.core.exception;

import cn.sijay.common.core.enums.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.helpers.MessageFormatter;

/**
 * <strong>UtilException</strong>
 * <p>
 * UtilException
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UtilException extends BaseException {
    public UtilException(String msg, Object... args) {
        this(MessageFormatter.arrayFormat(msg, args).getMessage());
    }

    public UtilException(String msg) {
        super(msg);
    }

    public UtilException(ExceptionEnum e) {
        this(e.getMessage());
    }
}
