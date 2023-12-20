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
      <p v-if="errorMessage !== null && errorMessage !== undefined" class="error-message">{{ errorMessage }}</p>
      <p v-if="token !== null && token !== undefined">Token: {{ token }}</p>
    </form>
  </div>
</template>

<script>
import { inject, ref, watch } from 'vue';
import { useRoute } from 'vue-router';

export default {
  name: 'SignIn',
  inject: ['sessionService'],
  setup() {
    const sessionService = inject('sessionService');
    const route = useRoute();
    const email = ref('');
    const password = ref('');
    const errorMessage = ref('');
    const token = ref(null);
    const isAuthenticated = ref(false);

    if (sessionService && sessionService.isAuthenticated) {
      isAuthenticated.value = sessionService.isAuthenticated();
    }

    watch(() => route.query.signOff, (newValue) => {
      if (newValue) {
        sessionService.signOut();
      }
    });

    const signIn = async () => {
      try {
        if (!sessionService || !sessionService.asyncSignIn) {
          throw new Error('asyncSignIn method not available');
        }

        const response = await sessionService.asyncSignIn(email.value, password.value);

        if (response) {
          token.value = response.token;
          errorMessage.value = '';
        } else {
          token.value = null;
          errorMessage.value = 'Invalid email or password. Please try again.';
        }
      } catch (error) {
        errorMessage.value = 'An error occurred during sign-in. Please try again later.';
        console.error('Error signing in:', error.message);
      }
    };

    return {
      email,
      password,
      errorMessage,
      token,
      isAuthenticated,
      signIn,
    };
  },
};
</script>

<style scoped>
.error-message {
  color: red;
}
</style>
