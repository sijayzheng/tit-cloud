const baseUrl = 'system'
// 字典数据后台接口
export const sysDictDataApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysDictData/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysDictData/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysDictData/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysDictData/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysDictData/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysDictData/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysDictData/downloadTemplate`, {}, '字典数据_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysDictData/export`, {}, '字典数据.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysDictData/import`,
            method: 'post',
            data: data
        })
    },
    // 字典下拉列表
    toOptions(typeCode) {
        return request({
            url: `${baseUrl}/sysDictData/toOptions`,
            method: 'get',
            params: {typeCode: typeCode}
        })
    }
}
