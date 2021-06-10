<template>
<div>
  <!--  面包屑导航栏-->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>风险监测</el-breadcrumb-item>
    <el-breadcrumb-item>站点管理</el-breadcrumb-item>
  </el-breadcrumb>
<!--  卡片区域-->
  <el-card>
    <!--    查询框布局区域-->
    <!--    查询框布局区域-->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input  placeholder="请输入站点名称" v-model="queryInfo.query" clearable @clear="getAdminList()">
          <el-button slot="append" icon="el-icon-search" @click="getUserListPage"></el-button>
        </el-input>
      </el-col>
    </el-row>
<!--    站点列表区域-->
    <!--    分类列表区域-->
    <el-table :data="siteList"  border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column label="储存站点id" prop="storageId"></el-table-column>
      <el-table-column label="企业用户id" prop="uId"></el-table-column>
      <el-table-column label="站点地区" prop="storageArea"></el-table-column>
      <el-table-column label="空气温度" prop="temper"></el-table-column>
      <el-table-column label="空气湿度" prop="humidity"></el-table-column>
      <el-table-column label="报警记录" prop="alarmRecord"></el-table-column>
      <el-table-column label="市电监控" prop="powerSupply"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <!--          编辑按钮-->
          <el-button type="primary" icon="el-icon-edit" size="small" @click="showEdit(scope.row.storageId)"></el-button>
          <!--          删除按钮-->
          <el-button type="danger" icon="el-icon-delete" size="small" @click="removeUserById(scope.row.storageId)"></el-button>

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
  <!--    修改食品的对话框-->
  <el-dialog
    title="修改用户"
    :visible.sync="editDialogVisible"
    width="30%" >
    <!--      内容主体区域-->
    <el-form :model="editForm"  :rules="editFormRules" ref="editFormRef" label-width="85px">
      <el-form-item label="储存站点id" prop="storageId">
        <el-input v-model="editForm.storageId" disabled></el-input>
      </el-form-item>
      <el-form-item label="企业用户id" prop="uId">
        <el-input v-model="editForm.uId" disabled></el-input>
      </el-form-item>
      <el-form-item label="站点地区" prop="storageArea">
        <el-input v-model="editForm.storageArea" disabled></el-input>
      </el-form-item>
      <el-form-item label="空气温度" prop="temper">
        <el-input v-model="editForm.temper"></el-input>
      </el-form-item>
      <el-form-item label="空气湿度" prop="humidity">
        <el-input v-model="editForm.humidity"></el-input>
      </el-form-item>
      <el-form-item label="报警记录" prop="alarmRecord">
        <el-input v-model="editForm.alarmRecord"></el-input>
      </el-form-item>
      <el-form-item label="市电监控" prop="powerSupply">
        <el-input v-model="editForm.powerSupply"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
       <el-button @click="editDialogVisible = false">取 消</el-button>
       <el-button type="primary" @click="editSite()">确 定</el-button>
      </span>
  </el-dialog>
</div>
</template>

<script>
import qs from "qs";

export default {
  name: "siteManage",
  data(){
    return{
      queryInfo:{
        query:'',
        pageNum:1,
        pageSize:5
      },
      addDialogVisible:false,
      editDialogVisible:false,
      total:'',
      siteList:[],
      editList:{},
      editForm:{},
      editFormRules:{
        temper:[
          {required:true,message:'请输入空气温度',trigger:'blur'}
        ],
        humidity:[
          {required:true,message:'请输入空气湿度',trigger:'blur'}
        ],
        alarmRecord:[
          {required:true,message:'请输入报警记录',trigger:'blur'}
        ],
        powerSupply:[
          {required:true,message:'请输入市电监控信息',trigger:'blur'}
        ]
      }
    }
  },
  created() {
    this.getSiteList()
  },
  methods:{
    async getSiteList(){
     const {data : res} =await this.$http.post('getStorage',
       qs.stringify(this.queryInfo))
      if(res.meta.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }return this.$message.error('获取站点信息失败!')
      }

      this.siteList = res.data.siteList
      this.total = res.data.total
    },
    //查询食品调用函数
    async getUserListPage(){
      this.queryInfo.pageNum = 1
      const { data:res } = await this.$http.post('getStorage',
        qs.stringify(this.queryInfo))
      if(res.meta.status !=="200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }
        return this.$message.error('获取食品列表失败!')
      }
      this.siteList = res.data.siteList
      this.total = res.data.total
      console.log(this.siteList)
    },
    //分页操作
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize
      this.getSiteList()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage
      this.getSiteList()
    },
    async showEdit(id) {
      const {data: res} = await this.$http.post("showStorage/" + id)
      console.log(res.data)
      if (res.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }return this.$message.error('查询站点信息失败!')
      }
      this.editForm = res.data
      this.editDialogVisible = true
    },
    editSite(){
      this.$refs.editFormRef.validate(async valid=>{
        if(!valid) return
        console.log(this.editForm.storageId)
        const {data:res} =
          await this.$http.post('modifyStorage',qs.stringify(this.editForm))
        if(res.meta.status !== "200"){
          if(res.meta.status==="403"){
            return this.$message.error('你无权访问!')
          }return this.$message.error('修改站点信息失败!')
        }
        this.$message.success('修改站点信息成功!')
        this.editDialogVisible = false
        await this.getSiteList()

      } )
    },
    async removeUserById(id) {
      const confirmResult = await this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      //如果用户确定删除，则返回值为字符串confirm
      //如果用户取消了删除，则返回值为字符串cancel
      //console.log(confirmResult)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }
      const {data: res} = await this.$http.post('deleteStorage/'+ id)
      if (res.meta.status !== "200"){
        if(res.meta.status==="403"){
          return this.$message.error('你无权访问!')
        }this.$message.error('删除站点失败!')
      }

      else
        this.$message.success('删除站点成功!')
      this.queryInfo.pageNum = 1

      //重置表单
      await this.getSiteList()
    }
  }
}
</script>

<style lang="Less" scoped>

</style>
