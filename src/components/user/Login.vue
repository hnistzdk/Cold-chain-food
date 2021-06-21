<template>
  <div class="login_container" >
    <vue-particles
      color="#fff"
      :particleOpacity="0.7"
      :particlesNumber="80"
      shapeType="edge"
      :particleSize="4"
      linesColor="#fff"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.3"
      :linesDistance="200"
      :moveSpeed="2"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="remove"
    >
    </vue-particles>
    <div class="Register_button">
      <el-button class="register" @click="register_request">注册</el-button>
    </div>
    <div class="login_box">
      <!--    头像区-->
      <div class="avatar_box">
        <img src="../../assets/xielunyan.png" alt="">
      </div>
      <!--      登录表单区域-->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0" class="login_form">
        <!--        用户名-->
        <el-form-item prop="id">
          <el-input v-model="loginForm.id" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--        密码-->
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" prefix-icon="el-icon-key"></el-input>
        </el-form-item>
        <el-button type="warning" @click="pwd_Forget">忘记密码</el-button>
        <!--        角色选择-->
        <el-radio-group v-model="radio" class="radio">
          <el-radio :label="1">普通用户</el-radio>
          <el-radio :label="2">企业用户</el-radio>
          <el-radio :label="3">管理员</el-radio>
        </el-radio-group>

        <!--登录组件-->
        <el-form-item class="btn">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      radio: 1,
      loginForm: {
        id: '',
        password: '',

      },
      loginFormRules: {
        id: [
          { required: true, message: '请输入账号', trigger: 'blur' },//blur 鼠标焦点事件
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        //验证账号和密码是否合法
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    resetLoginForm () {
      //重置表单
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      //axios发送客户端请求
      const qs = require('querystring')
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return
        if (this.radio === 1) {
          const { data: res } = await this.$http.post('primaryLogin', qs.stringify(this.loginForm))
          //判断登录是否成功并弹出提示框
          if (res.meta.status !== '200'){
            if(res.meta.status === '520'){
              return this.$message.error('该账号已被锁定')
            }
            return this.$message.error('登陆失败')
          }
          this.$message.success('登陆成功')
          window.sessionStorage.setItem('token', res.data.token)
          await this.$router.push('/home')
        } else if (this.radio === 2) {
          //判断登录是否成功并弹出提示框
          const { data: res } = await this.$http.post('enterpriseLogin', qs.stringify(this.loginForm))
          if (res.meta.status !== '200'){
            if(res.meta.status === '520'){
              return this.$message.error('该账号已被锁定')
            }
            return this.$message.error('登陆失败')
          }
          this.$message.success('登陆成功')
          window.sessionStorage.setItem('token', res.data.token)
          await this.$router.push('/home')
        } else {
          //判断登录是否成功并弹出提示框
          const { data: res } = await this.$http.post('adminLogin', qs.stringify(this.loginForm))
          if (res.meta.status !== '200'){
            if(res.meta.status === '520'){
              return this.$message.error('该账号已被锁定')
            }
            return this.$message.error('登陆失败')
          }
          this.$message.success('登陆成功')
          window.sessionStorage.setItem('token', res.data.token)
          await this.$router.push('/home')
        }
      })
    },
    register_request () {
      this.$router.push('/register')
    },
    pwd_Forget(){
      this.$router.push('/pwdChange')
    }
  }

}
</script>


<style Lang="less" scoped>
.login_container {

  height: 100%;
  width:100%;
  //background-color: #0093E9;
  background: url("../../assets/bg8.jpg");
  background-size:100% 100% ;
}

.login_box {

  width: 450px;
  height: 350px;
  background-color: white;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  opacity: 0.8;

}

.avatar_box {
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
}

.avatar_box img {
  height: 100%;
  width: 100%;
  border-radius: 50%;
  background-color: #eee;
}

.btn {
  display: flex;

}

.login_form {
  width: 300px;
  position: absolute;
  left: 50%;
  top: 60%;
  transform: translate(-50%, -50%);
}

.Register_button {
  display: flex;
  align-items: center;
  justify-content: space-between;
  transform: translate(90%, -1350%);
}

.radio {
  line-height: 20px;
}

.register {background-image: linear-gradient(to right, #70e1f5 0%, #ffd194  51%, #70e1f5  100%)}
.register {
  margin: 10px;
  padding: 15px 45px;
  text-align: center;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;
  box-shadow: 0 0 20px #eee;
  border-radius: 10px;
  display: block;
}

.register:hover {
  background-position: right center; /* change the direction of the change here */
  color: #fff;
  text-decoration: none;
}

</style>
