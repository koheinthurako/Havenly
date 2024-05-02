import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'
import tempDoc from '../components/Temp_Collection/tempForDoc.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import SubscribeView from '../views/SubscribeView.vue'
import CancelSubscription from '../views/CancelSubscription.vue'
import PackagesView from '@/views/PackagesView.vue'
import {createApp} from 'vue'
import Location_List from '../components/LocationAPI/Location_List.vue'
import testingPage from '../components/For_Testing/testingOne.vue'
import IndexUserDashboard from '../components/User_Dashboard/indexUserDashboard.vue'



const routes = [
  {
    path: '/register',
    name: 'RegisterView',
    component: RegisterView
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/subscribe',
    name: 'SubscribeView',
    component: SubscribeView
  },
  {
    path: '/cancel',
    name: 'CancelSubscription',
    component: CancelSubscription
  },
  {
    path: '/packages/purchase',
    name: 'PackagesView',
    component: PackagesView
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
    path: '/packages',
    name: 'package',
    component: tempPackage
  },
  {
    path: '/my-package',
    name: 'packageDoc',
    component: tempDoc
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/category',
    name: 'Location_List',
    component: Location_List
  },
  {
    path: '/userDashboard',
    name: 'indexUserDashboard',
    component: IndexUserDashboard
  },{
    path: '/test',
    name: 'testingOne',
    component: testingPage
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
