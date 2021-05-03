<template>
<div class="pwdChange_container">
  <div class="pwdChange_box">
    <div class="pwdChange_Form">
      <el-form :model="changeForm" ref="changeFormRef" :rules="changeFormRules" label-width="100px" >
        <el-form-item prop="id" label="id">
          <el-input v-model="changeForm.id" ></el-input>
        </el-form-item>

        <el-form-item prop="email" label="邮箱">
          <el-input type="text" v-model="changeForm.email" ></el-input>
        </el-form-item>

        <el-button type="warning" @click="sendCode" id="verification_button">发送验证码</el-button>
        <!--        角色选择-->
        <el-radio-group v-model="radio" class="radio" prop="role">
          <el-radio :label="1">普通用户</el-radio>
          <el-radio :label="2">企业用户</el-radio>
        </el-radio-group>
        <el-form-item prop="code" label="验证码" class="email_verification">
          <el-input type="text" v-model="changeForm.code" ></el-input>
        </el-form-item>
        <el-form-item prop="pwd" label="密码">
          <el-input type="password" v-model="changeForm.pwd" ></el-input>
        </el-form-item>
        <!--        密码-->
        <el-form-item prop="checkPwd" label="再次输入密码">
          <el-input type="password" v-model="changeForm.checkPwd" ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="commit">提交</el-button>
          <el-button>重置</el-button>
        </el-form-item>

      </el-form>
    </div>

  </div>


</div>
</template>

<script>
export default {
  name: 'PwdChange',
  data(){
    //密码的合法检验
    // const validatePass = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请输入密码'));
    //   } else {
    //     if (this.changeForm.pwd !=='') {
    //       this.$refs.ruleForm.validateField('checkPwd');
    //     }
    //     callback();
    //   }
    // };
    // const validatePass2 = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请再次输入密码'));
    //   } else if (value !== this.changeForm.pwd) {
    //     callback(new Error('两次输入密码不一致!'));
    //   } else {
    //     callback();
    //   }
    // };
    const checkEmail = (rule, value, cb) => {
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    }
    return{
      changeForm:{
        id:'',
        email:'',
        pwd:'',
        checkPwd:'',
        code:'',
        role:''
      },
      changeFormRules:{
        //验证账号和密码是否合法
        // pwd:[
        //   { validator: validatePass, trigger: 'blur' },
        //   {min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}
        // ],
        // checkPwd: [
        //
        //   { validator: validatePass2, trigger: 'blur' },
        //
        // ],
        email:[
          { validator: checkEmail, trigger: 'blur' }
        ],
        rule:[
          { required: true, message: '请填写角色框！', trigger: 'blur'}
        ]
      },
      radio:1,
      verification:''
    }
  },
  methods:{
    commit () {
      const  qs =require('querystring')
      this.$refs.changeFormRef.validate(async valid =>{
        if(!valid) return
        if(this.radio === 1){
          if(this.verification === this.changeForm.code)
          {
            const {data : res} = await  this.$http.post('primaryPwdChange',qs.stringify(this.changeForm))
            //判断注册是否成功并弹出提示框
            if(res.meta.status !==  '200') return this.$message.error('修改密码失败')
            this.$message.success('修改密码成功！即将跳转至登陆页面进行登录')
            await this.$router.push('/login')
          }
          else return  this.$message.error('验证码验证错误!')

        }else if(this.radio === 2){
          if(this.verification === this.changeForm.code){
            const {data : res} = await  this.$http.post('enterprisePwdChange',qs.stringify(this.changeForm))
            //判断注册是否成功并弹出提示框
            if(res.meta.status !=='200') return this.$message.error('修改密码失败')
            this.$message.success('修改密码成功！即将跳转至登陆页面进行登录')
            await this.$router.push('/login')
          }else  return  this.$message.error('验证码验证错误!')
        }
      })
    },
    async sendCode(){
      console.log(this.changeForm.email)
      const {data : res} = await this.$http.post('sendCode/'+this.changeForm.email)
      this.verification=res;
    }
  }
}
</script>

<style Lang="less" scoped>
.pwdChange_container{
  height: 800px;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 99%);
}
.pwdChange_box{
  width: 550px;
  height: 600px;
  background-color: white;
  border-radius:3px ;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  opacity: 0.9;
  border: 1px solid red;

}
.pwdChange_container span{
  font-size: 20px;
  color: #373d41;
}
.pwdChange_Form{
  position: absolute;
  left: 30%;
  top: 60%;
  transform: translate(-50%,-70%);

}

.radio{
  margin-top: 20px;
  display: flex;
  left: 50%;
  transform: translate(45%,-100%);
}

.email_verification{
  margin-bottom: 20px;
}

</style>
