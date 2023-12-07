<template>
  <div>
    <form @submit.prevent="signIn">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div class="form-group">
        <button type="submit">Sign In</button>
      </div>
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
      <p v-if="token">Token: {{ token }}</p>
    </form>
  </div>
</template>

<script>
import { inject } from 'vue';

export default {
  name: 'SignIn',
  data() {
    return {
      email: '',
      password: '',
      errorMessage: '',
      token: null,
    };
  },
  setup() {
    const sessionService = inject('sessionSbService');
    const isAuthenticated = sessionService.isAuthenticated;

    return {
      sessionService,
      isAuthenticated,
    };
  },
  methods: {
    async signIn() {
      try {
        const response = await this.sessionService.asyncSignIn(this.email, this.password);
        if (response) {
          this.token = response.token;
          this.errorMessage = '';
        } else {
          this.token = null;
          this.errorMessage = 'Invalid email or password. Please try again.';
        }
      } catch (error) {
        console.error('Error signing in:', error);
        this.errorMessage = 'An error occurred during sign-in. Please try again later.';
      }
    },
  },
};
</script>

<style scoped>
.error-message {
  color: red;
}
</style>
