<template>
  <div class="register_container">
    <div class="login_button">
      <el-button type="danger" @click="login_request">登录</el-button>
    </div>
    <div class="register_box">
      <div class="register_Form">
        <el-form  status-icon :model="RegisterForm" :rules="registerFormRules" ref="registerFormRef" label-width="200px" class="Register_Form">
          <el-form-item label="账号" prop="username">
            <el-input  type="username" v-model="RegisterForm.username" ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password"  v-model="RegisterForm.pass"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="RegisterForm.checkPass" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="text"  v-model="RegisterForm.email"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="commit">提交</el-button>
            <el-button  @click="resetRegisterForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>

    </div>
  </div>


</template>

<script>
export  default {
  data(){
    //密码的合法检验
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.RegisterForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.RegisterForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    const checkEmail = (rule, value, cb) => {
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    }
    return{
      RegisterForm:{
        username:"",
        pass:"",
        checkPass:"",
        email:""
      },
      registerFormRules:{
        username:[
          { required: true, message: '请输入账号', trigger: 'blur'},//blur 鼠标焦点事件
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        //验证账号和密码是否合法
        pass:[
          { validator: validatePass, trigger: 'blur' },
          {min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}
        ],
        checkPass: [

          { validator: validatePass2, trigger: 'blur' },

        ],
        email:[
          { validator: checkEmail, trigger: 'blur' }
        ]
      }
    };

    },
  methods:{
    resetRegisterForm(){
      //重置表单
      this.$refs.registerFormRef.resetFields();
    },
    commit(){
      this.$message.success("注册成功！")
    },
    login_request(){
      this.$router.push('/login');
    }
  }

};
</script>


<style Lang="less" scoped>
.register_container{
  height: 800px;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 99%);


}

.register_box{
  width: 550px;
  height: 400px;
  background-color: white;
  border-radius:3px ;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  opacity: 0.9;
  border: 1px solid red;

}
.register_container span{
  font-size: 20px;
  color: #373d41;
}
.register_Form{
  position: absolute;
  left: 30%;
  top: 60%;
  transform: translate(-50%,-50%);


}
.login_button{
  display: flex;
  left: 50%;
  top: 50%;
  transform: translate(95%,50%);

}

</style>
