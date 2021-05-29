<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>订单管理</el-breadcrumb-item>
    <el-breadcrumb-item>订单列表</el-breadcrumb-item>
  </el-breadcrumb>

  <!--卡片区域-->
  <el-card>
    <!--    查询框布局区域-->
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
    <el-table :data="orderList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="货单号" prop="manifestId"></el-table-column>
      <el-table-column label="发货人姓名" prop="consignorName"></el-table-column>
      <el-table-column label="收货人姓名" prop="consigneeName"></el-table-column>
      <el-table-column label="发货地址" prop="startingSite"></el-table-column>
      <el-table-column label="收货地址" prop="receivedSite"></el-table-column>
      <el-table-column label="运输状态" prop="travelStatus"></el-table-column>
      <el-table-column label="到达站点" prop="arrivedPoint"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <!--          编辑按钮-->
          <el-button type="primary" icon="el-icon-edit" size="small" @click="showEdit(scope.row.id)"></el-button>
          <!--          删除按钮-->
          <el-button type="danger" icon="el-icon-delete" size="small" @click="removeUserById(scope.row.id)"></el-button>

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
    title="添加货单"
    :visible.sync="addDialogVisible"
    width="40%" @close="addCloseDialog" >
    <!--      内容主体区域-->
    <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
      <el-form-item label="货单号" prop="manifestId">
        <el-input v-model="addForm.manifestId"></el-input>
      </el-form-item>
      <el-form-item label="食品id" prop="foodId">
        <el-input v-model="addForm.foodId"></el-input>
      </el-form-item>
      <el-form-item label="发货人姓名" prop="consignorName">
        <el-input v-model="addForm.consignorName"></el-input>
      </el-form-item>
      <el-form-item label="收货人姓名" prop="consigneeName">
        <el-input v-model="addForm.consigneeName"></el-input>
      </el-form-item>
      <el-form-item label="发货地址" prop="startingSite">
        <el-input v-model="addForm.startingSite"></el-input>
      </el-form-item>
      <el-form-item label="收货地址" prop="receivedSite">
        <el-input v-model="addForm.receivedSite"></el-input>
      </el-form-item>
      <el-form-item label="运输状态" prop="travelStatus">
        <el-input v-model="addForm.travelStatus"></el-input>
      </el-form-item>
      <el-form-item label="到达站点" prop="arrivedPoint">
        <el-input v-model="addForm.arrivedPoint"></el-input>
      </el-form-item>
      <el-form-item label="发货人id" prop="consignorId">
        <el-input v-model="addForm.consignorId"></el-input>
      </el-form-item>
      <el-form-item label="收货人id" prop="consigneeId">
        <el-input v-model="addForm.consigneeId"></el-input>
      </el-form-item>
    </el-form>
    <!--      底部区域-->
    <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addOrder">确 定</el-button>
  </span>
  </el-dialog>

  <!--    修改用户的对话框-->
  <el-dialog
    title="修改用户"
    :visible.sync="editDialogVisible"
    width="30%" >
    <!--      内容主体区域-->
    <el-form :model="editForm"  :rules="editFormRules" ref="editFormRef" label-width="85px">
      <el-form-item label="货单号" prop="manifestId">
        <el-input v-model="editForm.manifestId"></el-input>
      </el-form-item>
      <el-form-item label="食品id" prop="foodId">
        <el-input v-model="editForm.foodId"></el-input>
      </el-form-item>
      <el-form-item label="运输状态" prop="travelStatus">
        <el-input v-model="editForm.travelStatus"></el-input>
      </el-form-item>
      <el-form-item label="到达站点" prop="arrivedPoint">
        <el-input v-model="editForm.arrivedPoint"></el-input>
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
  name: "Order",
  data(){
    return{
      //分页以及查询参数
      queryInfo:{
        query:'',
        pageNum:1,
        pageSize:5
      },
      total:0,
      //获取订单的列表
      orderList:[],
      addForm:{
        //货单号
        manifestId:'',
        //食品id
        foodId:'',
        //发货人姓名
        consignorName:'',
        //收货人姓名
        consigneeName:'',
        //发货地址
        startingSite:'',
        //收货地址
        receivedSite:'',
        //运输状态
        travelStatus:'',
        //到达站点
        arrivedPoint:'',
        //发货人id
        consignorId:'',
        //收货人id
        consigneeId:''
      },
      addFormRules:{
        manifestId: [
          {required:true,message:'请输入货单号',trigger:'blur'}
        ],
        foodId: [
          {required:true,message:'请输入食品id',trigger:'blur'}
        ],
        consignorName: [
          {required:true,message:'请输入发货人姓名',trigger:'blur'}
        ],
        consigneeName: [
          {required:true,message:'请输入收货人姓名',trigger:'blur'}
        ],
        startingSite: [
          {required:true,message:'请输入发货地址',trigger:'blur'}
        ],
        receivedSite: [
          {required:true,message:'请输入收货地址',trigger:'blur'}
        ],
        travelStatus: [
          {required:true,message:'请输入运输状态',trigger:'blur'}
        ],
        arrivedPoint: [
          {required:true,message:'请输入到达站点',trigger:'blur'}
        ],
        consignorId: [
          {required:true,message:'请输入发货人id',trigger:'blur'}
        ],
        consigneeId: [
          {required:true,message:'请输入收货人id',trigger:'blur'}
        ]

      },
      editForm:{
        manifestId: [
          {required:true,message:'请输入货单号',trigger:'blur'}
        ],
        foodId: [
          {required:true,message:'请输入食品id',trigger:'blur'}
        ],
        travelStatus: [
          {required:true,message:'请输入运输状态',trigger:'blur'}
        ],
        arrivedPoint: [
          {required:true,message:'请输入到达站点',trigger:'blur'}
        ],

      },
      editFormRules:{

      }


    }
  },
  created() {
    this.getOrderList()
  },
  methods:{
    //获取货单列表
    async getOrderList(){
      const qs = require('querystring')
      const {data : res} = await this.$http.post('getManifest',
      qs.stringify(this.queryInfo))
      if(res.meta.status !== "200"){
        return this.$message.error('获取货单列表失败!')

      }
      this.orderList = res.data.orderList
      this.total = res.data.total
    },
    // 查询订单调用函数
    getUserListPage(){
      this.queryInfo.pageNum = 1
      this.getOrderList()
    },
    //分页操作
    handleSizeChange(newSize){
      this.queryInfo.pageSize =newSize
      this.getOrderList()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage
      this.getOrderList()
    },
    //添加货单
    async addOrder(){
      this.$refs.addFormRef.validate(async valid=>{
        if(!valid) return
        const {data : res} = await this.$http.post('addManifest',
          qs.stringify(this.addForm))
        if(res.meta.status !==200)
          return this.$message.error('添加货单失败!')
        this.$message.success('添加货单成功!')
        await this.getOrderList()
        this.addDialogVisible = true
      })
    },
    async showEdit(id){
      const {data : res} = await this.$http.get('manifests/'+id)
      this.editForm = res.data
      if(res.meta.status !== "200")
        return this.$message.error('查询货单信息失败!')

      this.editDialogVisible = true
    },

    editUser(){
      this.$refs.editFormRef.validate(async valid=>{
        if(!valid) return
        console.log(this.editForm.id)
        const {data:res} =
          await this.$http.post('modifyFood'+qs.stringify(this.editForm))
        if(res.meta.status !== "200")
          return this.$message.error('修改货单信息失败!')
        this.$message.success('修改货单信息成功!')
        this.editDialogVisible = false
        await this.getOrderList()

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
      const {data : res} = await  this.$http.post('deleteFood/'+id)
      if(res.meta.status !== "200")
        this.$message.error('删除货单失败!')
      else
        this.$message.success('删除货单成功!')

      //重置表单
      await  this.getOrderList()
    }

  }
}
</script>

<style scoped>

</style>
