<template>
  <div class="dashboard-container">
    <h1>Welcome to Your Dashboard!</h1>
    <p>This is a secure area of the application. Only authenticated users can access this page.</p>
    <button @click="logout" class="btn btn-danger">Logout</button>
  </div>
</template>

<script>
import {testAuthorization} from '@/service/test.service';

export default {
  name: 'Dashboard-View',
  methods: {
    logout() {
      localStorage.removeItem('token');
      this.$router.push('/login');
    }
  },
  mounted() {
    testAuthorization();
  }
}
</script>

<style scoped>
.dashboard-container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  text-align: center;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.btn {
  margin-top: 20px;
}
</style>
