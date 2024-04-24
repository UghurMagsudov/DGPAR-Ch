<template>
  <div class="register-container">
    <h1>Register</h1>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" :class="{ 'is-invalid': v$.username.$error }" required>
        <span v-if="v$.username.$error"
              class="error">Username is required and must be between 6 and 20 characters.</span>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" :class="{ 'is-invalid': v$.password.$error }" required>
        <span v-if="v$.password.$error"
              class="error">Password is required and must be between 8 and 20 characters.</span>
      </div>
      <button type="submit" class="btn btn-primary">Register</button>
      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script>
import {ref} from 'vue';
import {useVuelidate} from '@vuelidate/core';
import {required, minLength, maxLength} from '@vuelidate/validators';
import {registerUser} from '@/service/auth.service';

export default {
  setup() {
    const username = ref('');
    const password = ref('');
    const error = ref(null);

    const rules = {
      username: {required, minLength: minLength(6), maxLength: maxLength(20)},
      password: {required, minLength: minLength(8), maxLength: maxLength(20)},
    };

    const v$ = useVuelidate(rules, {username, password});

    const register = async () => {
      const isFormValid = await v$.value.$validate();
      if (!isFormValid) {
        error.value = 'Please correct the errors before submitting.';
        return;
      }
      try {
        const response = await registerUser({username: username.value, password: password.value});
        console.log('Registration successful', response);
        alert('Registration successful! Please login.');
        localStorage.setItem('token', response);
        this.$router.push('/login');
      } catch (err) {
        if (err.request) {
          // The request was made but no response was received
          error.value = 'No response from server. Please check your network connection.';
        } else {
          error.value = 'Failed to register. Please try again.';
        }

      }
    };

    return {username, password, error, register, v$};
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.error {
  display: block;
  color: red;
}

.is-invalid {
  border-color: red;
}
</style>
