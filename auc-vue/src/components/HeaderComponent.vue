<script>
export default {
  name: "HeaderComponent"
}
</script>

<template>
  <div class="navbar navbar-expand bg-success">
    <div>
      <img class="logo m-2" src="../assets/summer.png" alt="logo">
    </div>
    <div class="flex-grow-1 m-2">
      <div>
        <h1 class="text-center">Play Aan Zee</h1>
      </div>
      <div class="text-end">
        <h6>Chill out and feel good</h6>
      </div>
    </div>
    <div>
      <img class="logo m-2" src="../assets/sailing-boat.png" alt="logo">
    </div>
  </div>
</template>

<style scoped>
.logo {
  width: 75px;
  height: 75px;
}
</style>