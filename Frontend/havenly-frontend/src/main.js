import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
<<<<<<< HEAD
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/bootstrap/dist/js/bootstrap.bundle'
=======
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap';
import './assets/css/main.scss'
>>>>>>> abc0e72394c70d96d8ac881e81e5fb6a8d6e56dc

loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .mount('#app')
