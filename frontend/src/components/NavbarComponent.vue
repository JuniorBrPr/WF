<script>
export default {
  name: "NavbarComponent"
}
</script>

<template>
  <nav class="navbar navbar-expand-md bg-body py-3">
    <div class="container">
      <ul class="navbar-nav me-auto">
        <li class="nav-item ">
          <router-link class="nav-link m-1 item" to="/"><h2>Home</h2></router-link>
        </li>
        <li class="nav-item ">
          <a class="nav-link m-1 item" href="#">
            <h2 class="">My account</h2>
          </a>
        </li>
        <li class="nav-item ">
          <a class="nav-link m-1 item" href="#">
            <h2>My Bookings</h2>
          </a>
        </li>
        <li class="nav-item">
          <div class="nav-item dropdown m-1">
            <a class="nav-link item" aria-expanded="false" data-bs-toggle="dropdown" href="#">
              <h2>Cabins </h2>
            </a>
            <div class="dropdown-menu">
              <router-link class="nav-link dropdown-item" to="/overView31">All cabins overview</router-link>
              <router-link class="nav-link dropdown-item" to="/overView32">Cabins edit(components)</router-link>
              <a class="dropdown-item" href="#">Cabins edit(routed)</a>
              <a class="dropdown-item" href="#">Cabins edit(managed)</a>
            </div>
          </div>
        </li>
      </ul>

      <router-link class="nav-link btn btn-lg btn-success m-1" to="/sign-up">Sign up</router-link>
      <router-link class="nav-link btn btn-lg btn-success m-1" to="/sign-in">Log in</router-link>
<!--      <button class="btn btn-lg btn-success m-1" type="button">Sign up</button>-->
<!--      <button class="btn btn-lg btn-success m-1" type="button">Log in</button>-->
    </div>
  </nav>
</template>

<style scoped>
.item:hover, .dropdown-item:hover, .item:focus{
  background-color: lightgreen;
}

.nav-link.router-link-active {
  /* Highlight styles for active link */
  background-color: #007bff; /* Change this to your preferred highlight color */
  color: #fff; /* Change this to your preferred text color */
}
</style>