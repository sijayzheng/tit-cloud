package cn.sijay.system.service;

import cn.sijay.common.core.entity.SelectOption;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysDictDataDto;
import cn.sijay.system.entity.SysDictData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysDictDataService</strong>
 * <p>
 * 字典数据服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysDictDataService extends IBaseService<SysDictData, SysDictDataDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysDictDataDto dto) {
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysDictData> buildLambdaQueryWrapper(SysDictDataDto dto) {
        LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getTypeCode()), SysDictData::getTypeCode, dto.getTypeCode());
        wrapper.like(StringUtil.isNotBlank(dto.getLabel()), SysDictData::getLabel, dto.getLabel());
        wrapper.like(StringUtil.isNotBlank(dto.getValue()), SysDictData::getValue, dto.getValue());
        wrapper.like(StringUtil.isNotBlank(dto.getCssClass()), SysDictData::getCssClass, dto.getCssClass());
        wrapper.like(dto.getDisplayType() != null, SysDictData::getDisplayType, dto.getDisplayType());
        wrapper.eq(dto.getDefaults() != null, SysDictData::getDefaults, dto.getDefaults());
        wrapper.orderByAsc(SysDictData::getSort);
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

    List<SelectOption<String>> toSelectOption(String typeCode);

    boolean removeByTypeCode(String typeCode);
}
