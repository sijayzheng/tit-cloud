<template>
  <div class="card-container">
    <el-switch v-model="dark" @change="toggleDark">
      <template #active-action>
        <span class="custom-inactive-action"><div class="i-ep:moon w-1em h-1em"></div></span>
      </template>
      <template #inactive-action>
        <span class="custom-active-action"> <div class="i-ep:sunny w-1em h-1em"></div></span>
      </template>
    </el-switch>
    <el-card>
      <template #header>
        <div class="card-header">
          <span>登录</span>
        </div>
      </template>
      <el-form :model="data">
        <el-form-item label="用户名">
          <el-input v-model="data.username"/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="data.password"/>
        </el-form-item>
        <el-form-item label="记住密码">
          <el-checkbox v-model="data.rememberMe"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script name="Login" setup>
import useUserStore from "@/store/userStore.js";
import router from "@/router/index.js";

const userStore = useUserStore()

const dark = ref(false)
const data = reactive({
  username: 'su',
  password: '123456',
  rememberMe: true
})
const loading = ref(false)
const redirect = ref('')
watch(() => router.currentRoute.value, (newRoute) => {
  redirect.value = newRoute.query && newRoute.query.redirect
}, {immediate: true})


const login = () => {
  userStore.login(data).then(() => {
    loading.value = false
    router.push({path: redirect.value || '/'})
  })
}

</script>

<style lang="scss" scoped>
.card-container {
  position: absolute;
  top: 100px;
  right: 200px;

  .el-card {
    width: 400px;
    height: 300px;
  }
}

</style>
