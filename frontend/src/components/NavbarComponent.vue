<template>
  <nav class="navbar navbar-expand-md bg-body py-3">
    <div class="container">
      <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navcol-4"><span class="visually-hidden">Toggle navigation</span><span
          class="navbar-toggler-icon"></span></button>
      <div id="navcol-4" class="collapse navbar-collapse flex-grow-0 order-md-first">
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
          <li class="nav-item dropdown">
            <div class="nav-link item cursor-pointer" :class="{active: this.cabinsActive}"
                 data-bs-toggle="dropdown" aria-expanded="false">
              <h3 class="pe-auto dropdown-toggle">Cabins </h3>
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
              <router-link class="nav-link dropdown-item" to="/cabins/overView34" :class="{ active: isActive }">
                Cabins edit(guarded)
              </router-link>
              <router-link class="nav-link dropdown-item" to="/cabins/overView37" :class="{ active: isActive }">
                Cabins edit(REST)
              </router-link>
            </div>
          </li>
        </ul>
        <div class="d-md-none my-2">
          <router-link class="btn btn-lg m-1" to="/sign-up">Sign up</router-link>
          <router-link class="btn btn-lg m-1" to="/sign-in">Log in</router-link>
        </div>
      </div>
      <div class="d-none d-md-block">
        <router-link class="btn btn-lg m-1" to="/sign-up">Sign up</router-link>
        <router-link class="btn btn-lg m-1" to="/sign-in">Log in</router-link>
      </div>
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
  methods: {
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