import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/user/Login.vue'
import Hello from '@/components/user/Hello'
import Register from '@/components/user/Register'
import Home from '@/components/user/Home'
import Welcome from '@/components/user/Welcome'
import Users from '@/components/user/Users'
import PrimaryHome from '@/components/PriUser/PrimaryHome'

Vue.use(Router)

const router=new  Router({
  routes: [
    {path:'/',redirect:'/Hello'},
    {path:'/hello',component:Hello},
    { path: '/login',component: Login },
    {path:'/register',component:Register},
    {path:'/home',component:Home,
      redirect:'/welcome',
      children:[
        {path:'/welcome',component:Welcome},
        {path:'/users',component:Users}
      ]},
    {path:'/primaryHome',component:PrimaryHome}

  ]

});

export default router
