<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>食品管理</el-breadcrumb-item>
    <el-breadcrumb-item>食品列表</el-breadcrumb-item>
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
    <el-table :data="foodList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="食品名称" prop="foodName"></el-table-column>
      <el-table-column label="生产时期" prop="productionDate"></el-table-column>
      <el-table-column label="过期日期" prop="expiryDate"></el-table-column>
      <el-table-column label="生产地址" prop="address"></el-table-column>
      <el-table-column label="生产厂家" prop="producer"></el-table-column>
      <el-table-column label="联系电话" prop="phone"></el-table-column>
      <el-table-column label="风险等级" prop="riskDegree"></el-table-column>
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

  <!--    添加食品的对话框-->
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

  <!--    修改用户的对话框-->
  <el-dialog
    title="修改用户"
    :visible.sync="editDialogVisible"
    width="30%" >
    <!--      内容主体区域-->
    <el-form :model="editForm"  :rules="editFormRules" ref="editFormRef" label-width="85px">
      <el-form-item label="食品名称" prop="foodName">
        <el-input v-model="editForm.foodName"></el-input>
      </el-form-item>
      <el-form-item label="生产日期" prop="productionDate">
        <el-input v-model="editForm.productionDate"></el-input>
      </el-form-item>
      <el-form-item label="过期日期" prop="expiryDate">
        <el-input v-model="editForm.expiryDate"></el-input>
      </el-form-item>
      <el-form-item label="生产地址" prop="address">
        <el-input v-model="editForm.address"></el-input>
      </el-form-item>
      <el-form-item label="生产厂家" prop="producer">
        <el-input v-model="editForm.producer"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="editForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskDegree">
        <el-input v-model="editForm.riskDegree"></el-input>
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
  name: "List",
  data(){
    return{
      //分页的参数
      queryInfo:{
        query:null,
        pageNum:1,
        pageSize:5
      },
      foodList:[],
      //获取的食品的总数
      total:0,
      addDialogVisible:false,
      editDialogVisible:false,
      addForm:{
        foodName:'',
        productionDate:'',
        expiryDate:'',
        address:'',
        producer:'',
        phone:'',
        riskDegree:''
      },
      addFormRules:{
        foodName:[
          {required:true,message:'请输入食品名称',trigger:'blur'}
        ],
        productionDate:[
          {required:true,message:'请输入生产日期',trigger:'blur'}
        ],
        expiryDate:[
          {required:true,message:'请输入过期日期',trigger:'blur'}
        ],
        address:[
          {required:true,message:'请输入生产地址',trigger:'blur'}
        ],
        producer:[
          {required:true,message:'请输入生产厂家',trigger:'blur'}
        ],
        phone:[
          {required:true,message:'请输入联系电话',trigger:'blur'}
        ],
        riskDegree:[
          {required:true,message:'请输入风险等级',trigger:'blur'}
        ]

      },
      //修改食物信息的表单
      editForm:{

      },
      editFormRules:{
        foodName:[
          {required:true,message:'请输入食品名称',trigger:'blur'}
        ],
        productionDate:[
          {required:true,message:'请输入生产日期',trigger:'blur'}
        ],
        expiryDate:[
          {required:true,message:'请输入过期日期',trigger:'blur'}
        ],
        address:[
          {required:true,message:'请输入生产地址',trigger:'blur'}
        ],
        producer:[
          {required:true,message:'请输入生产厂家',trigger:'blur'}
        ],
        phone:[
          {required:true,message:'请输入联系电话',trigger:'blur'}
        ],
        riskDegree:[
          {required:true,message:'请输入风险等级',trigger:'blur'}
        ]

      }
    }
  },
  created() {
    this.getFoodList()
  },
  methods:{
    //获取食品列表
    async getFoodList(){
      const qs = require('querystring');
      const { data:res } = await this.$http.post('getFood',
      qs.stringify(this.queryInfo))
      if(res.meta.status !=="200"){
        return this.$message.error('获取食品列表失败!')
      }
      this.foodList = res.data.foodList
      this.total = res.data.total
      console.log(this.foodList)

    },
    //查询食品调用函数
    async getUserListPage(){
      this.queryInfo.pageNum = 1
      const { data:res } = await this.$http.post('getFood',
        qs.stringify(this.queryInfo))
      if(res.meta.status !=="200"){
        return this.$message.error('获取食品列表失败!')
      }
      this.foodList = res.data.foodList
      this.total = res.data.total
      console.log(this.foodList)


    },
    //分页操作
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize
      this.getFoodList()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage
      this.getFoodList()
    },
    //添加食物操作
    async addFood(){
      this.$refs.addFormRef.validate(async  valid=>{
        if(!valid) return
        const {data : res} = await this.$http.post('addFood',
          qs.stringify(this.addForm))

        if(res.meta.status !== "200"){
          return this.$message.error('添加食品失败!')
        }
        this.$message.success('添加食品成功!')
        await this.getFoodList()
        this.addDialogVisible = false
      })
    },
    //关闭添加食物对话框，重置对话框内容
    addCloseDialog()
    {
      this.$refs.addFormRef.resetFields()
    },
    async showEdit(id){
      console.log(id)
      const{ data:res } = await this.$http.get("foods/"+id)
      this.editForm = res.data
      console.log(res.data)
      if(res.status !== "200")
        return this.$message.error('查询食品信息失败!')

      this.editDialogVisible = true
    },
    editUser(){
      this.$refs.editFormRef.validate(async valid=>{
        if(!valid) return
        console.log(this.editForm.id)
        const {data:res} =
          await this.$http.post('modifyFood',qs.stringify(this.editForm))
        if(res.meta.status !== "200")
          return this.$message.error('修改食品信息失败!')
        this.$message.success('修改食品信息成功!')
        this.editDialogVisible = false
        await this.getFoodList()

      } )
    },
    //删除食品
    //删除分类
    async removeUserById(id){
      const confirmResult = await this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
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
        this.$message.error('删除食品失败!')
      else
        this.$message.success('删除食品成功!')
      this.queryInfo.pageNum = 1

      //重置表单
      await  this.getFoodList()
    }
  }
}
</script>

<style Lang="less" scoped>

</style>
