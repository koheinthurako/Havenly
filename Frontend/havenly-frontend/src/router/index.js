import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'

import register from '../components/Login_&_Register/registerVue.vue'
import login from '../components/Login_&_Register/loginVue.vue'

import userDashboard from '../components/User_Dashboard/indexUserDashboard.vue'

import testingPage from '../components/For_Testing/testingOne.vue'



const routes = [
  {
    path: '/home',
    redirect: '/'
  },

  {
    path: '/Home',
    redirect: '/',
  },
  {
    path: '/',
    name: 'home',
    component: HomeIndex
  },
  {
    path: '/package',
    name: 'package',
    component: tempPackage
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/userdashboard',
    name: 'User_dashboard',
    component: userDashboard
  },
  {
    path: '/testingPage',
    name: 'testingPage',
    component: testingPage
  },
  {
    path: '/login',
    name: 'login',
    component: login
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
