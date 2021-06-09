<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>个人中心</el-breadcrumb-item>
    <el-breadcrumb-item>个人面板</el-breadcrumb-item>
  </el-breadcrumb>
<!--  卡片区域-->
  <el-card>
    <el-button type="warning" icon="el-icon-edit" @click="passwordDialogVisible = true" id="changePwd">
      修改密码
    </el-button>
    <el-form :model="userInfo" label-width="100px" ref="userInfoRef" :rules="userInfoRules">
      <el-form-item label="昵称:" prop="username">
        <el-input type="text" v-model="userInfo.username"></el-input>
      </el-form-item>
      <el-form-item label="真实姓名:" prop="username">
        <el-input type="text" v-model="userInfo.trueName" disabled></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="userInfo.gender" class="radio" >
          <el-radio :label="'男'">男</el-radio>
          <el-radio :label="'女'">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="电话:" prop="tel">
        <el-input type="text" v-model="userInfo.tel"></el-input>
      </el-form-item>
      <el-form-item label="邮箱:" prop="email">
        <el-input type="text" v-model="userInfo.email"></el-input>
      </el-form-item>
      <el-form-item label="身份:" prop="role">
        <el-input type="text" v-model="userInfo.role" disabled></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="editUserInfo">保存</el-button>
      </el-form-item>

    </el-form>

  </el-card>
  <!--    修改用户密码的对话框-->
  <el-dialog
    title="修改密码"
    :visible.sync="passwordDialogVisible"
    width="30%" @close="passwordDialogClose">
    <!--      内容主体区域-->
    <el-form :model="passwordForm" :rules="passwordFormRules" ref="passwordFormRef" label-width="70px">
      <el-form-item label="密码" prop="password">
        <el-input v-model="passwordForm.password"></el-input>
      </el-form-item>
      <el-form-item label="再次输入密码" prop="checkPassword">
        <el-input v-model="passwordForm.checkPassword"></el-input>
      </el-form-item>

    </el-form>

    <span slot="footer" class="dialog-footer">
    <el-button @click="passwordDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="passwordChange()">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
import qs from "qs";

export default {
  name: "UserCenter",
  data(){
    const checkTel = (rules,value,cb)=> {
      const regTel =/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/
      if(regTel.test(value)){
        return cb()
      }
      cb(new Error('请输入合法的手机号!'))
    };
    const checkEmail = (rule, value, cb) => {
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    };
    //密码的合法检验
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.passwordForm.checkPassword !== '') {
          this.$refs.passwordFormRef.validateField('checkPassword')
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.passwordForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };

    return{
      //用户信息
      userInfo:{
        username:'',
        trueName:'',
        gender:'',
        tel:'',
        email:'',
        role:''
      },
      userInfoRules:{
       username:[
         { required: true, message: '请输入昵称', trigger: 'blur'},
         {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
       ],
        tel: [
          { required: true, message: '请输入联系电话', trigger: 'blur'},
          { validator: checkTel, trigger: 'blur' },
        ],
        email:[
          { required: true, message: '请输入电子邮箱', trigger: 'blur'},
          { validator: checkEmail, trigger: 'blur' },
        ]
      },
      passwordDialogVisible:false,
      passwordForm:{
        password:'',
        checkPassword:''
      },
      passwordFormRules:{
        //验证账号和密码是否合法
        password:[
          { validator: validatePass, trigger: 'blur' },
          {min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}
        ],
        checkPassword: [
          { validator: validatePass2, trigger: 'blur' },
        ],
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods:{
    async getUserInfo(){
      const tokenStr = window.sessionStorage.getItem('token')
        const {data : res} =await this.$http.get('userInfo/'+tokenStr)
        if(res.meta.status !== '200') return this.$message.error('获取用户信息失败!')
        this.userInfo = res.data.userInfo
      },
    async editUserInfo(){
      this.$refs.userInfoRef.validate(async valid =>{
        const id = window.sessionStorage.getItem('token')
        if(!valid) return
        const {data : res} =await this.$http.post('editUserInfo/'+id, qs.stringify(this.userInfo))
        if(res.meta.status !== "200") return this.$message.error('修改用户信息失败!')
        else{
          this.$message.success("保存成功")
          await this.getUserInfo()
        }

      })
    },
     passwordChange(){
      this.$refs.passwordFormRef.validate(async valid=>{
        if(!valid) return
        const {data :res} = await this.$http.post('changePwd',
          qs.stringify(this.passwordForm))
        if(res.meta.status !=="200")
          this.$message.error('修改密码失败!')
        else
          this.$message.success('修改密码成功!')
        this.passwordDialogVisible = false
      })
    },
    passwordDialogClose(){
      this.$refs.passwordFormRef.resetFields()
    }

    },


}
</script>

<style Lang="less" scoped>
.el-card{
  margin-top: 15px;
},
#changePwd{
  right:80% ;
  margin-bottom: 15px;
}
</style>
