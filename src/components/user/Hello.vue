<template>
<div class="Hello_container">
 <p> 欢迎来到管理系统！</p>
  <div class="btn">
    <el-button type="primary" @click="login_request">登录</el-button>
    <el-button type="primary" @click="register_request">注册</el-button>
  </div>
</div>
</template>

<script>
export default {
  name: 'Hello',
  methods:{
    login_request(){
      this.$router.push('/login');
    },
    register_request(){
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>

</style>
