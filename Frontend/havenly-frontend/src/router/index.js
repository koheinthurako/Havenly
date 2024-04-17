import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'

const routes = [
  {
    path: '/home',
    redirect: '/home'
  },
  {
    path: '/Home',
    redirect: '/home',
  },
  {
    path: '/',
    name: 'home',
    component: HomeIndex
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
