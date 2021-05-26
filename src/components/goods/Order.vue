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
        <el-button type="primary" @click="addDialogVisible=true">添加食品</el-button>
      </el-col>
    </el-row>

    <!--    分类列表区域-->
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
    title="添加食品"
    :visible.sync="addDialogVisible"
    width="40%" @close="addCloseDialog" >
    <!--      内容主体区域-->
    <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
      <el-form-item label="食品名称" prop="foodName">
        <el-input v-model="addForm.foodName"></el-input>
      </el-form-item>
      <el-form-item label="生产日期" prop="productionDate">
        <el-input v-model="addForm.productionDate"></el-input>
      </el-form-item>
      <el-form-item label="过期日期" prop="expiryDate">
        <el-input v-model="addForm.expiryDate"></el-input>
      </el-form-item>
      <el-form-item label="生产地址" prop="address">
        <el-input v-model="addForm.address"></el-input>
      </el-form-item>
      <el-form-item label="生产厂家" prop="producer">
        <el-input v-model="addForm.producer"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="addForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskDegree">
        <el-input v-model="addForm.riskDegree"></el-input>
      </el-form-item>
    </el-form>
    <!--      底部区域-->
    <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addFood">确 定</el-button>
  </span>
  </el-dialog>

</div>
</template>

<script>
export default {
  name: "Order",
  data(){
    return{
      //分页以及查询参数
      queryInfo:{
        query:null,
        pageNum:1,
        pageSize:5
      },
      total:0,
      //获取订单的列表
      orderList:[],
      addForm:{

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
      const {data : res} = await this.$http.post('getOrder',
      qs.stringify(this.queryInfo))
      if(res.meta.status !== "200"){
        return this.$message.error('获取订单列表失败!')

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
    //

  }
}
</script>

<style scoped>

</style>
