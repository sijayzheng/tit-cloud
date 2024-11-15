const baseUrl = 'system'
// 部门信息后台接口
export const sysDeptApi = {
    // 根据id查询
    getById(id) {
        return request({
            url: `${baseUrl}/sysDept/getById`,
            method: 'get',
            params: {id: id}
        })
    },
    // 查询所有记录
    listAll() {
        return request({
            url: `${baseUrl}/sysDept/listAll`,
            method: 'get'
        })
    },
    // 分页查询
    page(queryParam) {
        return request({
            url: `${baseUrl}/sysDept/page`,
            method: 'get',
            params: queryParam
        })
    },
    // 添加
    add(data) {
        return request({
            url: `${baseUrl}/sysDept/add`,
            method: 'post',
            data: data
        })
    },
    // 修改
    update(data) {
        return request({
            url: `${baseUrl}/sysDept/update`,
            method: 'post',
            data: data
        })
    },
    // 删除
    remove(data) {
        return request({
            url: `${baseUrl}/sysDept/remove`,
            method: 'post',
            data: data
        })
    },
    // 下载导入模板
    downloadTemplate() {
        download(`${baseUrl}/sysDept/downloadTemplate`, {}, '部门信息_模板.xlsx')
    },
    // 导出数据
    exportData() {
        download(`${baseUrl}/sysDept/export`, {}, '部门信息.xlsx')
    },
    // 导入数据
    importData(data) {
        return request({
            url: `${baseUrl}/sysDept/import`,
            method: 'post',
            data: data
        })
    }
}
