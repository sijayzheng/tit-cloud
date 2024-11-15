package cn.sijay.common.web.entity;

import cn.sijay.common.core.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <strong>PageResult<T></strong>
 * <p>
 * PageResult<T>
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, long total) {
        this.rows = list;
        this.total = total;
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = "查询成功";
    }

    public static <T> PageResult<T> build(List<T> list) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> PageResult<T> build() {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        return rspData;
    }

    public static <T> PageResult<T> build(IPage<T> page) {
        PageResult<T> rspData = new PageResult<>();
        rspData.setCode(ResultCodeEnum.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(Optional.ofNullable(page.getRecords()).orElse(Collections.emptyList()));
        rspData.setTotal(page.getTotal());
        return rspData;
    }
}
