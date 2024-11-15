<template>
  <el-drawer v-model="drawer.visible" size="100%" @close="close">
    <template #header>
      <h4>{{ drawer.title }}</h4>
    </template>
    <template #default>
      <el-row style="margin-bottom: 32px">
        <el-col :md="18" :xl="19">
          <el-steps :active="septIndex" align-center finish-status="success">
            <el-step title="表信息"/>
            <el-step :title="title"/>
            <el-step title="代码预览"/>
          </el-steps>
        </el-col>
        <el-col :md="6" :xl="5">
          <el-button type="primary" @click="septIndex = septIndex > 0?septIndex-1:0">上一步</el-button>
          <el-button type="primary" @click="next">下一步</el-button>
          <el-button type="danger" @click="close">关闭</el-button>
          <el-button v-if="canDownload" type="success" @click="download">下载代码</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-scrollbar max-height="650px">
            <div v-if="septIndex === 0">
              <table-info ref="tableInfoRef"/>
            </div>
            <div v-else-if="septIndex === 1">
              <column-info ref="columnInfoRef"/>
            </div>
            <div v-else-if="septIndex === 2">
              <preview-code ref="previewRef"/>
            </div>
          </el-scrollbar>
        </el-col>
      </el-row>
    </template>
  </el-drawer>
</template>

<script name="Generate" setup>
const drawer = reactive({
  visible: false,
  title: '添加表信息'
});
const emit = defineEmits({successful: null})
const tableForm = ref({})
const dataList = ref([])
const tableInfoRef = ref()
const columnInfoRef = ref()
const septIndex = ref(-1)
const title = ref('表的列信息')
const previewRef = ref()
const canDownload = ref(false)
const tableId = ref(undefined)

const open = async (id) => {
  console.log(id)
  drawer.visible = true;
  tableForm.value = {
    id: undefined,
    author: undefined,
    businessName: undefined,
    className: undefined,
    functionName: undefined,
    genPath: undefined,
    genType: undefined,
    moduleName: undefined,
    packageName: undefined,
    superClass: undefined,
    tableComment: undefined,
    tableName: undefined,
    templateType: undefined,
    fields: []
  }
  if (id) {
    tableId.value = id
    drawer.title = '修改表信息'
    Object.assign(tableForm.value, (await genApi.getTableById(id)).data)
  }
  septIndex.value = 0
}
const next = () => {
  if (septIndex.value === 0) {
    tableInfoRef.value.validate((valid) => {
      if (valid) {
        septIndex.value = 1
        console.log(tableInfoRef.value.getData())
        tableForm.value = tableInfoRef.value.getData()
        title.value = `表[${tableForm.value.tableName}]的列信息`
      }
    })
  } else if (septIndex.value === 1) {
    dataList.value = columnInfoRef.value.getData()
    tableForm.value.fields = dataList.value
    genApi.update(tableForm.value).then(res => {
      ElMessage.success(res.msg)
      septIndex.value = 2
      canDownload.value = true
      nextTick(() => {
        previewRef.value.preview(tableId.value)
      })
    })
  } else if (septIndex.value === 2) {
    septIndex.value = 2
  }
}
const close = () => {
  drawer.visible = false
  septIndex.value = undefined
  tableId.value = undefined
  emit('successful')
}
const download = () => {
  genApi.download(tableId.value, tableForm.value.functionName)
}
watch(tableInfoRef, (val) => val?.initData(tableForm.value))
watch(columnInfoRef, (val) => val?.initData(tableForm.value.superClass, tableId.value, tableForm.value.tableName))

defineExpose({
  open
})
</script>

<style scoped>

</style>