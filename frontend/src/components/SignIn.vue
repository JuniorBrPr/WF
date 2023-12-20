<template>
  <div>
    <form @submit.prevent="signIn">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" class="form-control" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" class="form-control" required>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary">Sign In</button>
      </div>
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
      <p v-if="token">Token: {{ token }}</p>
    </form>
  </div>
</template>

<script>
import { inject } from 'vue';
import {useRoute} from "vue-router";

export default {
  inject: ['sessionService'],
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
    const route = useRoute();

    const isAuthenticated = sessionService.isAuthenticated;

    // Check for the signOff query parameter in the route
    if (route.query.signOff) {
      sessionService.signOut(); // Call signOut method if signOff parameter is present
    }

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
          console.log('SignIn successful:', response);
          console.log('Token:', response.token);
          this.sessionService.saveTokenIntoBrowserStorage(response.account, response.token);
          console.log('Token saved into browser storage.');
          this.errorMessage = '';
        } else {
          this.token = null;
          this.errorMessage = 'Invalid email or password. Please try again.';
        }
      } catch (error) {
        console.error('RequestError signing in:', error);
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
