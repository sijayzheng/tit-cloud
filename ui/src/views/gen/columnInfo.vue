<template>
  <el-table v-loading="loading" :data="dataList" max-height="600px" scrollbar-always-on stripe>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" fixed label="列名" prop="columnName">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.columnName"></span>
        <el-input v-else v-model="scope.row.columnName"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="列注释" prop="columnComment">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.columnComment"></span>
        <el-input v-else v-model="scope.row.columnComment"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="90" :show-overflow-tooltip="true" label="列类型" prop="dataType">
    </el-table-column>
    <el-table-column :min-width="110" :show-overflow-tooltip="true" label="长度" prop="length">
      <template #default="scope">
        <span v-if="scope.row.unmodified||(scope.row.javaType!=='字符'&&scope.row.javaType!=='字符串')" v-text="scope.row.length"></span>
        <el-input-number v-else v-model="scope.row.length" controls-position="right" style="width: 80px"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="字段名称" prop="fieldName">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.fieldName"></span>
        <el-input v-else v-model="scope.row.fieldName"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="字段类型" prop="javaType">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.javaType"></span>
        <el-select v-else v-model="scope.row.javaType">
          <el-option v-for="item in javaTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="60" :show-overflow-tooltip="true" label="排序" prop="sort"></el-table-column>
    <el-table-column :min-width="80" :show-overflow-tooltip="true" label="唯一" prop="uniqueKey">
      <template #default="scope">
        <span v-if="scope.row.unmodified">{{ scope.row.uniqueKey ? '是' : '否' }}</span>
        <el-switch v-else v-model="scope.row.uniqueKey" active-text="是" inactive-text="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="80" :show-overflow-tooltip="true" label="可显示" prop="visible">
      <template #default="scope">
        <span v-if="scope.row.unmodified">{{ scope.row.visible ? '是' : '否' }}</span>
        <el-switch v-else v-model="scope.row.visible" active-text="是" inactive-text="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="80" :show-overflow-tooltip="true" label="可编辑" prop="editable">
      <template #default="scope">
        <span v-if="scope.row.unmodified">{{ scope.row.editable ? '是' : '否' }}</span>
        <el-switch v-else v-model="scope.row.editable" active-text="是" inactive-text="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="80" :show-overflow-tooltip="true" label="必填" prop="required">
      <template #default="scope">
        <span v-if="scope.row.unmodified">{{ scope.row.required ? '是' : '否' }}</span>
        <el-switch v-else v-model="scope.row.required" active-text="是" inactive-text="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="150" :show-overflow-tooltip="true" label="输入类型" prop="inputType">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.inputType"></span>
        <el-select v-else v-model="scope.row.inputType">
          <el-option v-for="item in inputTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="80" :show-overflow-tooltip="true" label="可查询" prop="queryable">
      <template #default="scope">
        <span v-if="scope.row.unmodified">{{ scope.row.queryable ? '是' : '否' }}</span>
        <el-switch v-else v-model="scope.row.queryable" active-text="是" inactive-text="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
    <el-table-column :min-width="120" :show-overflow-tooltip="true" label="查询方式" prop="queryType">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.queryType"></span>
        <el-select v-else v-model="scope.row.queryType">
          <el-option v-for="item in queryTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="160" :show-overflow-tooltip="true" label="数据字典" prop="dictType">
      <template #default="scope">
        <span v-if="scope.row.unmodified" v-text="scope.row.dictType"></span>
        <el-select v-else v-model="scope.row.dictType">
          <el-option v-for="item in dictTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column :min-width="80" :show-overflow-tooltip="true" label="可导出" prop="excelColumn">
      <template #default="scope">
        <span v-if="scope.row.unmodified">{{ scope.row.excelColumn ? '是' : '否' }}</span>
        <el-switch v-else v-model="scope.row.excelColumn" active-text="是" inactive-text="否" inline-prompt
                   style="--el-switch-on-color: #13ce66"/>
      </template>
    </el-table-column>
  </el-table>
</template>

<script name="ColumnInfo" setup>
const dataList = ref([])

const loading = ref(false)
const javaTypeOptions = ref([])
const inputTypeOptions = ref([])
const queryTypeOptions = ref([])
const dictTypeOptions = ref([])

const initData = async (superClass, tableId, tableName) => {
  loading.value = true
  javaTypeOptions.value = (await sysDictDataApi.toOptions('JavaType')).data
  inputTypeOptions.value = (await sysDictDataApi.toOptions('InputType')).data
  queryTypeOptions.value = (await sysDictDataApi.toOptions('QueryType')).data
  dictTypeOptions.value = (await sysDictTypeApi.toOptions()).data
  if (tableId) {
    genApi.listColumnByTableId(tableId, superClass).then(res => {
      dataList.value = res.data
      loading.value = false
    })
  } else {
    genApi.listTableColumns(tableName, superClass).then(res => {
      dataList.value = res.data
      loading.value = false
    })
  }
}

const getData = () => {
  return dataList.value
}
defineExpose({
  initData,
  getData
})
</script>

<style scoped>

</style>