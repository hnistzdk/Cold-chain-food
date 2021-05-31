<template>
  <div class="register_container">
    <div class="login_button">
      <el-button type="danger" @click="login_request">登录</el-button>
    </div>
    <div class="register_box">
      <div class="register_Form">
        <el-form  status-icon :model="RegisterForm" :rules="registerFormRules" ref="registerFormRef" label-width="200px"  class="Register_Form">
          <el-form-item label="昵称" prop="username">
            <el-input  type="text" v-model="RegisterForm.username" ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pwd">
            <el-input type="password"  v-model="RegisterForm.pwd"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPwd">
            <el-input type="password" v-model="RegisterForm.checkPwd" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="text"  v-model="RegisterForm.email"></el-input>
                      <el-button  type="warning" id="verification_button" @click="verification_request">获取验证码</el-button>
          </el-form-item>

          <el-form-item label="验证码" prop="code" class="email_verification">
            <el-input type="text"  v-model="RegisterForm.code" placeholder="请输入验证码"></el-input>
          </el-form-item>
          <!--        角色选择-->
            <el-radio-group v-model="radio" class="radio" prop="role">
            <el-radio :label="1">普通用户</el-radio>
            <el-radio :label="2">企业用户</el-radio>
            </el-radio-group>
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
        if (this.RegisterForm.checkPwd !== '') {
          this.$refs.registerFormRef.validateField('checkPwd')
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.RegisterForm.pwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    const checkEmail = (rule, value, cb) => {
      if (this.verification.test(value)) {
        return cb()
      }
      cb(new Error('请输入正确的验证码'))
    };
    const checkCode =(rule, value, cb) => {
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    }
    return{
      radio:1,
      verification:'',
      RegisterForm:{
        username:"",
        pwd:"",
        checkPwd:"",
        email:"",
        code:'',
        role:""

      },
      registerFormRules:{
        username:[
          { required: true, message: '请输入账号', trigger: 'blur'},//blur 鼠标焦点事件
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        //验证账号和密码是否合法
        pwd:[
          { validator: validatePass, trigger: 'blur' },
          {min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}
        ],
        checkPwd: [

          { validator: validatePass2, trigger: 'blur' },

        ],
        email:[
          { validator: checkEmail, trigger: 'blur' }
        ],
        rule:[
          { required: true, message: '请填写角色框！', trigger: 'blur'}
          ],
        code:[
          { required: true, message: '请填写验证码', trigger: 'blur'},
          { validator: checkCode, trigger: 'blur' }
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

      const  qs =require('querystring')
      this.$refs.registerFormRef.validate(async valid =>{
        if(!valid) return
        if(this.radio === 1){
          if(this.verification === this.RegisterForm.code)
          {
            const {data : res} = await  this.$http.post('primaryRegister',qs.stringify(this.RegisterForm))
            //判断注册是否成功并弹出提示框
            if(res.meta.status !==  '200') return this.$message.error('注册失败')
            this.$message.success('注册成功！即将跳转至登陆页面进行登录')
            await this.$router.push('/login')
          }
          else return  this.$message.error('验证码验证错误!')

        }else if(this.radio === 2){
          if(this.verification === this.RegisterForm.code){
            const {data : res} = await  this.$http.post('enterpriseRegister',qs.stringify(this.RegisterForm))
            //判断注册是否成功并弹出提示框
            if(res.meta.status !=='200') return this.$message.error('注册失败')
            this.$message.success('注册成功！即将跳转至登陆页面进行登录')
            await this.$router.push('/login')
          }else  return  this.$message.error('验证码验证错误!')
        }
      })

    },
    login_request(){
      this.$router.push('/login');
    },
    async verification_request(){
      //console.log(this.RegisterForm.email)
      const {data : res} = await this.$http.post('sendCode/'+this.RegisterForm.email)
      this.verification=res;
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
  height: 500px;
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
  transform: translate(-50%,-60%);

}
.login_button{
  display: flex;
  left: 50%;
  top: 50%;
  transform: translate(95%,50%);

}
.radio{
  margin-top: 40px;
  display: flex;
  left: 50%;
  transform: translate(45%,-100%);
}
#verification_button{
  display: flex;
  left: 70%;
  transform: translate(180%,-100%);
}
.email_verification{
  margin-bottom: 20px;
}
</style>
