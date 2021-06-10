<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>风险监测</el-breadcrumb-item>
    <el-breadcrumb-item >站点监控</el-breadcrumb-item>
  </el-breadcrumb>

  <!--  卡片区域-->
  <el-card>
    <!--    查询框布局区域-->

    <!--    到达站点的货单列表区域-->
    <el-table :data="orderList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="货单号" prop="manifestId"></el-table-column>
      <el-table-column label="食品名称" prop="foodName"></el-table-column>
      <el-table-column label="发货地址" prop="startingSite"></el-table-column>
      <el-table-column label="收货地址" prop="receivedSite"></el-table-column>
      <el-table-column label="风险等级" prop="riskDegree"></el-table-column>
      <el-table-column label="风险检测" prop="riskEditor"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <!--          检测按钮，如果未检测，则不能进行发货-->
          <el-button type="primary" icon="el-icon-warning" size="small"
                     @click="scope.row.riskEditor==='已检测'">
                      {{scope.row.riskEditor}}</el-button>
          <!--         发货按钮-->
          <el-button type="danger" icon="el-icon-delete" size="small"
                     v-if="scope.row.riskEditor==='未检测'"
                     disabled>未检测</el-button>
          <el-button v-else type="primary" icon="el-icon-check"
                     @click="showEdit(scope.row.storageId)">
                     已检测</el-button>

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
  <!--    修改货单状态的对话框-->
  <el-dialog
    title="修改货单"
    :visible.sync="editDialogVisible"
    width="30%" >
    <!--      内容主体区域-->
    <el-form :model="editForm"  :rules="editFormRules" ref="editFormRef" label-width="85px">
      <el-form-item label="货单号" prop="manifestId">
        <el-input v-model="editForm.manifestId" disabled></el-input>
      </el-form-item>
      <el-form-item label="食品名称" prop="foodName">
        <el-input v-model="editForm.foodName" disabled></el-input>
      </el-form-item>
      <el-form-item label="运输状态" prop="travelStatus">
        <el-select v-model="editForm.travelStatus"
                   placeholder="请选择运输状态">
          <el-option
            v-for="item in travelStatusList"
            :key="item.opt"
            :label="item.opt"
            :value="item.opt">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="下一站点" prop="storageId">
        <el-select v-model="editForm.storageId"
                   placeholder="请选择下一站点">
          <el-option
            v-for="item in arrivedPointList"
            :key="item.storageId"
            :label="item.storageArea"
            :value="item.storageId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskDegree">
        <el-input v-model="editForm.manifestId" disabled></el-input>
      </el-form-item>
      <el-form-item label="风险检测" prop="riskEditor">
        <el-input v-model="editForm.manifestId" disabled></el-input>
      </el-form-item>

    </el-form>

    <span slot="footer" class="dialog-footer">
       <el-button @click="editDialogVisible = false">取 消</el-button>
       <el-button type="primary" @click="editOrder()">确 定</el-button>
      </span>
  </el-dialog>
  <div class="bottom">
    <span>有问题请咨询qq:369365576</span>
  </div>
</div>
</template>

<script>
import qs from "qs";

export default {
  name: "siteApplication",
  data(){
    return{
      queryInfo:{
        query:'',
        pageNum:1,
        pageSize:5
      },
      total:'',
      orderList:{},
      editForm:{},
      editDialogVisible:false,
      travelStatusList:[
        {opt:'未发货',value:0},
        {opt:'已出库',value:1},
        {opt:'运输中',value:2},
        {opt:'已送达',value:3},
        {opt:'已签收',value:4}
      ],
      arrivedPointList:[],
      editFormRules:{
      }
    }
  },
  created() {
    this.getOrderListerList()
  },
  methods:{
     async getOrderListerList() {
     const{data : res} =await this.$http.post('getSiteOrderList',
        qs.stringify(this.queryInfo))
      if(res.meta.status !=="200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }return this.$message.error('获取站点货单信息失败!')
      }

      this.orderList = res.data.orderList
      this.total =res.data.total
      this.arrivedPointList = res.data.arrivedPointList
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
    showEdit(id){
      const {data : res} =  this.$http.post('showOrder/'+id)
      if(res.meta.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }return this.$message.error('获取货单信息失败!')
      }

      this.editForm = res.data.orderInfo
      this.editDialogVisible = true
    },
    async editOrder(){
        this.$refs.editFormRef.validate(async valid=>{
          if(!valid) return
          const {data : res} = this.$http.post('changeOrder',
          qs.stringify(this.editForm))
          if(res.meta.status !=="200"){
            if(res.meta.status==="403"){
              return this.$message.error('你无权访问!')
            }return this.$message.error('修改订单状态失败!')
          }

          this.$message.success('修改订单状态成功!')
          this.editDialogVisible =false

        })
    }

  }
}
</script>

<style lang="Less" scoped>

</style>
