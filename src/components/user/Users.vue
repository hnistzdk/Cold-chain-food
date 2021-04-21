<template>
  <div>
    <!--  面包屑导航区域-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片区域 -->
    <el-card  class="el-card">
      <!--    查询框布局区域-->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input  placeholder="请输入查询内容" v-model="queryInfo.query" clearable @clear="getAdminList()">
            <el-button slot="append" icon="el-icon-search" @click="getUserListPage"></el-button>
          </el-input></el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
        </el-col>
      </el-row>
      <!--    用户列表区域-->
      <el-table :data="userList" border stripe>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="账号" prop="id"></el-table-column>
        <el-table-column label="昵称" prop="username"></el-table-column>
        <el-table-column label="性别" prop="gender"></el-table-column>
        <el-table-column label="电话" prop="tel"></el-table-column>
        <el-table-column label="角色" prop="role"></el-table-column>
        <el-table-column label="状态">
          <template  v-slot:default="scope">
                <!--编辑按钮-->
              <el-switch v-model="scope.row.mg_state" @change="userStateChanged(scope.row)"> </el-switch>
            <!--          删除按钮-->
            <el-button type="danger" icon="el-icon-delete" size="small" @click="removeUserById(scope.row.id)"></el-button>

          </template  >
        </el-table-column>
        <el-table-column label="操作" width="200px">
          <template >
            <!--          编辑按钮-->
            <el-button type="primary" icon="el-icon-edit" size="small" @click="showEdit"></el-button>
            <!--          删除按钮-->
            <el-button type="danger" icon="el-icon-delete" size="small" ></el-button>
            <!--          分配角色按钮-->
            <el-tooltip class="item" effect="dark" content="分配角色" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-share" size="small"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!--    分页区域-->

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 5, 12]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"  >
      </el-pagination>
      <!--    添加用户的对话框-->
      <el-dialog
        title="添加用户"
        :visible.sync="addDialogVisible"
        width="40%" @close="addCloseDialog" >
        <!--      内容主体区域-->
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="addForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addForm.password"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-radio v-model="addForm.gender" label="1">男</el-radio>
            <el-radio v-model="addForm.gender" label="2">女</el-radio>
          </el-form-item>
          <el-form-item label="电话" prop="mobile">
            <el-input v-model="addForm.mobile"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-radio v-model="addForm.role" label="1">普通用户</el-radio>
            <el-radio v-model="addForm.role" label="2">企业用户</el-radio>
            <el-radio v-model="addForm.role" label="3">管理员</el-radio>
          </el-form-item>
        </el-form>
        <!--      底部区域-->
        <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </span>
      </el-dialog>

      <!--    修改用户的对话框-->
      <el-dialog
        title="修改用户"
        :visible.sync="editDialogVisible"
        width="30%" @close="editCloseDialog">
        <!--      内容主体区域-->
        <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="editForm.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="editForm.email"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="mobile">
            <el-input v-model="editForm.mobile"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editUser()">确 定</el-button>
  </span>
      </el-dialog>


    </el-card>
  </div>
</template>

<script>
import * as qs from "qs";

export default {
  name: 'Users',
  data(){
    const checkMobile = (rule, value, cb) => {
      const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的电话号码'))
    }

    return{
      //获取用户列表的参数对象
      queryInfo:{
        query:'',
        pagenum:0,
        pagesize:2
      },
      userList:[],
      total:0,
      addDialogVisible:false,
      editDialogVisible:false,
      addForm:{
        id:'',
        username:'',
        gender:'',
        tel:'',
        role:''
      },
      editForm:{

      },
      addFormRules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
          {min:3,max:10,message: '用户名的长度在3~10个字符之间',trigger:'blur'}
        ],
        password: [
          {required:true,message:'请输入密码',trigger:'blur'},
          {min:3,max:12,message: '密码的长度在3~12个字符之间',trigger:'blur'}
        ],
        gender:[
          {required:true,message:'请点击单选框!',trigger:'blur'},
        ],
        mobile: [
          {required:true,message:'请输入电话号码',trigger:'blur'},
          {validator:checkMobile,trigger: 'blur'}
        ],
        rule:[
          {required:true,message:'请点击单选框!',trigger:'blur'}
        ]

      },
      editFormRules:{

        mobile: [
          {required:true,message:'请输入电话号码',trigger:'blur'},
          {validator:checkMobile,trigger: 'blur'}
        ]
      }

    }
  },
  created() {
    this.getAdminList()
    /*this.getPrimaryUserList(),
     this.getEnterpriseList()*/
  },
  methods:{
    async getAdminList(){
      const qs = require('querystring');
      //await this.$http.get('users',{params:this.queryInfo })
      const {data:res} = await this.$http.post('users',
        qs.stringify(this.queryInfo)
      )
      if(res.meta.status !== "200"){
        return this.$message.error('获取用户列表失败！')
      }
      this.userList=res.data.users;
      this.total =res.data.total
    },
    async getUserListPage(){

      this.queryInfo.pagenum=1
      const {data:res} = await this.$http.post('users',
        qs.stringify(this.queryInfo)
      )
      if(res.meta.status !== "200"){
        return this.$message.error('获取用户列表失败！')
      }
      this.userList=res.data.users
      this.total =res.data.total
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize=newSize
      this.getAdminList()
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum=newPage
      this.getAdminList()
    },
    //关闭表单后，将表单里的内容清空
    addCloseDialog(){
      this.$refs.addFormRef.resetFields();
    },
   async userStateChanged(userinfo){
      console.log(userinfo)
      const {data:res}=await  this.$http.put(`users/${userinfo.id}/state/${userinfo.mg_state}`)
      if(res.meta.status !=="200"){
        userinfo.mg_state =!userinfo.mg_state
        return this.$message.error('更新用户状态失败!')
      }
      this.$message.success('更新用户状态成功！')
    },
    //进行与后台的验证，如果验证成功，则添加用户成功
    addUser(){
      this.$refs.addFormRef.validate(async valid =>{
        if(!valid) return
        //console.log(valid)
        const {data:res}= await this.$http.post('users',this.addForm)
        if(res.meta.status!==201)
          this.$message.error('添加用户失败！')
        else
          this.$message.success('添加用户成功！')
        //将对话框隐藏
        this.addDialogVisible = false
        //重置表单
        await this.getAdminList()
      } )
    },
    editUser(){
      this.$refs.editFormRef.validate(async valid =>{
        if(!valid) return
        console.log(valid)
        //发起修改用户信息的数据请求
        const {data:res}= await  this.$http.put("users/"+this.editForm.id,{
          email:this.editForm.email,
          mobile:this.editForm.mobile
        })
        //console.log(this.editForm.id)
        if(res.meta.status!==200)
          this.$message.error('修改用户信息失败！')
        else
          this.$message.success('修改用户信息成功！')
        this.editDialogVisible =false
        await  this.getAdminList()

      })
    },
    //显示编辑用户的对话框
    async showEdit(id){

      //console.log(this.editForm.id)
      const {data:res} = await this.$http.get("users/"+id)
      if(res.meta.status!==200)
        return this.$message.error('查询用户信息失败!')
      this.editForm = res.data
      this.editDialogVisible =true

    },

    //重置编辑表单
    editCloseDialog(){
      this.$refs.editFormRef.resetFields();
    },
    //显示删除用户的对话框
    async removeUserById(id){
      //const {data:res} = await this.$http.delete("users/"+id)
      //console.log(id)

      const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      //如果用户确定删除，则返回值为字符串confirm
      //如果用户取消了删除，则返回值为字符串cancel
      //console.log(confirmResult)
      if(confirmResult !=='confirm'){
        return this.$message.info('已取消删除')
      }
      //console.log('确认了删除')
      const {data:res} = await this.$http.delete("users/"+id)
      if(res.meta.status!==200){
        this.$message.error('删除失败!')
      }
      else
        this.$message.success('删除成功!')
      //重置表单
      await  this.getAdminList()
    }


  }
}
</script>

<style scoped>

</style>
