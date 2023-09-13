<script>
export default {
  name: "HeaderComponent",
  data() {
    return {
      dateToday: String,
    }
  },
  computed: {
    getCurrentDate() {
      const weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December"];
      const date = new Date();
      return `${weekday[date.getDay()]}, ${date.getDate()} ${months[date.getMonth()]} ${date.getFullYear()}`
    }
  }
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
      <div class="d-flex justify-content-between">
        <h6 class="text-start">Today is <br/>{{ getCurrentDate }}</h6>
        <h6 class="text-end">Chill out and feel good</h6>
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