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
    <CabinsDetail :selected="selectedCabin" @delete="onDelete"/>
  </div>
</template>

<script>
import {Cabin} from '@/models/cabin.js';
import CabinsDetail from "@/components/cabins/CabinsDetail32.vue"

export default {
  name: "cabinsOverview",
  components: {
    CabinsDetail,
  },
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
  },
  methods: {
    selectCabin(cabin) {
      if (this.selectedCabin !== cabin) {
        this.selectedCabin = cabin;
      } else {
        this.selectedCabin = null;
      }
    },
    onNewCabin() {
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      let newCabin = Cabin.createSampleCabin(this.lastId)

      this.cabins.push(
          newCabin
      )
      this.selectedCabin = newCabin
    },
    onDelete() {
      let index = this.cabins.indexOf(this.selectedCabin);
      if(index > -1){
        this.cabins.splice(index, 1)
        this.selectedCabin = null
      }
    }
  }
}
</script>


<style scoped>
.card {
  min-width: 200px;
  min-height: fit-content;
}

.card-img-top {
  width: 150px;
  height: 150px;
}
</style>
