<template>
  <el-container>
    <!--    头部区域-->
    <el-header>
      <div>
        <span>电商后台管理系统</span>
      </div>
      <!--  点击”退出“按钮,将token删除，使用户页面跳转至登录页面-->
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <!--    主体区域-->
    <el-container>
      <!--      侧边栏-->
      <!--      为侧边栏动态的设置宽度，如果点击按钮,则进行侧边栏宽度的转换-->
      <el-aside :width="isCollapse? '64px':'200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!--        侧菜单栏-->
        <el-menu background-color="#333744" text-color="#fff" active-text-color="#ffd04b" unique-opened :collapse="isCollapse"
                 :collapse-transition="false" :router="true" :default-active="activePath">
          <!--          一级菜单-->
          <!--          为菜单绑定数据嵌套，获取接口中的数据-->
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <!--            一级菜单的模板区域-->
            <template slot="title">
              <!--              图标-->
              <i :class="iconsObj[item.id]"></i>
              <!--              文本-->
              <span>{{ item.authName }}</span>
            </template>
            <!--            二级菜单-->
            <el-menu-item :index="'/'+subItem.path+''" v-for="subItem in item.children" :key="subItem.id" @click="saveNavState('/'+subItem.path)">
              <template slot="title">
                <!--              图标-->
                <i class="el-icon-menu"></i>
                <!--              文本-->
                <span>{{ subItem.authName }}</span>
              </template>

            </el-menu-item>

          </el-submenu>

        </el-menu>

      </el-aside>
      <!--      内容主题-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
export default {
  name: 'Home',
  data(){
    return{
      menuList:[],
      //给font里的图标设置一个方法用于调用
      iconsObj:{
        '125':'iconfont icon-user',
        '103':'iconfont icon-tijikongjian',
        '101':'iconfont icon-shangpin',
        '102':'iconfont icon-danju',
        '145':'iconfont icon-baobiao'
      },
      isCollapse: false,
      //被激活的链接地址
      activePath:''
    }
  },
  //在登录后马上创建geyMenuList函数用来为之后的获取表单数据做准备
  created () {
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
  },

  methods:{
    //退出按钮功能
    logout(){
      //销毁session
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    //获取所有的表单
    async getMenuList(){
      const {data:res}= await this.$http.get('menus')
      //200是接口的状态码，如果res.meta.status不等于200,则数据请求成功
      if(res.meta.status != 200) return this.$message.error(res.meta.msg)
      this.menuList = res.data
      console.log(res);
    },
    //点击按钮，操作菜单的折叠与展开
    toggleCollapse(){
      this.isCollapse=!this.isCollapse;
    },
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath);
      this.activePath = activePath;
    }
  }
}
</script>

<style Lang="less" scoped>
.el-container{

  height: 100%;
}

.el-header{
  padding-left: 0;
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  font-size: 20px;


}
.el-header div{
  display: flex;
  align-items: center;
}
.el-header span{
  margin-left: 15px;

}
.el-aside{
  background-color: #333744;

}
.el_main{
  background-color: #cb2020;

}
.iconfont{
  margin-right: 10px;
}
.el-menu{
  border-right: none;
}
.toggle-button{
  background-color: #4A5064;
  font-size: 10px;
  line-height: 24px;
  color: white;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;

}

</style>
