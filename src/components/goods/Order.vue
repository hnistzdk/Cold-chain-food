<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>货单管理</el-breadcrumb-item>
    <el-breadcrumb-item>货单列表</el-breadcrumb-item>
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
        <el-button type="primary" @click="addDialogVisible=true">添加货单</el-button>
      </el-col>
    </el-row>

    <!--   货单列表区域-->
    <el-table :data="orderList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="货单号" prop="manifestId"></el-table-column>
      <el-table-column label="食品名称" prop="foodName"></el-table-column>
      <el-table-column label="发货人姓名" prop="consignorName"></el-table-column>
      <el-table-column label="收货人姓名" prop="consigneeName"></el-table-column>
      <el-table-column label="发货地址" prop="startingSite"></el-table-column>
      <el-table-column label="收货地址" prop="receivedSite"></el-table-column>
      <el-table-column label="运输状态" prop="travelStatus"></el-table-column>
      <el-table-column label="到达站点" prop="arrivedPoint"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <!--          编辑按钮-->
          <el-button type="primary" icon="el-icon-edit" size="small" @click="showEdit(scope.row.manifestId)"></el-button>
          <!--          删除按钮-->
          <el-button type="danger" icon="el-icon-delete" size="small" @click="removeUserById(scope.row.manifestId)"></el-button>

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
<!--      <el-form-item label="货单号" prop="manifestId">-->
<!--        <el-input v-model="addForm.manifestId"></el-input>-->
<!--      </el-form-item>-->

      <el-form-item label="食品名称" prop="foodName">
        <el-select v-model="addForm.foodName"
                   placeholder="请选择食品"
                    @change="selectModel($event)">
          <el-option
            v-for="item in foodList"
            :key="item.foodId"
            :label="item.foodName"
            :value="item.foodId">
          </el-option>
        </el-select>
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
        <el-select v-model="addForm.travelStatus"
                   placeholder="请选择运输状态">
          <el-option
            v-for="item in travelStatusList"
            :key="item.opt"
            :label="item.opt"
            :value="item.opt">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="到达站点" prop="storageId">
        <el-select v-model="addForm.storageId"
                   placeholder="请选择站点">
          <el-option
            v-for="item in arrivedPointList"
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

  <!--    修改用户的对话框-->
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
        <el-select v-model="editForm.foodName"
                   placeholder="请选择食品"
                   @change="selectChangeModel($event)">
          <el-option
            v-for="item in foodList"
            :key="item.foodId"
            :label="item.foodName"
            :value="item.foodId">
          </el-option>
        </el-select>
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

      <el-form-item label="到达站点" prop="storageId">
        <el-select v-model="editForm.storageId"
                   placeholder="请选择站点">
          <el-option
            v-for="item in arrivedPointList"
            :key="item.storageId"
            :label="item.storageArea"
            :value="item.storageId">
          </el-option>
        </el-select>
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
      addDialogVisible:false,
      editDialogVisible:false,
      //获取订单的列表
      orderList:[],
      //获取食品的列表
      foodList:[],
      arrivedPointList:[],
      travelStatusList:[
        {opt:'未发货',value:0},
        {opt:'已出库',value:1},
        {opt:'运输中',value:2},
        {opt:'已送达',value:3},
        {opt:'已签收',value:4}
      ],


      addForm:{
        //货单号
        manifestId:'',
        //食品id
        foodId:'',
        //食品名称
        foodName:'',
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
      editForm:{},
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
      editFormRules:{
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
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }
        return this.$message.error('获取货单列表失败!')
      }
      this.orderList = res.data.orderList
      this.foodList = res.data.foodList
      this.arrivedPointList = res.data.arrivedPointList
      console.log(this.arrivedPointList)
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
      console.log(this.addForm)
      this.$refs.addFormRef.validate(async valid=>{
        if(!valid) return
        const {data : res} = await this.$http.post('addManifest',
          qs.stringify(this.addForm))
        if(res.meta.status !=="200")
          return this.$message.error('添加货单失败!')
        this.$message.success('添加货单成功!')
        await this.getOrderList()
        this.addDialogVisible = false
      })
    },
    selectModel(eh){
        this.addForm.foodId = this.foodList[eh].foodId
    },
    async showEdit(id){
      const {data : res} = await this.$http.get('manifests/'+id)
      this.editForm = res.data
      console.log(this.editForm)
      if(res.status !== "200")
        return this.$message.error('查询货单信息失败!')
      this.editDialogVisible = true
    },

   async editUser(){
      this.$refs.editFormRef.validate(async valid=>{
        if(!valid) return
        console.log(this.editForm.id)
        const {data:res} =
          await this.$http.post('modifyManifest',qs.stringify(this.editForm))
        if(res.meta.status !== "200")
          return this.$message.error('修改货单信息失败!')
        this.$message.success('修改货单信息成功!')
        this.editDialogVisible = false
        await this.getOrderList()

      } )
    },
    selectChangeModel(eh){
      this.editForm.foodId = this.foodList[eh].foodId
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
      const {data : res} = await  this.$http.post('deleteManifest/'+id)
      if(res.meta.status !== "200")
        this.$message.error('删除货单失败!')
      else
        this.$message.success('删除货单成功!')

      //重置表单
      this.queryInfo.pageNum=1
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

<style lang="Less" scoped>
.el-card{
  margin-top: 15px;
}
</style>
