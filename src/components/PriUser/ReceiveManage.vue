<template>
  <div>
    <!--  面包屑导航栏-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>货单管理</el-breadcrumb-item>
      <el-breadcrumb-item >收货列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!--  卡片区域-->
    <el-card>
      <!--    查询框布局区域-->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input  placeholder="请输入查询内容" v-model="queryInfo.query" clearable >
            <el-button slot="append" icon="el-icon-search" @click="getUserListPage"></el-button>
          </el-input></el-col>
      </el-row>
      <!--    到达站点的货单列表区域-->
      <el-table :data="orderList"  border stripe>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="货单号" prop="manifestId"></el-table-column>
        <el-table-column label="食品名称" prop="foodName"></el-table-column>
        <el-table-column label="发货地址" prop="startingSite"></el-table-column>
        <el-table-column label="发货人姓名" prop="consignorName"></el-table-column>
        <el-table-column label="收货状态" prop="isReceived"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <!--          收货按钮-->
            <el-button type="primary" icon="el-icon-receiving" size="small"
                       @click="receiveManifest(scope.row.manifestId)"
                        v-if="scope.row.isReceived==='未收货'"></el-button>
          </template>
        </el-table-column>
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
    <div class="bottom">
      <span>有问题请咨询qq:369365576</span>
    </div>
  </div>
</template>

<script>

export default {
  name: "DeliveryManage",
  data(){
    return{
      queryInfo:{
        query:'',
        pageNum:1,
        pageSize:5
      },
      total:'',
      orderList:{

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
      const {data : res} = await this.$http.post('getReceiveManifest',
        qs.stringify(this.queryInfo))
      if(res.meta.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }
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
    async receiveManifest(id){
      const {data : res} =await this.$http.post('receive/'+id)
      if(res.meta.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }
        return this.$message.error('收货失败!')
      }
      this.$message.success('收货成功!')
      await this.getOrderList()
    }
  }
}
</script>

<style Lang="less" scoped>
.el-card{
  margin-top: 15px;
}
</style>
