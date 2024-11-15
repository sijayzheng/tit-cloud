const baseUrl = 'system'
// 角色信息后台接口
export const sysRoleApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysRole/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysRole/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysRole/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysRole/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysRole/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysRole/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysRole/downloadTemplate`, {}, '角色信息_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysRole/export`, {}, '角色信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysRole/import`,
            method: 'post',
            data: data
        })
    }
}
