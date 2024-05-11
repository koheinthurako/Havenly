import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'
import tempDoc from '../components/Temp_Collection/tempForDoc.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import SubscribeVue from '@/views/SubscribeVue.vue'
import CancelSubscription from '../views/CancelSubscription.vue'
import PackagesView from '@/views/PackagesView.vue'
import testPhoto from '@/components/For_Testing/testPhoto.vue'
import AdminView from '../views/AdminView.vue'
import AdminLoginView from '../views/AdminLoginView.vue'
import CreditCard from '@/views/CreditCard.vue'




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
    name: 'SubscribeVue',
    component: SubscribeVue
  },
  {
    path: '/subscribe/cancel',
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
    path: '/packages/payment',
    name: 'CreditCard',
    component: CreditCard
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
  
  

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router