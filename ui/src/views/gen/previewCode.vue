<template>
  <el-tabs v-model="activeName" tab-position="left">
    <el-tab-pane v-for="item in Object.keys(files)" :label="files[item].name" :name="item">
      <el-scrollbar max-height="600px">
        <code-view :code="codeData[item]" :language="files[item].type"/>
      </el-scrollbar>
    </el-tab-pane>
  </el-tabs>
</template>

<script name="PreviewCode" setup>
const files = {
  'entity.java': {
    name: 'entity',
    type: 'java'
  },
  'dto.java': {
    name: 'dto',
    type: 'java'
  },
  'controller.java': {
    name: 'controller',
    type: 'java'
  },
  'service.java': {
    name: 'service',
    type: 'java'
  },
  'serviceImpl.java': {
    name: 'serviceImpl',
    type: 'java'
  },
  'mapper.java': {
    name: 'mapper',
    type: 'java'
  },
  'mapper.xml': {
    name: 'mapper',
    type: 'xml'
  },
  'vue': {
    name: 'vue',
    type: 'html'
  },
  'js': {
    name: 'js',
    type: 'js'
  },
}
const activeName = ref('entity.java')
const codeData = ref({})
const tableId = ref(undefined)

const preview = (id) => {
  tableId.value = id
  genApi.preview(id).then(r => codeData.value = r.data)
}

defineExpose({
  preview,
})
</script>

<style scoped>

</style>