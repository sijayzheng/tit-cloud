package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysPostDto;
import cn.sijay.system.entity.SysPost;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysPostService</strong>
 * <p>
 * 岗位信息服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysPostService extends IBaseService<SysPost, SysPostDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysPostDto dto) {
        if (exists(new QueryWrapper<SysPost>().eq("code", dto.getCode()))) {
            throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "code", dto.getCode());
        }
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysPost>().eq(SysPost::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysPost> buildLambdaQueryWrapper(SysPostDto dto) {
        LambdaQueryWrapper<SysPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysPost::getName, dto.getName());
        wrapper.like(StringUtil.isNotBlank(dto.getCode()), SysPost::getCode, dto.getCode());
        wrapper.eq(dto.getSort() != null, SysPost::getSort, dto.getSort());
        wrapper.orderByAsc(SysPost::getSort);
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
