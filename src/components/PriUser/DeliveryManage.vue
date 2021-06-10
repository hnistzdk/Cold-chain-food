<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>货单管理</el-breadcrumb-item>
    <el-breadcrumb-item >发货列表</el-breadcrumb-item>
  </el-breadcrumb>

  <!--  卡片区域-->
  <el-card>
    <!--    查询框布局区域-->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input  placeholder="请输入查询内容" v-model="queryInfo.query" clearable >
          <el-button slot="append" icon="el-icon-search" @click="getUserListPage"></el-button>
        </el-input></el-col>
      <el-col :span="4">
        <el-button type="primary" @click="addDialogVisible=true">添加订单</el-button>
      </el-col>
    </el-row>
    <!--    到达站点的货单列表区域-->
    <el-table :data="orderList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="货单号" prop="manifestId"></el-table-column>
      <el-table-column label="食品名称" prop="foodName"></el-table-column>
      <el-table-column label="发货地址" prop="startingSite"></el-table-column>
      <el-table-column label="收货地址" prop="receivedSite"></el-table-column>
      <el-table-column label="收货人姓名" prop="consigneeName"></el-table-column>

    </el-table>
    <!--    分页区-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNum"
      :page-sizes="[5,10,15]"
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
    <el-form :model="addForm" :rules="addFormRules" ref="addFormRef"
             label-width="70px">
      <el-form-item label="食品名称" prop="foodName">
        <el-select v-model="addForm.foodName"
                   placeholder="请选择食品"
                   @change="selectModel($event)">
          <el-option
            v-for="item in foodList"
            :key="item.id"
            :label="item.foodName"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="收货人Id" prop="consigneeId">
        <el-input v-model="addForm.consigneeId"></el-input>
      </el-form-item>

      <el-form-item label="收货人姓名" prop="consigneeName">
        <el-input v-model="addForm.consigneeName"></el-input>
      </el-form-item>

      <el-form-item label="收货地址" prop="receivedSite">
        <el-select v-model="addForm.receivedSite"
                   placeholder="请选择收货地址"
                   @change="selectModel1($event)">
          <el-option
            v-for="item in siteList"
            :key="item.storageId"
            :label="item.storageArea"
            :value="item.storageId">
          </el-option>
        </el-select>
      </el-form-item>

    </el-form>
    <!--      底部区域-->
    <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addOrder">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
import qs from "qs";

export default {
  name: "DeliveryManage",
  data(){
    return{
      queryInfo:{
        query:'',
        pageNum:1,
        pageSize:2
      },
      total:'',
      foodList:[],
      siteList:[],
      orderList:{

      },
      addDialogVisible:false,
      addForm:{
        foodId:'',
        foodName:'',
        //收货地址
        receivedSite:'',
        //收货地址id
        storageId:'',
        //收货人姓名
        consigneeName:'',
        consigneeId:''
      },
      addFormRules:{
        foodName:[
          {required:true,message:'请填写食品名称',trigger:'blur'}
        ],
        receivedSite:[
          {required:true,message:'请填写收货地址',trigger:'blur'}
        ],
        consigneeName:[
          {required:true,message:'请输入收货人姓名',trigger:'blur'}
        ]
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
      const {data : res} = await this.$http.post('getSendManifest',
        qs.stringify(this.queryInfo))
      if(res.meta.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }
        return this.$message.error('获取货单列表失败!')
      }
      this.orderList = res.data.orderList
      this.foodList = res.data.foodList
      this.siteList = res.data.siteList
      //console.log(this.arrivedPointList)
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
    addOrder(){
      //console.log(this.addForm)
      this.$refs.addFormRef.validate(async valid=>{
        if(!valid) return
        const {data : res} = await this.$http.post('addManifest',
          qs.stringify(this.addForm))
        if(res.meta.status !=="200"){
          if(res.meta.status==="403"){
            return this.$message.error('你无权访问!')
          }return this.$message.error('发货失败!')
        }
        this.$message.success('发货成功!')
        await this.getOrderList()
        this.addDialogVisible = false
      })
    },
    addCloseDialog(){
      this.$refs.addFormRef.resetFields()
    },
    selectModel(eh){
      this.addForm.foodId = this.foodList[eh].id
    },
    selectModel1(eh){
      this.addForm.storageId = this.siteList[eh].storageId
    }
  }
}
</script>

<style Lang="less" scoped>
.el-card{
  margin-top: 15px;
}
</style>
