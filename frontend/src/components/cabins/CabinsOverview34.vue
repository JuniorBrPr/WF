<template>
  <div class="container text-center">
    <div class="list-group list-group-horizontal position-relative overflow-auto">
      <div class="card list-group-item border-2" v-for="cabin in cabins"
           :key="cabin.id"
           @click="selectCabin(cabin)"
           :class="{ 'border-success': selectedCabin === cabin }">
        <img class="card-img-top rounded mx-auto d-block" :src="cabin.getImage()" alt="Card image cap">
        <div class="card-body">
          <p class="card-info">{{ cabin.type }}</p>
          <p class="card-info">{{ cabin.location }}</p>
        </div>
      </div>
    </div>
    <button class="btn btn-primary" @click="onNewCabin()">
      New Cabin
    </button>
    <router-view :cabins="cabins" @delete="onDelete"/>
  </div>
</template>

<script>
import {Cabin} from '@/models/cabin.js';

export default {
  name: "cabinsOverview34",
  components: {},
  data() {
    return {
      cabins: [],
      lastId: 10000,
      selectedCabin: null,
      isActive: true,
    }
  },

  created() {
    for (let i = 0; i < 8; i++) {
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      this.cabins.push(
          Cabin.createSampleCabin(this.lastId)
      )
    }
    this.selectedCabin = this.$route.params.id ? this.findSelectedCabinFromRoute(this.$route) : null;
  },

  methods: {
    selectCabin(cabin) {
      if (this.selectedCabin !== cabin) {
        this.selectedCabin = cabin;
        this.$router.push(this.$route.matched[1].path + '/' + cabin.id);
      } else {
        this.selectedCabin = null;
        this.$router.push(this.$route.matched[1].path);
      }
    },
    onNewCabin() {
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      let newCabin = Cabin.createSampleCabin(this.lastId)

      this.cabins.push(
          newCabin
      )
      this.selectedCabin = newCabin
      this.$router.push("/cabins/overView34/"+ this.lastId);
    },
    onDelete() {
      let index = this.cabins.indexOf(this.selectedCabin);
      if (index > -1) {
        this.cabins.splice(index, 1)
        this.selectedCabin = null
      }
    },
    findSelectedCabinFromRoute($route) {
      let cabinId = $route.params.id;
      this.cabins.forEach(cabin => {
        if (cabin.id === cabinId) {
          return cabin;
        }
      })
      return null;
    }
  }
}
</script>

<style scoped>
.card {
  min-width: 200px;
  min-height: fit-content;
  /*padding: 2px;*/
}
.card-img-top {
  width: 150px;
  height: 150px;
}
</style>
