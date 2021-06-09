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
      <el-table-column label="发货人姓名" prop="receivedUserName"></el-table-column>

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
</div>
</template>

<script>
export default {
  name: "ReceiveManage",
  data(){
    return{

    }
  },
  created() {
  },
  methods:{

  }
}
</script>

<style scoped>

</style>
