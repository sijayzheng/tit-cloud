package cn.sijay.auth.controller;

import cn.sijay.common.core.entity.LoginReq;
import cn.sijay.common.core.entity.LoginRes;
import cn.sijay.common.core.entity.Res;
import cn.sijay.common.mybatis.dictionary.OperateType;
import cn.sijay.common.web.annotation.OprLog;
import cn.sijay.common.web.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <strong>AuthController</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Tag(name = "登录用户", description = "/auth")
@RequiredArgsConstructor
@RestController
public class AuthController extends BaseController {

//    private final AuthService authService;

    /**
     * 登录
     *
     * @param loginReq 登录请求对象
     * @return 返回选项集合
     */
    @OprLog(value = "登录", operateType = OperateType.LOGIN)
    @Operation(summary = "登录")
    @Parameter(name = "tableName", description = "表名")
    @ApiResponse(responseCode = "200", description = "登录")
    @PostMapping("login")
    public Res<LoginRes> login(@RequestBody LoginReq loginReq) {
        return success(new LoginRes(UUID.randomUUID().toString(), 6000L));
    }

    /**
     * 登出
     */
    @OprLog(value = "登出", operateType = OperateType.LOGOUT)
    @Operation(summary = "登出")
    @ApiResponse(responseCode = "200", description = "登出")
    @PostMapping("logout")
    public Res<Void> logout() {
        return success("登出成功");
    }
}

