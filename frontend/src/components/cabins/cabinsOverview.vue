<template>
  <div class="card-container">
  <card class="card" v-for="cabin in cabins"
        :key="cabin.id"
        @click="selectCabin(cabin)"
        :class="{ 'selected': selectedCabin === cabin }">
    <img class="card-image" :src="cabin.image"/>
    <p class="card-info" >{{ cabin.type }}</p>
    <p class="card-info" >{{ cabin.location}}</p>
  </card>
  </div>
  <button @click="onNewCabin()">
    New Cabin
  </button>
  <div class="sub-panel">
    <CabinsDetail :selectedCabin="selectedCabin" @delete="onDelete"/>
  </div>
</template>

<script>
import {Cabin} from '@/models/cabin.js';
import CabinsDetail from "@/components/cabins/cabinsDetail.vue"; // Import the CabinsDetail component

export default  {
  name: "cabinsOverview",
  components: {
    CabinsDetail,
  },
  data() {
    return {
      cabins: [],
      lastId: 10000,
      selectedCabin: null,
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
    selectCabin(cabin){
      if(this.selectedCabin !== cabin){
        this.selectedCabin = cabin;
      } else{
        this.selectedCabin = null;
      }
    },
    onNewCabin(){
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      let newCabin = Cabin.createSampleCabin(this.lastId)

      this.cabins.push(
          newCabin
      )
      this.selectedCabin = newCabin
    },
    onDelete(){
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
.card-container {
  display: flex;
  overflow-x: auto; /* Enable horizontal scrolling */
  white-space: nowrap; /* Prevent card wrapping to the next line */
  gap: 10px; /* Add some spacing between cards */
  width: 100%; /* Specify a width for the container */
}

.card {
  background-color: #fff;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  overflow: hidden; /* Crop overflowing content */
  text-align: center;
}

.card-image {
  max-height: 200px; /* Set a maximum height for the image */
  overflow: hidden; /* Crop the image if it's too tall */
}

.card-image img {
  width: 100%; /* Ensure the image takes up the full width of its container */
  height: auto; /* Maintain the image's aspect ratio */
}

.card-info {
  padding: 10px;
}

.selected {
  border: 2px solid blue;
}
</style>
