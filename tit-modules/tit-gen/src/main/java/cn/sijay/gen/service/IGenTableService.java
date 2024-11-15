package cn.sijay.gen.service;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.gen.entity.GenTable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <strong>IGenTableService</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
public interface IGenTableService extends IService<GenTable> {
    List<GenTable> listTable();

    IPage<GenTable> page(GenTable entity, PageQuery pageQuery);

    @Transactional(rollbackFor = {Exception.class})
    boolean remove(Id<Long> id);

    @Transactional(rollbackFor = {Exception.class})
    boolean update(GenTable entity);
}
