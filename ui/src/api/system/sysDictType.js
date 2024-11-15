const baseUrl = 'system'
// 字典类型后台接口
export const sysDictTypeApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysDictType/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysDictType/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysDictType/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysDictType/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysDictType/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysDictType/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysDictType/downloadTemplate`, {}, '字典类型_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysDictType/export`, {}, '字典类型.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysDictType/import`,
            method: 'post',
            data: data
        })
    },
    // 字典下拉列表
    toOptions() {
        return request({
            url: `${baseUrl}/sysDictType/toOptions`,
            method: 'get',
        })
    }
}
