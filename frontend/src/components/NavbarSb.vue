<template>
  <nav class="navbar navbar-expand-md bg-body py-3">
    <div class="container">
      <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navcol-4">
        <span class="visually-hidden">Toggle navigation</span>
        <span class="navbar-toggler-icon"></span>
      </button>
      <div id="navcol-4" class="collapse navbar-collapse flex-grow-0 order-md-first">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <a class="nav-link m-1 item disabled" href="#">
              <h3 class="">My account</h3>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link m-1 item disabled" href="#">
              <h3>My Bookings</h3>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link m-1 item" href="#">
              <router-link class="nav-link dropdown-item" to="/cabins/overView37" :class="{ active: isActive }">
               <h3>Cabins</h3>
              </router-link>
            </a>
          </li>
        </ul>

        <div class="d-md-none my-2">
          <router-link v-if="!isAuthenticated" class="btn btn-lg m-1" to="/sign-up">Sign up</router-link>
          <router-link v-if="!isAuthenticated" class="btn btn-lg m-1" to="/sign-in">Log in</router-link>
          <router-link v-if="isAuthenticated" class="btn btn-lg m-1" to="/sign-out">Log out</router-link>
        </div>
        <div class="d-none d-md-block">
          <router-link v-if="!isAuthenticated" class="btn btn-lg m-1" to="/sign-up">Sign up</router-link>
          <router-link v-if="!isAuthenticated" class="btn btn-lg m-1" to="/sign-in">Log in</router-link>
          <router-link v-if="isAuthenticated" class="btn btn-lg m-1" to="/sign-out">Log out</router-link>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { inject, ref, watch } from "vue";

export default {
  inject: ['sessionService'],
  name: "NavbarSb",

  setup() {
    const sessionService = inject('sessionService');
    const isAuthenticated = ref(false);
    const cabinsActive = ref(false);

    const checkAuthentication = () => {
      isAuthenticated.value = sessionService._currentAccount !== null;
    };

    // Initial check on component mount
    checkAuthentication();

    watch(() => sessionService._currentAccount, () => {
      checkAuthentication();
    });

    watch('$route', (to) => {
      cabinsActive.value = to.path.includes('cabins');
    });

    return {
      isAuthenticated,
      cabinsActive,
    };
  },
};
</script>

<style scoped>
.item:hover,
.dropdown-item:hover,
.item:focus,
.active,
.router-link-active,
.router-link-exact-active {
  background-color: lightgreen;
}

.cursor-pointer {
  cursor: default;
}
</style>