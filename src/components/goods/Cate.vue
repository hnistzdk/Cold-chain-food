<template>
  <div>
    <!--  面包屑导航栏-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类</el-breadcrumb-item>
    </el-breadcrumb>
    <!--卡片区域-->
    <el-card>
      <el-row>
        <!--  设置添加分类的列表  -->
        <el-button type="primary" @click="showAddCateDialog">添加分类</el-button>
      </el-row>

      <!--    分类列表区域-->
      <tree-table class="cateTable" :data="cateList" :columns="columns"
                  :selection-type="false" :expand-type="false"
                  show-index index-text="#" border>

        <!--      操作-->
        <template slot="opt" slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEdit(scope.row.id)">编辑</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserById(scope.row.id)">删除</el-button>
        </template>
      </tree-table>
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

    <!--  添加分类的表单-->
    <el-dialog title="添加分类"
               :visible.sync="addDialogVisible"
               width="30%" @close="addDialogClose">

      <!--      内容主体区域-->
      <el-form :model="addCateForm"
               :rules="addCateFormRules"
               ref="addCateFormRef">
      <!--添加商品的名称-->
        <el-form-item label="分类名称: " prop="categoryName">
          <el-input v-model="addCateForm.categoryName">
          </el-input>
        </el-form-item>
      </el-form>
      <!--      底部区域-->
      <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addCate">确 定</el-button>
      </span>

    </el-dialog>

    <!--    修改用户的对话框-->
    <el-dialog
      title="修改用户"
      :visible.sync="editDialogVisible"
      width="30%" >
      <!--      内容主体区域-->
      <el-form :model="editForm"  :rules="editFormRules" ref="editFormRef" label-width="85px">
        <!--添加商品的名称-->
        <el-form-item label="分类名称: " prop="foodName">
          <el-input v-model="editForm.categoryName">
          </el-input>
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
  name: 'Cate.vue',
  data(){
    return{
      queryInfo:{
        pageNum:1,
        pageSize:5
      },
      //商品分类的数据列表
      cateList:[],
      //总数据条数
      total:0,
      columns:[
        {
          label:'分类名称',
          prop:'categoryName'
        },
        {
          label: '操作',
          //表示将当前列定位模板列
          type:'template',
          template:'opt'
        }

      ],
      addDialogVisible: false,
      editDialogVisible:false,

      addCateForm:{
        //将要添加的分类的名称
        categoryName:'',

      },
      //父级分类的列表
      parentCateList:[],
      addCateFormRules:{
        categoryName: [
          {required:true,message:'请输入分类名称',trigger:'blur'}
        ]
      },
      //存放编辑表单的信息
      editForm:{

      },
      //编辑表单的校验规则
      editFormRules:{
        categoryName: [
          {required:true,message:'请输入分类名称',trigger:'blur'}
        ]
      }


    }
  },
  created () {
    this.getCateList()
  },
  methods:{
    //获取商品分类数据
    async getCateList(){
      const {data : res}= await this.$http.get('getFoodCategory',
        {params:this.queryInfo})
      if(res.meta.status !== "200")
        return this.$message.error('获取商品分类失败!')

      //console.log(res.data)
      //把数据列表赋值给cateList
      this.cateList = res.data.foodCategory
      console.log(this.cateList)
      //为总数据条数赋值
      this.total = res.data.total
    },
    handleSizeChange(newSize){
      this.queryInfo.pagesize = newSize
      this.getCateList()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pagenum = newPage
      this.getCateList()
    },

    showAddCateDialog(){
      this.addDialogVisible = true
    },

    addDialogClose()
    {
      this.$refs.addCateFormRef.resetFields()

    },
    async addCate(){
      this.$refs.addCateFormRef.validate(async  valid=>{
        if(!valid) return
        const {data : res} = await this.$http.post('categories',qs.stringify(this.addCateForm))

        if(res.meta.status !== "200"){
          return this.$message.error('添加分类失败!')
        }
        this.$message.success('添加分类成功!')
        await this.getCateList()
        this.addDialogVisible = false
      })
    },
    async showEdit(id){
      console.log(id)
      const{ data:res } = await this.$http.get("categories/"+id)
      this.editForm = res.data
      console.log(res.data)
      if(res.meta.status !== "200")
        return this.$message.error('查询分类信息失败!')

      this.editDialogVisible = true
    },
    async editUser(){
      this.$refs.editFormRef.validate(async valid=>{
        if(!valid) return
        console.log(this.editForm.id)
        const {data:res} = await this.$http.post('categories'+qs.stringify({
          categoryName:this.editForm.categoryName,
          id:this.editForm.id
        }))
        if(res.meta.status !== "200")
          return this.$message.error('修改分类信息失败!')
        this.$message.success('修改分类信息成功!')
        this.editDialogVisible = false
        await this.getCateList()

      } )
    },
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
      const {data : res} = await  this.$http.post('categories/'+id)
      if(res.meta.status !== "200")
        this.$message.error('删除分类失败!')
      else
        this.$message.success('删除分类成功!')

      //重置表单
      await  this.getCateList()
    }
  },


}
</script>

<style  Lang="less" scoped>
.cateTable{
  margin-top: 15px;
  margin-bottom: 15px;
}
.el-cascader{
  width: 100%;
}
</style>
