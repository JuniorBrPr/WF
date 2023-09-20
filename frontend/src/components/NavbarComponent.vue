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
          <a class="nav-link active m-1 item" href="#">
            <h2>Home</h2>
          </a>
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
              <a class="dropdown-item" href="#">All cabins overview</a>
              <a class="dropdown-item" href="#">Cabins edit(components)</a>
              <a class="dropdown-item" href="#">Cabins edit(routed)</a>
              <a class="dropdown-item" href="#">Cabins edit(managed)</a>
            </div>
          </div>
        </li>
      </ul>

      <button class="btn btn-lg btn-success m-1" type="button">Sign up</button>
      <button class="btn btn-lg btn-success m-1" type="button">Log in</button>
    </div>
  </nav>
</template>

<style scoped>
.item:hover, .dropdown-item:hover, .item:focus{
  background-color: lightgreen;
}
</style>