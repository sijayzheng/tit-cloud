package cn.sijay.system.dto;

import cn.sijay.common.core.utils.ValidateUtil;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>MetaDto</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
public class MetaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public MetaDto(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaDto(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaDto(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaDto(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (ValidateUtil.isHttp(link)) {
            this.link = link;
        }
    }

}
