<template>
<div class="Hello_container">
  <div class="el-header">


    <div class="btn">
      <el-button type="primary" @click="login_request">登录</el-button>
      <el-button type="primary" @click="register_request">注册</el-button>
    </div>


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

<style Lang="less" scoped>
.Hello_container{

  height: 800px;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 99%);
}
.el-container{

  height: 100%;
}
.el-header{
  height: 150px;
  padding-left: 0;
  background-color: #7899ae;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  font-size: 20px;
  border: 1px red solid;

}
.btn{
  height: 200px;

  display: flex;
  left: 50%;
  transform: translate(800%,-20%);



}
.el-header div{
  display: flex;
  align-items: center;
}
.el-header span{

}



</style>
