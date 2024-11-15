const baseUrl = 'system'
// 系统模块后台接口
export const sysModuleApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysModule/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysModule/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysModule/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysModule/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysModule/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysModule/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysModule/downloadTemplate`, {}, '系统模块_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysModule/export`, {}, '系统模块.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysModule/import`,
            method: 'post',
            data: data
        })
    }
}
