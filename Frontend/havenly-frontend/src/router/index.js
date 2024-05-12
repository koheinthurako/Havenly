
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

// import testPhoto from '../components/Test_Photo/testPhoto.vue'
import AdminView from '../views/AdminView.vue'
import AdminLoginView from '../views/AdminLoginView.vue'
import AdminBanList from '../views/AdminBanList.vue'



// import testPhoto from '../components/Test_Photo/testPhoto.vue'
// import AdminView from '../views/AdminView.vue'
// import AdminLoginView from '../views/AdminLoginView.vue'
// import userDashBoardNew from '@/components/User_Dashboard/userDashBoardNew.vue'
import SubscribeVue from '@/views/SubscribeVue.vue'
import CancelSubscription from '../views/CancelSubscription.vue'
import PackagesView from '@/views/PackagesView.vue'

import AdminPost from '@/views/AdminPost.vue'

import PostsView from '@/views/PostsView.vue'



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
    path: '/subscribe',
    name: 'SubscribeVue',
    component: SubscribeVue
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
  // {
  //   path: '/userDashBoardNew',
  //   name: 'userDashBoardNew',
  //   component: userDashBoardNew,
  //   meta: { requiresAuth: true }
  // },
  {
    path: '/userdashboard',
    name: 'User_dashboard',
    component: userDashboard,
    meta: { requiresAuth: true }
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
    path: '/PostsView',
    name: 'PostsView',
    component: PostsView
  },
  // {
  //   path: '/testphoto',
  //   name: 'testphoto',
  //   component: testPhoto
  // },
  {
    path: '/admin',
    name: 'Admin',
  component : AdminView,
  meta: { requiresAdmin: true }
  },
  {
    path: '/admin/login',
    name: 'AdminLogin',
  component : AdminLoginView
  },
  {
    path: '/admin/ban',
    name: 'AdminBanList',
    component: AdminBanList,
    meta: { requiresAdmin: true }
  },
  {
    path: '/admin/post',
    name: 'AdminPost',
    component: AdminPost,
    meta: { requiresAdmin: true },
   
  },
  
  

  

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from, next) => {

  const admin = sessionStorage.getItem('admin_user');

  // Check if the route requires authentication
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // Check if user is logged in
    const user = sessionStorage.getItem('login_user');
    if (!user) {
      // If user is not logged in, redirect to login page
      alert("You are not login please login first!")
      next('/loginakm');
    } else {
      // If user is logged in, proceed to the route
      // const loginUser = JSON.parse(sessionStorage.getItem('login_user'));
      // if(loginUser.userIsSubbed === false) {
      //   alert("You are not subscriber please subscribe first!")
      //   next('/subscribe')
      //   console.log("you are in subscribe page")
      // } else {
      //   console.log("subscribe page to home")
      //   next();
      // }
      next();
    }
  } 
  else if(to.meta.requiresAdmin == true && !admin){
    next('/admin/login');
  }
  else {

    // If the route does not require authentication, proceed to the route
    // console.log("you will go to main")
    next();
    // const user = sessionStorage.getItem('login_user');
    // if (to.name === 'profile' && user) {
    //     next('/userdashboard');
    // } else {
    //   next();
    // }
  }
  
  
}
)




export default router
