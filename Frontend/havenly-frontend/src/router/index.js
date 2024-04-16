import { createRouter, createWebHistory } from 'vue-router'
import mainIndex from '../components/mainIndexVue.vue'

const routes = [
  {
    path: '/Home', 
    redirect: '/home',
  },
  {
    path: '/',
    redirect: '/home',
  },
  {
    path: '/home',
    name: 'home',
    component: mainIndex
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
