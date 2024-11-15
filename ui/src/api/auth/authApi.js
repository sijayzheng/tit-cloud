// 系统配置后台接口
export const authApi = {
    // 登录
    login(data) {
        return request({
            url: '/auth/login',
            method: 'post',
            data: data,
        })
    },
    // 登出
    logout() {
        return request({
            url: '/auth/logout',
            method: 'post',
        })
    },
}
