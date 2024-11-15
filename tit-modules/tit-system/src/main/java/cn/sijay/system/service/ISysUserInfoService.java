package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysUserInfoDto;
import cn.sijay.system.entity.SysUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysUserInfoService</strong>
 * <p>
 * 用户信息服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysUserInfoService extends IBaseService<SysUserInfo, SysUserInfoDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysUserInfoDto dto) {
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysUserInfo>().eq(SysUserInfo::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysUserInfo> buildLambdaQueryWrapper(SysUserInfoDto dto) {
        LambdaQueryWrapper<SysUserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getUserId() != null, SysUserInfo::getUserId, dto.getUserId());
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysUserInfo::getName, dto.getName());
        wrapper.like(StringUtil.isNotBlank(dto.getGender()), SysUserInfo::getGender, dto.getGender());
        wrapper.between(dto.getBirthdayStart() != null && dto.getBirthdayEnd() != null,
                SysUserInfo::getBirthday, dto.getBirthdayStart(), dto.getBirthdayEnd());
        wrapper.like(StringUtil.isNotBlank(dto.getAvatar()), SysUserInfo::getAvatar, dto.getAvatar());
        wrapper.eq(dto.getProvince() != null, SysUserInfo::getProvince, dto.getProvince());
        wrapper.eq(dto.getCity() != null, SysUserInfo::getCity, dto.getCity());
        wrapper.eq(dto.getArea() != null, SysUserInfo::getArea, dto.getArea());
        wrapper.like(StringUtil.isNotBlank(dto.getAddress()), SysUserInfo::getAddress, dto.getAddress());
        wrapper.like(StringUtil.isNotBlank(dto.getRemark()), SysUserInfo::getRemark, dto.getRemark());
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
