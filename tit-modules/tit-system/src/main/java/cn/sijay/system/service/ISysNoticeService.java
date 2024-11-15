package cn.sijay.system.service;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.SysNoticeDto;
import cn.sijay.system.entity.SysNotice;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>ISysNoticeService</strong>
 * <p>
 * 通知公告服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysNoticeService extends IBaseService<SysNotice, SysNoticeDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysNoticeDto dto) {
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysNotice>().eq(SysNotice::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysNotice> buildLambdaQueryWrapper(SysNoticeDto dto) {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(dto.getTitle()), SysNotice::getTitle, dto.getTitle());
        wrapper.like(StringUtil.isNotBlank(dto.getContent()), SysNotice::getContent, dto.getContent());
        wrapper.like(StringUtil.isNotBlank(dto.getType()), SysNotice::getType, dto.getType());
        wrapper.like(StringUtil.isNotBlank(dto.getStatus()), SysNotice::getStatus, dto.getStatus());
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
