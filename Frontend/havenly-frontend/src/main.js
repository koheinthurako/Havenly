import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'

// Import Bootstrap styles Need both Css and JS
import 'bootstrap/dist/css/bootstrap.min.css'
import './assets/css/main.scss';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';


// Import SweetAlert styles
import 'sweetalert2/dist/sweetalert2.min.css'

// Import Font Awesome styles
import '@fortawesome/fontawesome-free/css/all.css'

// Import main SCSS file
import '../src/assets/css/main.scss' // Adjust the path as needed




loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .mount('#app')
