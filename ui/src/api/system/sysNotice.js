const baseUrl = 'system'
// 通知公告后台接口
export const sysNoticeApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysNotice/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysNotice/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysNotice/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysNotice/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysNotice/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysNotice/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysNotice/downloadTemplate`, {}, '通知公告_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysNotice/export`, {}, '通知公告.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysNotice/import`,
            method: 'post',
            data: data
        })
    }
}
