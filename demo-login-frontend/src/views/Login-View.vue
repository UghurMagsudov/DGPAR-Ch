<template>
  <div class="login-container">
    <div class="form-group">
      <label for="username" class="form-label">Username</label>
      <input type="text" id="username" v-model="username" placeholder="Enter your username" class="form-control">
    </div>
    <div class="form-group">
      <label for="password" class="form-label">Password</label>
      <input type="password" id="password" v-model="password" placeholder="Enter your password" class="form-control">
    </div>
    <p v-if="error" class="error">{{ error }}</p>
    <button @click="login" class="btn btn-primary w-100">Login</button>
  </div>
</template>

<script>
import {loginUser} from '@/service/auth.service';

export default {
  data() {
    return {
      username: '',
      password: '',
      error: '',
    };
  },
  methods: {
    async login() {
      this.error = '';
      try {
        const token = await loginUser({
          username: this.username,
          password: this.password,
        });
        localStorage.setItem('token', token);
        this.$router.push('/dashboard');
      } catch (error) {
        if (error.response) {
          if (error.response.status === 400) {
            this.error = 'Username or password incorrect';
          } else {
            this.error = error.response.data.message || 'An error occurred during login';
          }
        } else if (error.request) {
          this.error = 'No response from server. Please check your network connection.';
        } else {
          this.error = 'Error during login attempt: ' + error.message;
        }
        console.error('Login error:', error);
      }
    }
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.form-label {
  display: block;
  margin-bottom: 5px;
}

.btn-primary {
  margin-top: 10px;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>
