package cn.sijay.system.service;

import cn.sijay.common.core.entity.SelectOption;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysDictTypeDto;
import cn.sijay.system.entity.SysDictType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysDictTypeService</strong>
 * <p>
 * 字典类型服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysDictTypeService extends IBaseService<SysDictType, SysDictTypeDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysDictTypeDto dto) {
        if (exists(new QueryWrapper<SysDictType>().eq("code", dto.getCode()))) {
            throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "code", dto.getCode());
        }
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysDictType> buildLambdaQueryWrapper(SysDictTypeDto dto) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysDictType::getName, dto.getName());
        wrapper.like(StringUtil.isNotBlank(dto.getCode()), SysDictType::getCode, dto.getCode());
        wrapper.eq(dto.getInternal() != null, SysDictType::getInternal, dto.getInternal());
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

    void syncDict();

    List<SelectOption<String>> toOptions();
}
