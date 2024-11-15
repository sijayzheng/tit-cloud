const baseUrl = 'system'
// 菜单信息后台接口
export const sysMenuApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysMenu/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysMenu/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysMenu/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysMenu/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysMenu/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysMenu/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysMenu/downloadTemplate`, {}, '菜单信息_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysMenu/export`, {}, '菜单信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysMenu/import`,
            method: 'post',
            data: data
        })
    },
    getMenuTreeOptions() {
        return request({
            url: `${baseUrl}/sysMenu/getMenuTreeOptions`,
            method: 'get',
        })
    }
}
