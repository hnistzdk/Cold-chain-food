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
    <el-form :model="userInfo" label-width="100px" ref="userInfoRef" :rules="userInfoRules">
      <el-form-item label="昵称:" prop="username">
        <el-input type="text" v-model="userInfo.username"></el-input>
      </el-form-item>
      <el-form-item label="真实姓名:" prop="username">
        <el-input type="text" v-model="userInfo.trueName" disabled></el-input>
      </el-form-item>
        <el-radio-group v-model="userInfo.gender" class="radio" prop="gender">
          <el-radio :label="男">男</el-radio>
          <el-radio :label="女">女</el-radio>
        </el-radio-group>
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
    }

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
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods:{
    async getUserInfo(){
      const tokenStr = window.sessionStorage.getItem('token')
        const {data : res} = this.$http.post('userInfo/'+tokenStr.id)
        if(res.meta.status !== '200') return this.$message.error('获取用户信息失败!')
        this.userInfo = res.data.userInfo
      },
    editUserInfo(){
      this.$refs.userInfoRef.validate(async valid =>{
        if(!valid) return
        const {data : res} = this.$http.post('editUserInfo/'+tokenStr.id,
        qs.stringify(this.userInfo))
        if(res.meta.status !== '200') return this.$message.error('修改用户信息失败!')
        this.userInfo = res.data.userInfo
        await this.getUserInfo()
      })
    }
    },


}
</script>

<style scoped>

</style>
