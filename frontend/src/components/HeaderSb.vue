<script>

export default {
  inject: ['sessionService'],

  name: "HeaderSb",
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
  },
  setup() {
  }
}
</script>

<template>
  <div class="bg-success row justify-content-between m-0">
    <div class="col">
      <div class="row">
        <img class="img-fluid" src="../assets/summer.png" alt="logo">
      </div>
    </div>
    <div class="col col-10">
      <div class="row">
        <h3 class="text-center">Play Aan Zee</h3>
        <h6 class="text-start">Today is {{ getCurrentDate }}
          <div class="text-end">Chill out and feel good</div>
        </h6>
        <h3>Welcome {{ sessionService.currentAccount ? sessionService.currentAccount.name : 'Visitor' }}!</h3>
      </div>
    </div>
    <div class="col align-self-end">
      <div class="row p-2 justify-content-end ">
        <img class="img-fluid align-self-end" src="../assets/sailing-boat.png" alt="logo">
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>