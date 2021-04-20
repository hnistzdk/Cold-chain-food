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
          <el-input  placeholder="请输入内容" >
            <el-button slot="append" icon="el-icon-search" @click="getUserListPage"></el-button>
          </el-input></el-col>
        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>
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
          <template >
            <el-switch >
            </el-switch>
          </template  >
        </el-table-column>
        <el-table-column label="操作" width="200px">
          <template >
            <!--          编辑按钮-->
            <el-button type="primary" icon="el-icon-edit" size="small" ></el-button>
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
        :page-sizes="[1, 2, 5, 8]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"  >
      </el-pagination>

    </el-card>
  </div>
</template>

<script>
import * as qs from "qs";

export default {
  name: 'Users',
  data(){

    return{
      //获取用户列表的参数对象
      queryInfo:{
        query:'',
        pagenum:1,
        pagesize:2
      },
      userList:[],
      total:0,

      addForm:{
        id:'',
        username:'',
        gender:'',
        tel:'',
        role:''
      }
    }
  },
  created() {
    this.getUserList()
  },
  methods:{
    async getUserList(){
      const qs = require('querystring');
      //await this.$http.get('users',{params:this.queryInfo })
      const {data:res} = await this.$http.get('users', {
        params:qs.stringify(this.queryInfo)
      })
      console.log(res)
      if(res.meta.status !== 200){
        return this.$message.error('获取用户列表失败！')
      }
      this.userList=res.data.users
      this.total =res.data.total
      console.log(res)
    },
    async getUserListPage(){

      this.queryInfo.pagenum=1
      const {data:res} = await this.$http.get('users', { params: qs.stringify(this.queryInfo)
      })
      console.log(res)
      if(res.meta.status !== 200){
        return this.$message.error('获取用户列表失败！')
      }
      this.userList=res.data.users
      this.total =res.data.total
      console.log(res)
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize=newSize
      this.getUserList()
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum=newPage
      this.getUserList()
    }
 /*   async userStateChanged(userinfo){
      console.log(userinfo)
      const {data:res}=await  this.$http.put(`users/${userinfo.id}/state/${userinfo.mg_state}`)
      if(res.meta.status !==200){
        userinfo.mg_state =!userinfo.mg_state
        return this.$message.error('更新用户状态失败!')
      }
      this.$message.success('更新用户状态成功！')
    }*/


  }
}
</script>

<style scoped>

</style>
