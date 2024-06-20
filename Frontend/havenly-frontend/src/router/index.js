
import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'

// Documentation ?
// import tempDoc from '../components/Temp_Collection/tempForDoc.vue'
// import register from '../components/Login_&_Register/registerVue.vue'
import userDashboard from '../components/User_Dashboard/indexUserDashboard.vue'

import login from '../views/LoginView.vue'
import register from '../views/RegisterView.vue'
import AdminLoginView from '../views/AdminLoginView.vue'
import SubscribeVue from '@/views/SubscribeVue.vue'
import Payment from '@/views/Payment.vue'
import ForgotPassword from '@/views/ForgotPassword.vue'
import ResetPassword from '@/views/ResetPassword.vue'

// For Posts
import postDetailView from '@/views/PostDetailView.vue'
import AllPostView from '@/views/AllPostView.vue'
import uploadedAllPosts from '@/components/User_Dashboard/Dashboard_Categories/uploadedAllPosts.vue'
import About from '../views/AboutVue.vue'
import Swal from 'sweetalert2'
import PostsByLocation from '@/views/PostsByLocation.vue'
import MainLocationPosts from '@/views/MainLocationPosts.vue'

import indexAdminDashboard from '@/views/indexAdminDashboard.vue'

import testingPage from '@/components/For_Testing/testingOne.vue'

const routes = [
  {
    path: '/testingPage',
    name: 'testingPage',
    component: testingPage,
  },
  {
    path: '/',
    name: 'home',
    component: HomeIndex
  },
  {
    path: '/',
    redirect: '/home',
  },

  // change with temp For Package
  {
    path: '/package',
    name: 'tempPackage',
    component: tempPackage
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/subscribe',
    name: 'SubscribeVue',
    component: SubscribeVue
  },
  {
    path: '/userdashboard',
    name: 'User_dashboard',
    component: userDashboard,
    meta: { requiresAuth: true }
  },
  {
    path: '/payment',
    name: 'Payment',
    component: Payment
  },
  {
    path: '/post/alluserposts',
    name: 'uploadedAllPosts',
    component: uploadedAllPosts
  },
  {
    path: '/post/detail/view/:id',
    name: 'postDetailView',
    component: postDetailView
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/admin/login',
    name: 'AdminLogin',
    component: AdminLoginView,
    // meta: { requiresAdmin: true }
  },
  {
    path: '/admin/dashboard',
    name: 'AdminDashboard',
    component: indexAdminDashboard,
    meta: { requiresAdmin: true }
  },
  {
    path: '/all/posts/of/:postType',
    name: 'AllPostView',
    component: AllPostView,
  },
  {
    path: '/all/posts/postsbylocation/:locationId',
    name: 'PostsByLocation',
    component: PostsByLocation,
  },
  {
    path: '/all/posts/mainLocationPosts/:locationId',
    name: 'MainLocationPosts',
    component: MainLocationPosts,
  },
  {
    path: '/forgot',
    name: 'ForgotPassword',
    component: ForgotPassword
  },
  {
    path: '/akmakmset',
    name: 'ResetPassword',
    component: ResetPassword
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from, next) => {

  const admin = sessionStorage.getItem('admin_user');
  const user = sessionStorage.getItem('login_user');
  const subUser = JSON.parse(sessionStorage.getItem('sub_user'));

  // Check if the route requires authentication
  if (to.path === '/subscribe') {
    if (!user) {
      Swal.fire({
        title: 'Login Required',
        text: 'Please login first to subscribe!',
        icon: 'info',
        customClass: {
          confirmButton: 'myCustomButton'
        },
        buttonsStyling: false,
        allowOutsideClick: false,
        allowEscapeKey: false
      }).then(() => {
        next('/login');
      });
    } else if (subUser && subUser.packageType) {
      Swal.fire({
        title: 'Already Subscribed',
        text: 'You are already a subscriber!',
        icon: 'info',
        customClass: {
          confirmButton: 'myCustomButton'
        },
        buttonsStyling: false,
        allowOutsideClick: false,
        allowEscapeKey: false
      }).then(() => {
        next(false);
      });
    } else {
      next();
    }
  } else if (to.path === '/payment') {
    if (!user) {
      Swal.fire({
        title: 'Login Required',
        text: 'Please login first to proceed with the payment!',
        icon: 'info',
        customClass: {
          confirmButton: 'myCustomButton'
        },
        buttonsStyling: false,
        allowOutsideClick: false,
        allowEscapeKey: false
      }).then(() => {
        next('/login');
      });
    } else if (!subUser) {
      Swal.fire({
        title: 'Subscribe First',
        text: 'You must be subscribed to buy our packages!',
        icon: 'error',
        customClass: {
          confirmButton: 'myCustomButton'
        },
        buttonsStyling: false,
        allowOutsideClick: false,
        allowEscapeKey: false
      }).then(() => {
        next('/subscribe');
      });
    } else if (subUser.packageType !== 'Free Trial' && subUser.availPosts > 0) {
      Swal.fire({
        title: 'Alread Purchased',
        text: 'Please use your package until 0 post!',
        icon: 'error',
        customClass: {
          confirmButton: 'myCustomButton'
        },
        buttonsStyling: false,
        allowOutsideClick: false,
        allowEscapeKey: false
      }).then(() => {
        next(false);
      });
    } else {
      next();
    }
  } else if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!user) {
      Swal.fire({
        title: 'Login Required',
        text: 'You are not logged in. Please log in first!',
        icon: 'error',
        customClass: {
          confirmButton: 'myCustomButton'
        },
        buttonsStyling: false,
        allowOutsideClick: false,
        allowEscapeKey: false
      }).then(() => {
        next('/login');
      });
    } else {
      next();
    }
  } else if (to.meta.requiresAdmin && !admin) {
    Swal.fire({
      title: 'Admin Access Required',
      text: 'Please login your admin account first!',
      icon: 'error',
      customClass: {
        confirmButton: 'myCustomButton'
      },
      buttonsStyling: false,
      allowOutsideClick: false,
      allowEscapeKey: false
    }).then(() => {
      next('/admin/login');
    });
  } else {
    next();
  }
}
)
// });

export default router