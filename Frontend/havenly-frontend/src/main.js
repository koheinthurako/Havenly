import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap';
import './assets/css/main.scss'
import './assets/js/main.js'


loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .mount('#app')
