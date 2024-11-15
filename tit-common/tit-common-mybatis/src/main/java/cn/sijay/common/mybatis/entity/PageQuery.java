package cn.sijay.common.mybatis.entity;

import cn.sijay.common.core.utils.NumberUtil;
import cn.sijay.common.core.utils.StringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * <strong>PageQuery</strong>
 * <p>
 * PageQuery
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
public class PageQuery implements Serializable {

    /**
     * 当前记录起始索引 默认值
     */
    public static final int DEFAULT_PAGE_NUM = 1;
    /**
     * 每页显示记录数 默认值 默认查全部
     */
    public static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 分页大小
     */
    private Integer size = 10;
    /**
     * 当前页数
     */
    private Integer current = 1;
    /**
     * 排序列
     */
    private List<String> asc;
    private List<String> desc;

    public <T> IPage<T> build() {
        int pageNum = NumberUtil.firstGreatThanZero(Optional.ofNullable(current).orElse(DEFAULT_PAGE_NUM), DEFAULT_PAGE_NUM);
        int pageSize = NumberUtil.firstGreatThanZero(Optional.ofNullable(size).orElse(DEFAULT_PAGE_SIZE), DEFAULT_PAGE_SIZE);
        Page<T> page = new Page<>(pageNum, pageSize);
        if (CollectionUtils.isNotEmpty(asc)) {
            List<OrderItem> orderItems = asc.stream().map(StringUtil::toLowerSnakeCase).map(OrderItem::asc).toList();
            if (CollectionUtils.isNotEmpty(orderItems)) {
                page.addOrder(orderItems);
            }
        }
        if (CollectionUtils.isNotEmpty(desc)) {
            List<OrderItem> orderItems = desc.stream().map(StringUtil::toLowerSnakeCase).map(OrderItem::desc).toList();
            if (CollectionUtils.isNotEmpty(orderItems)) {
                page.addOrder(orderItems);
            }
        }
        return page;
    }
}
