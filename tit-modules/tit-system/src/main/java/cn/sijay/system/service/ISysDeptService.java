package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysDeptDto;
import cn.sijay.system.entity.SysDept;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysDeptService</strong>
 * <p>
 * 部门信息服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysDeptService extends IBaseService<SysDept, SysDeptDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysDeptDto dto) {
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysDept>().eq(SysDept::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysDept> buildLambdaQueryWrapper(SysDeptDto dto) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getParentId() != null, SysDept::getParentId, dto.getParentId());
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysDept::getName, dto.getName());
        wrapper.eq(dto.getLeader() != null, SysDept::getLeader, dto.getLeader());
        wrapper.like(StringUtil.isNotBlank(dto.getPhone()), SysDept::getPhone, dto.getPhone());
        wrapper.eq(dto.getSort() != null, SysDept::getSort, dto.getSort());
        wrapper.orderByAsc(SysDept::getSort);
        return wrapper;
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    String importData(MultipartFile file);

    /**
     * 导出
     */
    void export(HttpServletResponse response);
}
