<template>
  <nav class="navbar navbar-expand-md bg-body py-3">
    <div class="container">
      <ul class="navbar-nav me-auto">
        <li class="nav-item ">
          <router-link class="nav-link m-1 item" :class="{ active: isActive }" to="/"><h2>Home</h2></router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link m-1 item disabled" href="#">
            <h2 class="">My account</h2>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link m-1 item disabled" href="#">
            <h2>My Bookings</h2>
          </a>
        </li>
        <li class="nav-item dropdown">
          <div class="nav-link item cursor-pointer"
               :class="{active: this.cabinsActive}"
               data-bs-toggle="dropdown">
            <h2 class="pe-auto dropdown-toggle">Cabins </h2>
          </div>
          <div class="dropdown-menu">
            <router-link class="nav-link dropdown-item" :class="{ active: isActive }" to="/cabins/overView31">
              All cabins overview
            </router-link>
            <router-link class="nav-link dropdown-item" to="/cabins/overView32" :class="{ active: isActive }">
              Cabins edit(components)
            </router-link>
            <router-link class="nav-link dropdown-item" to="/cabins/overView33" :class="{ active: isActive }">
              Cabins edit(routed)
            </router-link>
            <a class="dropdown-item disabled" href="#">Cabins edit(managed)</a>
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
      display: false,
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