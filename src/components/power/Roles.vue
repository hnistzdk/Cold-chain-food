<template>

  <div>
    <!--  面包屑导航栏-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--  卡片区域-->
    <el-card class="el-card">
      <!--    设置添加角色按钮-->
      <el-button type="primary" @click="addDialogVisible=true">添加角色</el-button>
      <!--    角色列表区域-->
      <el-table :data="roleList" border stripe>

        <!--      索引列-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="角色名称" prop="roleName"></el-table-column>
        <el-table-column label="角色描述" prop="roleDesc"></el-table-column>
        <el-table-column label="操作" >
          <template >
            <el-button type="primary" icon="el-icon-edit" size="small" >编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" size="small" >删除</el-button>
            <el-button type="warning" icon="el-icon-share" size="small" >分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--    添加角色的对话框-->
      <el-dialog
        title="添加用户"
        :visible.sync="addDialogVisible"
        width="30%"  >
        <!--      内容主体区域-->
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="addForm.roleName"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="addForm.roleDesc"></el-input>
          </el-form-item>
        </el-form>

        <!--      底部区域-->
        <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </span>
      </el-dialog>
      <!--    修改权限信息的对话框-->
      <el-dialog title="编辑信息" :visible.sync="editDialogVisible" width="30%">

        <el-form :model="editForm"  ref="editFormRef" label-width="70px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="editForm.roleName" ></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="editForm.roleDesc" ></el-input>
          </el-form-item>

        </el-form>

        <!--      底部栏-->
        <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editUser()">确 定</el-button>
  </span>
      </el-dialog>
      <!--    分配权限的对话框-->
      <el-dialog
        title="分配权限"
        :visible.sync="showSetRightDialogVisible"
        width="30%" >
        <!--      树形控件-->
        <el-tree :data="rightsList" :props="treeProps" show-checkbox node-key="id" default-expand-all :default-checked-keys="defKeys" ref="treeRef"></el-tree>
        <span slot="footer" class="dialog-footer">
    <el-button @click="showSetRightDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="allotRights">确 定</el-button>
  </span>
      </el-dialog>

    </el-card>
</div>
</template>

<script>
export default {
  name: "Role"
}
</script>

<style scoped>

</style>
