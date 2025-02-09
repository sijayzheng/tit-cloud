const baseUrl = 'system'
// 登录用户后台接口
export const sysUserApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysUser/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysUser/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysUser/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysUser/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysUser/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysUser/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysUser/downloadTemplate`, {}, '登录用户_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysUser/export`, {}, '登录用户.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysUser/import`,
            method: 'post',
            data: data
        })
    }
}
