<template>
  <div class="login_container">
    <div class="Register_button">
      <el-button type="danger" @click="register_request">注册</el-button>
    </div>
    <div class="login_box">
      <!--    头像区-->
      <div class="avatar_box">

      </div>
      <!--      登录表单区域-->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0" class="login_form">
        <!--        用户名-->
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--        密码-->
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" prefix-icon="el-icon-key"></el-input>
        </el-form-item>

        <el-form-item class="btn">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export  default {
  data(){
    return{
      loginForm:{
        username:"admin",
        password:"123456"
      },
      loginFormRules:{
        username:[
          { required: true, message: '请输入账号', trigger: 'blur'},//blur 鼠标焦点事件
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        //验证账号和密码是否合法
        password:[
          { required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods:{
    resetLoginForm(){
      //重置表单
      this.$refs.loginFormRef.resetFields();
    },
    login(){
      //axios发送客户端请求
     /* this.$refs.loginFormRef.validate(async valid=>{
        if(!valid) return;
        const {data:res }=await this.$http.post("login",this.loginForm);

        //判断登录是否成功并弹出提示框
        if(res.meta.status!==200) return this.$message.error("登陆失败");
        this.$message.success("登陆成功");
        //alert("登陆成功");
        window.sessionStorage.setItem('token',res.data.token);*/
        this.$router.push('/home');


    },
    register_request(){
      this.$router.push('/register');
    }
  }

};
</script>


<style Lang="less" scoped>
                   .login_container{

                     height: 800px;
                     background-color: #0093E9;
                     background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 99%);

                   }
.login_box{

  width: 450px;
  height: 300px;
  background-color: white;
  border-radius:3px ;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  opacity: 0.9;

}
.avatar_box{
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%,-50%);
  background-color: #fff;
}
.avatar_box img{
  height: 100%;
  width: 100%;
  border-radius: 50%;
  background-color: #eee;
}
.btn{
  display: flex;

}
.login_form{
  position: absolute;
  left: 50%;
  top: 60%;
  transform: translate(-50%,-50%);
}
.Register_button{
  display: flex;
  align-items: center;
  justify-content: space-between;
  transform: translate(95%,50%);
}
</style>
