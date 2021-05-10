import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/user/Login.vue'
import Hello from '@/components/user/Hello'
import Register from '@/components/user/Register'
import Home from '@/components/user/Home'
import Welcome from '@/components/user/Welcome'
import Admin from '@/components/user/Admin'
import PrimaryHome from '@/components/PriUser/PrimaryHome'
import PrimaryUsers from "@/components/user/PrimaryUsers";
import EnterpriseUsers from "@/components/user/EnterpriseUsers";
import PwdChange from '@/components/user/PwdChange'
import Roles from "@/components/power/Roles";
Vue.use(Router)

const router=new  Router({
  routes: [
    {path:'/',redirect:'/hello'},
    {path:'/hello',component:Hello},
    { path: '/login',component: Login },
    {path:'/pwdChange',component:PwdChange},
    {path:'/register',component:Register},
    {path:'/home',component:Home,
      redirect:'/welcome',
      children:[
        {path:'/welcome',component:Welcome},
        {path:'/admin',component:Admin},
        {path:'/primary',component:PrimaryUsers },
        {path:'/enterprise',component:EnterpriseUsers },
        {path:'/roles',component:Roles }
      ]},
    {path:'/primaryHome',component:PrimaryHome}

  ]

});
//挂载路由导航守卫
router.beforeEach((to,form,next)=>{
  //to 将要访问的路径
  // form 代表从哪个路径跳转而来
  //next 是一个函数，表示放行
  if(to.path=='/pwdChange') return next();
  if(to.path=='/hello') return next();
  if(to.path=='/login') return next();
  if(to.path=='/register') return next();
  //获取token,如果token存在，则允许跳转至home页，否则回退回login页面
  const tokenStr = window.sessionStorage.getItem('token')
  if(!tokenStr) return next('/login')
  next()

})

export default router
