<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>风险监测</el-breadcrumb-item>
    <el-breadcrumb-item>食品检测</el-breadcrumb-item>
  </el-breadcrumb>

  <!--卡片区域-->
  <el-card>
    <!--    查询框布局区域-->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input  placeholder="请输入查询内容" v-model="queryInfo.query" clearable @clear="getAdminList()">
          <el-button slot="append" icon="el-icon-search" @click="getUserListPage"></el-button>
        </el-input></el-col>
      <el-col :span="4">
        <el-button type="primary" @click="addDialogVisible=true">添加订单</el-button>
      </el-col>
    </el-row>

    <!--   货单列表区域-->
    <el-table :data="riskList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="食品名称"  prop="foodName"></el-table-column>
      <el-table-column label="风险等级"  prop="riskDegree"></el-table-column>
      <el-table-column label="风险检测"  prop="riskEditor"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <!--          编辑按钮-->
          <el-button type="primary" icon="el-icon-edit" size="small" @click="showEdit(scope.row.foodId)"></el-button>
          <!--          删除按钮-->
          <el-button type="danger" icon="el-icon-delete" size="small" @click="removeUserById(scope.row.foodId)"></el-button>

        </template>
      </el-table-column>
    </el-table>

    <!--    分页区-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNum"
      :page-sizes="[2,5,10,15]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </el-card>
  <!--    添加的对话框-->
  <el-dialog
    title="添加食品风险监控"
    :visible.sync="addDialogVisible"
    width="40%" @close="addCloseDialog" >
    <!--      内容主体区域-->
    <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
      <el-form-item label="食品id" prop="foodId">
        <el-input v-model="addForm.foodName"></el-input>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskDegree">
        <el-radio-group v-model="addForm.riskDegree">
          <el-radio :label="1">一级</el-radio>
          <el-radio :label="2">二级</el-radio>
          <el-radio :label="3">三级</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="风险检测" prop="riskEditor">
        <el-radio-group v-model="addForm.riskEditor">
          <el-radio :label="1">已检测</el-radio>
          <el-radio :label="0">未检测</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <!--      底部区域-->
    <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addRisk">确 定</el-button>
  </span>
  </el-dialog>
  <!--    修改用户的对话框-->
  <el-dialog
    title="修改用户"
    :visible.sync="editDialogVisible"
    width="30%" >
    <!--      内容主体区域-->
    <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
      <el-form-item label="食品id" prop="foodName">
        <el-input v-model="editForm.foodName" disabled></el-input>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskDegree">
        <el-radio-group v-model="editForm.riskDegree">
          <el-radio :label="1">一级</el-radio>
          <el-radio :label="2">二级</el-radio>
          <el-radio :label="3">三级</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="风险检测" prop="riskEditor">
        <el-radio-group v-model="editForm.riskEditor">
          <el-radio :label="1">已检测</el-radio>
          <el-radio :label="0">未检测</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
       <el-button @click="editDialogVisible = false">取 消</el-button>
       <el-button type="primary" @click="editUser()">确 定</el-button>
      </span>
  </el-dialog>

</div>
</template>

<script>
import qs from "qs";

export default {
  name: "risk",
  data(){
    return{
      //存放货单列表数据
      riskList:[],
      //分页以及查询参数
      queryInfo:{
        query:'',
        pageNum:1,
        pageSize:5
      },
      total:0,
      addDialogVisible:false,
      editDialogVisible:false,
      addForm:{
        //食品名称
        foodName:'',
        //风险等级
        riskDegree:'',
        //风险检测
        riskEditor:''
      },
      addFormRules:{
        foodName: [
          {required:true,message:'请输入食品名称',trigger:'blur'}
        ]
      },
      editForm:{

      },
      editFormRules:{

      }
    }
  },
  created() {
    this.getRiskList()
  },
  methods:{
    async getRiskList(){
      const qs = require('querystring')
      const {data : res} =await this.$http.post('getRiskList',
      qs.stringify(this.queryInfo))
      if(res.meta.status !== "200"){
        return this.$message.error('获取货单列表失败!')
      }
      console.log(res.data)
      this.riskList = res.data.riskList
      console.log(this.riskList)
      this.total = res.data.total

    },
    // 查询订单调用函数
    getUserListPage(){
      this.queryInfo.pageNum = 1
      this.getRiskList()
    },
    //分页操作
    handleSizeChange(newSize){
      this.queryInfo.pageSize =newSize
      this.getRiskList()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage
      this.getRiskList()
    },
    //添加货单
    async addRisk(){
      this.$refs.addFormRef.validate(async valid=>{
        if(!valid) return
        const {data : res} = await this.$http.post('addRisk',
          qs.stringify(this.riskList))
        if(res.meta.status !=="200")
          return this.$message.error('添加监测食品失败!')
        this.$message.success('添加监测食品成功!')
        await this.getRiskList()
        this.addDialogVisible = true
      })
    },
    async showEdit(id){
      const {data : res} = await this.$http.get('risks/'+id)
      this.editForm = res.data
      if(res.status !== "200")
        return this.$message.error('查询货单信息失败!')

      this.editDialogVisible = true
    },
    editUser(){
      this.$refs.editFormRef.validate(async valid=>{
        if(!valid) return
        console.log(this.editForm.id)
        const {data:res} =
          await this.$http.post('modifyRisks',qs.stringify(this.editForm))
        if(res.meta.status !== "200")
          return this.$message.error('修改风险信息失败!')
        this.$message.success('修改风险信息成功!')
        this.editDialogVisible = false
        await this.getRiskList()

      } )
    },
    //删除分类
    async removeUserById(id){
      const confirmResult = await this.$confirm('此操作将永久删除该货单, 是否继续?', '提示', {
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
      const {data : res} = await  this.$http.post('deleteRisk/'+id)
      if(res.meta.status !== "200")
        this.$message.error('删除货单失败!')
      else
        this.$message.success('删除货单成功!')

      //重置表单
      await  this.getOrderList()
    },
    addCloseDialog(){
      this.$refs.addFormRef.resetFields()
    },
    editCloseDialog(){
      this.$refs.editFormRef.resetFields()
    }
  }
}
</script>

<style scoped>

</style>
