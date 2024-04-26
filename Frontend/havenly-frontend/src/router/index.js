import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import SubscribeView from '../views/SubscribeView.vue'
import CancelSubscription from '../views/CancelSubscription.vue'
import PackagesView from '@/views/PackagesView.vue'
import {createApp} from 'vue'
import Location_List from '../components/LocationAPI/Location_List.vue'

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
    path: '/package',
    name: 'package',
    component: tempPackage
  },
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
    path: '/category',
    name: 'Location_List',
    component: Location_List
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

const app = createApp(LoginView);
app.use(router);



export default router
