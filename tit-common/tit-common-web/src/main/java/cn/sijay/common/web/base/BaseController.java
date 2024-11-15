package cn.sijay.common.web.base;

import cn.sijay.common.core.entity.Res;
import cn.sijay.common.core.enums.ResultCodeEnum;
import cn.sijay.common.mybatis.dictionary.OperateType;
import cn.sijay.common.web.entity.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <strong>BaseController</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 16:36
 */
public class BaseController {

    /**
     * @return 通用返回结果
     */
    protected Res<Void> success() {
        return Res.success();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Res<Void> success(String msg) {
        return Res.success(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> success(T data) {
        return Res.success(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> success(String msg, T data) {
        return Res.success(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    protected Res<Void> error() {
        return Res.failure();
    }

    /**
     * @param msg 信息
     * @return 通用返回结果
     */
    protected Res<Void> error(String msg) {
        return Res.failure(msg);
    }

    /**
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> error(T data) {
        return Res.failure(data);
    }

    /**
     * @param msg  信息
     * @param data 数据
     * @return 通用返回结果
     */
    protected <T> Res<T> error(String msg, T data) {
        return Res.failure(msg, data);
    }

    /**
     * @return 通用返回结果
     */
    protected Res<Boolean> toBoolean(boolean flag) {
        return success(flag ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAILURE.getMsg(), flag);
    }

    /**
     * @return 通用返回结果
     */
    protected Res<Boolean> toBoolean(boolean flag, String msg) {
        msg += flag ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAILURE.getMsg();
        return success(msg + ResultCodeEnum.SUCCESS.getMsg(), flag);
    }

    /**
     * @return 通用返回结果
     */
    protected Res<Boolean> toBoolean(boolean flag, OperateType operateType) {
        String msg = flag ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAILURE.getMsg();
        return success(operateType.getDesc() + msg, flag);
    }

    protected <T> PageResult<T> toPageResult(List<T> list) {
        return PageResult.build(list);
    }

    protected <T> PageResult<T> toPageResult() {
        return PageResult.build();
    }

    protected <T> PageResult<T> toPageResult(IPage<T> page) {
        return PageResult.build(page);
    }
}
