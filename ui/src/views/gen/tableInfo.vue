<template>
  <el-form ref="tableFormRef" :model="tableForm" :rules="tableFormRules" label-position="left" label-width="120px" status-icon>
    <el-form-item label="表名：" prop="tableName">
      <el-select v-model="tableForm.tableName" clearable placeholder="请选择表名" @change="selectTable">
        <el-option v-for="item in tableNameOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="表备注：" prop="tableComment">
      <el-input v-model="tableForm.tableComment" clearable placeholder="请输入表备注"/>
    </el-form-item>
    <el-form-item label="实体类名称：" prop="className">
      <el-input v-model="tableForm.className" clearable placeholder="请输入实体类名称"/>
    </el-form-item>
    <el-form-item label="包路径：" prop="packageName">
      <el-input v-model="tableForm.packageName" clearable placeholder="请输入包路径"/>
    </el-form-item>
    <el-form-item label="模块名：" prop="moduleName">
      <el-input v-model="tableForm.moduleName" clearable placeholder="请输入模块名"/>
    </el-form-item>
    <el-form-item label="生成业务名" prop="businessName">
      <el-input v-model="tableForm.businessName" clearable placeholder="请输入生成业务名"/>
    </el-form-item>
    <el-form-item label="生成功能名" prop="functionName">
      <el-input v-model="tableForm.functionName" clearable placeholder="请输入生成功能名"/>
    </el-form-item>
    <el-form-item label="模板类型：" prop="templateType">
      <el-select v-model="tableForm.templateType" clearable placeholder="请选择模板类型">
        <el-option v-for="item in templateOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="作者：" prop="author">
      <el-input v-model="tableForm.author" clearable placeholder="请输入作者"/>
    </el-form-item>
    <el-form-item label="生成方式：" prop="genType">
      <el-select v-model="tableForm.genType" clearable placeholder="请选择生成方式">
        <el-option v-for="item in genTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item v-if="tableForm.genType==='C'" label="生成路径" prop="genPath">
      <el-input v-model="tableForm.genPath" clearable placeholder="请输入生成路径"/>
    </el-form-item>
    <el-form-item label="父类：" prop="superClass">
      <el-select v-model="tableForm.superClass" clearable placeholder="请选择父类">
        <el-option v-for="item in superClassOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="所属菜单：" prop="menuId">
      <el-tree-select v-model="tableForm.menuId"
                      :data="menuIdOptions"
                      :props="{ value: 'id', label: 'name', children: 'children' }"
                      accordion
                      check-strictly
                      clearable
                      filterable
                      highlight-current
                      node-key="id"
                      placeholder="请选择所属菜单"
                      value-key="id"/>
    </el-form-item>
  </el-form>
</template>

<script name="TableInfo" setup>

const tableForm = ref({})
const tableFormRef = ref();
const tableFormRules = ref({
  tableName: [relus.required('表名'), relus.maxLength('表名', 50)],
  tableComment: [relus.required('表备注'), relus.maxLength('表备注', 200)],
  className: [relus.required('实体类'), relus.maxLength('实体类名称', 100)],
  templateType: [relus.required('模板类型'),],
  packageName: [relus.required('包路径'), relus.maxLength('包路径', 100)],
  moduleName: [relus.required('模块名'), relus.maxLength('模块名', 30)],
  author: [relus.required('作者'), relus.maxLength('作者', 50)],
  genType: [relus.required('生成方式'),],
  menuId: [relus.required('所属菜单'),],
})

const templateOptions = ref([])
const genTypeOptions = ref([])
const superClassOptions = ref([])
const menuIdOptions = ref([])
const tableNameOptions = ref([])

const tableMap = ref([])
const selectTable = (value) => {
}
const initData = async (data) => {
  Object.assign(tableForm.value, data)
  templateOptions.value = (await sysDictDataApi.toOptions('TemplateType')).data
  genTypeOptions.value = (await sysDictDataApi.toOptions('GenType')).data
  superClassOptions.value = (await sysDictDataApi.toOptions('SuperClassType')).data
  sysMenuApi.getMenuTreeOptions().then(res => {
    menuIdOptions.value = res.data
    tableForm.value.menuId = data?.menuId ? data.menuId : 1
  })
  genApi.listTable().then(res => {
    let map = {}, options = []
    res.data.forEach(item => {
      options.push({
        label: `${item.tableName}(${item.tableComment})`,
        value: item.tableName
      })
      map[item.tableName] = item
    })
    tableNameOptions.value = options
    tableMap.value = map
  })
  let genProperties = (await genApi.getGenProperties()).data
  tableForm.value.path = genProperties.path
  tableForm.value.author = genProperties.author
  tableForm.value.templateType = genProperties.templateType
  tableForm.value.packageName = genProperties.packageName
  tableForm.value.genType = genProperties.genType
  console.log(tableForm.value)
}
const getData = () => tableForm.value

const validate = (fun) => {
  return tableFormRef.value?.validate(fun)
}
defineExpose({
  initData,
  getData,
  validate
})
</script>

<style scoped>

</style>