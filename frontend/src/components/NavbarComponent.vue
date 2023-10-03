<template>
  <nav class="navbar navbar-expand-md bg-body ">
    <div class="container">
      <ul class="navbar-nav me-auto">
        <li class="nav-item ">
          <router-link class="nav-link m-1 item" :class="{ active: isActive }" to="/"><h3>Home</h3></router-link>
        </li>
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
        <li class="nav-item dropdown" >
          <div class="nav-link item cursor-pointer" :class="{active: this.cabinsActive}"
               data-bs-toggle="dropdown" aria-expanded="false">
            <h3 class="pe-auto">Cabins </h3>
          </div>
          <div class="dropdown-menu" @focusout="this.hideDropdown">
            <router-link class="nav-link dropdown-item" :class="{ active: isActive }" to="/cabins/overView31">
              All cabins overview
            </router-link>
            <router-link class="nav-link dropdown-item" to="/cabins/overView32" :class="{ active: isActive }">
              Cabins edit(components)
            </router-link>
            <router-link class="nav-link dropdown-item" to="/cabins/overView33" :class="{ active: isActive }">
              Cabins edit(routed)
            </router-link>
            <router-link class="nav-link dropdown-item" to="/cabins/overView34" :class="{ active: isActive }">
              Cabins edit(guarded)
            </router-link>
          </div>
        </li>
      </ul>

      <router-link class="btn btn-lg m-1" to="/sign-up">Sign up</router-link>
      <router-link class="btn btn-lg m-1" to="/sign-in">Log in</router-link>
    </div>
  </nav>
</template>

<script>

export default {
  name: "NavbarComponent",

  data() {
    return {
      cabinsActive: false,
    }
  },
  methods:  {
    hideDropdown(){
      this.$el.querySelector(".dropdown-menu").classList.remove('show');
    }
  },

  watch: {
    $route() {
      this.cabinsActive = this.$route.path.includes('cabins');
    }
  },
}
</script>

<style scoped>
.item:hover, .dropdown-item:hover, .item:focus, .active, .router-link-active, .router-link-exact-active {
  background-color: lightgreen;
}

.cursor-pointer {
  cursor: default;
}
</style>