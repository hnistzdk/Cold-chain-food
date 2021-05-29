<template>

  <div>
    <!--  面包屑导航栏-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--  卡片区域-->
    <el-card class="el-card">
      <!--    设置添加角色按钮-->
      <el-button type="primary" @click="addDialogVisible=true">添加角色</el-button>
      <!--    角色列表区域-->
      <el-table :data="roleList" border stripe>
        <!--      索引列-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="角色名称" prop="roleName"></el-table-column>
        <el-table-column label="角色描述" prop="roleDescription"></el-table-column>
        <el-table-column label="操作" >
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="small" @click="showEdit(scope.row.id)" >编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" size="small" @click="removeUserById(scope.row.id)">删除</el-button>
            <el-button type="warning" icon="el-icon-share" size="small" @click="showSetRightDialog(scope.row.id)" >分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--    添加角色的对话框-->
      <el-dialog
        title="添加用户"
        :visible.sync="addDialogVisible"
        width="30%" @close="addCloseDialog" >
        <!--      内容主体区域-->
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="addForm.roleName"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDescription">
            <el-input v-model="addForm.roleDescription"></el-input>
          </el-form-item>
        </el-form>

        <!--      底部区域-->
        <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </span>
      </el-dialog>
      <!--    修改权限信息的对话框-->
      <el-dialog title="编辑信息" :visible.sync="editDialogVisible" width="30%">

        <el-form :model="editForm"  ref="editFormRef" :rules="editFormRules"  label-width="70px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="editForm.roleName" ></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDescription">
            <el-input v-model="editForm.roleDescription" ></el-input>
          </el-form-item>
        </el-form>

        <!--      底部栏-->
        <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editUser()">确 定</el-button>
  </span>
      </el-dialog>

      <el-dialog
        title="分配权限"
        :visible.sync="showSetRightDialogVisible"
        width="30%" @close="roleCloseDialog">

        <el-checkbox-group v-model="checkedList" >
          <el-checkbox  v-for="item in rightsList" :label="item.rightName" :key="item.id"
                       @change="item.checked=!item.checked"></el-checkbox>
        </el-checkbox-group>


        <span slot="footer" class="dialog-footer">
          <el-button @click="showSetRightDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="allotRights">确 定</el-button>
        </span>
      </el-dialog>
    </el-card>
</div>
</template>

<script>


import * as qs from 'qs'

export default {
  name: "Role",
  data(){
    return{
      roleList:[],
      //所有权限的数据
      rightsList:[],
      checkedList:[],
      showSetRightDialogVisible:false,
      addDialogVisible:false,
      editDialogVisible:false,
      addForm:{
        roleName:'',
        roleDescription:''
      },
      addFormRules:{
        roleName: [],
        roleDescription:[]
      },
      //编辑用户的表单
      editForm:{

      },
      editFormRules:{
        roleName: [],
        roleDescription:[]
      },
      treeProps:{
        label:'authName',
        children:'children'
      },
      defKeys:[],
      //分配权限的角色的id
      Id:''
    }

  },
  created() {
    this.getRoleList()
  },
  methods:{

    //获取角色列表
    async getRoleList(){
      const {data:res} = await this.$http.get("roles")
      //console.log(res);
      if(res.meta.status !=="200"){
        if(res.meta.status =="403")
          return this.$message.error('你无权访问!')
        return this.$message.error('获取角色列表失败!')
      }
      this.roleList = res.data.roleList
      console.log(this.roleList)
    },
    //添加角色
    addUser(){
      const qs = require('querystring')
      this.$refs.addFormRef.validate(async valid =>{
        if(!valid) return
        const {data:res} = await this.$http.post('roles',qs.stringify(this.addForm))
        console.log("添加表"+qs.stringify(this.addForm));
        if(res.meta.status !== "200")
          this.$message.error('添加角色失败!')
        else
          this.$message.success('添加角色成功!')
        //将对话框隐藏
        this.addDialogVisible = false
        //重置表单
        await this.getRoleList()
      })
    },
    //展示编辑角色的信息
    async showEdit(id){
      const {data:res} = await  this.$http.get('roles/'+id)
      if(res.meta.status !== "200")
        return this.$message.error('查询角色信息失败!')
      this.editForm = res.data.role
      this.editDialogVisible = true
    },
    //编辑角色
    editUser(){
      this.$refs.editFormRef.validate(async valid =>{
        if(!valid) return
        const {data:res}= await  this.$http.post('roles/'+this.editForm.id,qs.stringify({
          roleName:this.editForm.roleName,
          roleDescription:this.editForm.roleDescription
        }))
        if(res.meta.status !=="200")
          this.$message.error('修改角色信息失败!')
        else
          this.$message.success('修改角色信息成功!')
        this.editDialogVisible =false
        await  this.getRoleList()
      })
    },
    //删除角色
    async removeUserById(id){
      const confirmResult = await this.$confirm('此操作将永久删除角色,是否继续?','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:"warning"
      }).catch(err => err)
      //如果用户确定删除，则返回值为字符串confirm
      //如果用户取消删除，则返回值为字符串cancel
      if(confirmResult !=='confirm'){
        return this.message.info('已取消删除')
      }
      const {data:res} = await this.$http.delete('roles/'+id)
      if(res.meta.status!=="200"){
        this.$message.error('删除失败!')
      }
      else
        this.$message.success('删除成功!')
      //重置表单
      await this.getRoleList()

    },
    async showSetRightDialog(id)
    {
      this.Id = id
      //获取所有权限的数据
      const {data : res} = await this.$http.get('rights/list/'+id)
      if(res.meta.status !== "200")
      return this.$message.error('获取权限信息失败!')
      console.log(res.data)
      //把获取到的权限保存到data中
      this.rightsList = res.data.rightsList
      this.checkedList = res.data.checkedList
      this.showSetRightDialogVisible=true;

    },
    async allotRights(){

      console.log(this.rightsList)
      const {data:res}= await this.$http.post(`roles/${this.Id}/rights`,
        {rightList:this.rightsList})
      console.log(res)
      if(res.meta.status !== "200"){
        return this.$message.error('分配权限失败')
      }
      this.$message.success('分配权限成功')
      await this.getRoleList()
      this.showSetRightDialogVisible = false
    },
    addCloseDialog()
    {
      this.$refs.addFormRef.resetFields();
    },
    roleCloseDialog(){
      this.Id = ''
    }


  }
}
</script>

<style scoped>

</style>
