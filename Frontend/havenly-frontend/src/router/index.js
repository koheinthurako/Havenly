
import { createRouter, createWebHistory } from 'vue-router'
import HomeIndex from '../components/mainIndexVue.vue'
import tempPackage from '../components/Temp_Collection/tempForPackage.vue'

// Documentation ?
// import tempDoc from '../components/Temp_Collection/tempForDoc.vue'
import register from '../components/Login_&_Register/registerVue.vue'
import userDashboard from '../components/User_Dashboard/indexUserDashboard.vue'
import testingPage from '../components/For_Testing/testingOne.vue'
import login from '../views/LoginView.vue'
import registerakm from '../views/RegisterView.vue'
import AdminView from '../views/AdminView.vue'
import AdminLoginView from '../views/AdminLoginView.vue'
import AdminBanList from '../views/AdminBanList.vue'
import SubscribeVue from '@/views/SubscribeVue.vue'
import CreditCard from '@/views/CreditCard.vue'

// For Posts
import postDetailView from '@/views/PostDetailView.vue'
import AllPostView from '@/views/AllPostView.vue'
import AdminPost from '@/views/AdminPost.vue'
import About from '../views/AboutVue.vue'
import Swal from 'sweetalert2'



const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeIndex
  },

  // change with temp For Package
  {
    path: '/package/details',
    name: 'tempPackage',
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
    path: '/Home',
    redirect: '/',
  },
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
    path: '/tempDoc',
    name: 'tempDoc',
    component: tempDoc
  },
  {
    path: '/packages/payment',
    name: 'CreditCard',
    component: CreditCard
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
    path: '/admin',
    name: 'Admin',
    component: AdminView,
    meta: { requiresAdmin: true }
  },
  {
    path: '/admin/login',
    name: 'AdminLogin',
    component: AdminLoginView
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
  } else if (to.path === '/packages/payment') {
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