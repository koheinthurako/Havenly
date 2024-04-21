import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'

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
    name: 'RegisterView',
    component: RegisterView
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
