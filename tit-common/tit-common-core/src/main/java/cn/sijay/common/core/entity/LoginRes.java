package cn.sijay.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <strong>LoginRes</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {
    private String accessToken;
    private long expireIn;
}
