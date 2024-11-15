package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysUserDto;
import cn.sijay.system.entity.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysUserService</strong>
 * <p>
 * 登录用户服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysUserService extends IBaseService<SysUser, SysUserDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysUserDto dto) {
        if (exists(new QueryWrapper<SysUser>().eq("username", dto.getUsername()))) {
            throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "username", dto.getUsername());
        }
        if (exists(new QueryWrapper<SysUser>().eq("phone", dto.getPhone()))) {
            throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "phone", dto.getPhone());
        }
        if (exists(new QueryWrapper<SysUser>().eq("email", dto.getEmail()))) {
            throw new BaseException(ExceptionEnum.VALIDATE_UNIQUE_ERROR, "email", dto.getEmail());
        }
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysUser> buildLambdaQueryWrapper(SysUserDto dto) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getUsername()), SysUser::getUsername, dto.getUsername());
        wrapper.like(StringUtil.isNotBlank(dto.getPhone()), SysUser::getPhone, dto.getPhone());
        wrapper.like(StringUtil.isNotBlank(dto.getEmail()), SysUser::getEmail, dto.getEmail());
        wrapper.like(StringUtil.isNotBlank(dto.getPassword()), SysUser::getPassword, dto.getPassword());
        wrapper.eq(dto.getDeptId() != null, SysUser::getDeptId, dto.getDeptId());
        wrapper.like(dto.getRoleIds() != null, SysUser::getRoleIds, dto.getRoleIds());
        wrapper.like(dto.getPostIds() != null, SysUser::getPostIds, dto.getPostIds());
        wrapper.eq(dto.getEnable() != null, SysUser::getEnable, dto.getEnable());
        wrapper.eq(dto.getSort() != null, SysUser::getSort, dto.getSort());
        wrapper.between(dto.getLastLoginTimeStart() != null && dto.getLastLoginTimeEnd() != null,
                SysUser::getLastLoginTime, dto.getLastLoginTimeStart(), dto.getLastLoginTimeEnd());
        wrapper.orderByAsc(SysUser::getSort);
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
