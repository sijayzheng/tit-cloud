package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysConfigDto;
import cn.sijay.system.entity.SysConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysConfigService</strong>
 * <p>
 * 系统配置服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysConfigService extends IBaseService<SysConfig, SysConfigDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysConfigDto dto) {
        if (exists(new QueryWrapper<SysConfig>().eq("code", dto.getCode()))) {
            throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "code", dto.getCode());
        }
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysConfig> buildLambdaQueryWrapper(SysConfigDto dto) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysConfig::getName, dto.getName());
        wrapper.like(StringUtil.isNotBlank(dto.getCode()), SysConfig::getCode, dto.getCode());
        wrapper.like(StringUtil.isNotBlank(dto.getValue()), SysConfig::getValue, dto.getValue());
        wrapper.eq(dto.getSort() != null, SysConfig::getSort, dto.getSort());
        wrapper.orderByAsc(SysConfig::getSort);
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
