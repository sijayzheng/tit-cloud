const baseUrl = 'system'
// 用户信息后台接口
export const sysUserInfoApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysUserInfo/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysUserInfo/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysUserInfo/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysUserInfo/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysUserInfo/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysUserInfo/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysUserInfo/downloadTemplate`, {}, '用户信息_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysUserInfo/export`, {}, '用户信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysUserInfo/import`,
            method: 'post',
            data: data
        })
    }
}
