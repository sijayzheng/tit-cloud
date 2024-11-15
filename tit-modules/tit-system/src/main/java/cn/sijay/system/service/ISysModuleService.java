package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysModuleDto;
import cn.sijay.system.entity.SysModule;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysModuleService</strong>
 * <p>
 * 系统模块服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysModuleService extends IBaseService<SysModule, SysModuleDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysModuleDto dto) {
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysModule>().eq(SysModule::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysModule> buildLambdaQueryWrapper(SysModuleDto dto) {
        LambdaQueryWrapper<SysModule> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysModule::getName, dto.getName());
        wrapper.like(dto.getMenuIds() != null, SysModule::getMenuIds, dto.getMenuIds());
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
