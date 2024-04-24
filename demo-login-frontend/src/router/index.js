import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/views/Login-View.vue';
import Register from '@/views/Register-View.vue';
import Dashboard from '@/views/Dashboard-View.vue';

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
