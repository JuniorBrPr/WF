<script>
export default {
  name: "NavbarComponent"
}
</script>

<template>
  <nav class="navbar navbar-expand-md bg-body py-3">
    <div class="container">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active bg-success m-1" href="#">
            <h2>First Item</h2>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link  bg-success m-1" href="#">
            <h2>First Item</h2>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link  bg-success m-1" href="#">
            <h2>First Item</h2>
          </a>
        </li>
        <li class="nav-item">
          <div class="nav-item dropdown bg-success m-1">
            <a class=" nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#">
              <h2>Cabins </h2>
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">First Item</a>
              <a class="dropdown-item" href="#">Second Item</a>
              <a class="dropdown-item" href="#">Third Item</a>
            </div>
          </div>
        </li>
      </ul>


      <button class="btn btn-lg btn-success m-1" type="button">Button</button>
      <button class="btn btn-lg btn-success m-1" type="button">Button</button>
    </div>
  </nav>
</template>

<style scoped>

</style>