import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'
import tempDoc from '../components/Temp_Collection/tempForDoc.vue'
import register from '../components/Login_&_Register/registerVue.vue'
import login from '../components/Login_&_Register/loginVue.vue'
import userDashboard from '../components/User_Dashboard/indexUserDashboard.vue'
import testingPage from '../components/For_Testing/testingOne.vue'
import loginakm from '../views/LoginView.vue'
import registerakm from '../views/RegisterView.vue'
import testPhoto from '../components/Test_Photo/testPhoto.vue'
import AdminView from '../views/AdminView.vue'
import AdminLoginView from '../views/AdminLoginView.vue'
import AdminBanList from '../views/AdminBanList.vue'





const routes = [
  // {
  //   path: '/home',
  //   redirect: '/home'
  // },
  // {
  //   path: '/Home',
  //   redirect: '/home',
  // },
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
    path: '/registerakm',
    name: 'registerakm',
    component: registerakm
  },
  {
    path: '/loginakm',
    name: 'loginakm',
    component: loginakm
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
  },
  {
    path: '/tempDoc',
    name: 'tempDoc',
    component: tempDoc
  },
  {
    path: '/testphoto',
    name: 'testphoto',
    component: testPhoto
  },
  {
    path: '/admin',
    name: 'Admin',
  component : AdminView
  },
  {
    path: '/admin/login',
    name: 'AdminLogin',
  component : AdminLoginView
  },
  {
    path: '/admin/ban',
    name: 'AdminBanList',
    component: AdminBanList
  },
  
  

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router