import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import SubscribeView from '../views/SubscribeView.vue'
import CancelSubscription from '../views/CancelSubscription.vue'

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
    path: '/',
    name: 'home',
    component: HomeIndex
  },
  {
    path: '/package',
    name: 'package',
    component: tempPackage
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
