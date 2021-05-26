<template>
  <div>
    <!--  面包屑导航栏-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--  卡片区域-->
    <el-card class="el-card">
      <!--    权限列表区域-->
      <el-table :data="rightsList" style="width: 100%" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="rightName" label="权限名称" ></el-table-column>
        <el-table-column prop="rightPath" label="路径" ></el-table-column>
        <el-table-column prop="rightDescription" label="权限描述" ></el-table-column>
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
  name: 'Rights',
  data(){
    return{
      //权限列表
      rightsList:[],
      queryInfo:{
        pageNum:1,
        pageSize:5
      },
      total:0
    }
  },
  created(){
    //获取所有的数据
    this.getRightsList()
  },
  methods:{
    //获取权限列表
    async getRightsList(){
      const {data: res} = await  this.$http.get("rights/list",{params:this.queryInfo})
      if(res.meta.status !=="200"){
        return this.$message.error('获取权限列表失败!')
      }
      this.rightsList=res.data.rightsList
      this.total = res.data.total
      console.log(this.rightsList)
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize
      this.getRightsList()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage
      this.getRightsList()
    }
  }
}
</script>

<style Lang="less" scoped>

</style>
