import { createApp } from 'vue';
import { router } from "@/router";
import App from './AppComponent44.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.js';

const app = createApp(App);

// Use Vue Router
app.use(router);

// Mount the app to the HTML element with id="app"
app.mount('#app');
