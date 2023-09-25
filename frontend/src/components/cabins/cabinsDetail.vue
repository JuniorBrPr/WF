<template>
<!--  <div class="cabins-detail container">-->
<!--    <div v-if="selectedCabin">-->
<!--      <h2>Cabin details {{ selectedCabin.id }}</h2>-->
<!--      <img class="card-image" :src="selectedCabin.image"/>-->
<!--      <table class="table">-->
<!--        <tr>-->
<!--          <td>Type:</td>-->
<!--          <td>-->
<!--            <select v-model="selectedCabin.type">-->
<!--              <option v-for="type in Cabin.typeList" :key="type">-->
<!--                {{ type }}-->
<!--              </option>-->
<!--            </select>-->
<!--          </td>-->
<!--        </tr>-->
<!--        <tr>-->
<!--          <td>Location:</td>-->
<!--          <td>-->
<!--            <select v-model="selectedCabin.location">-->
<!--              <option v-for="location in Cabin.locationList" :key="location">-->
<!--                {{ location }}-->
<!--              </option>-->
<!--            </select>-->
<!--          </td>-->
<!--        </tr>-->
<!--        <tr>-->
<!--          <td>Description:</td>-->
<!--          <td>-->
<!--            <input v-model="selectedCabin.description"/>-->
<!--          </td>-->
<!--        </tr>-->
<!--        <tr>-->
<!--          <td>Image</td>-->
<!--          <td>-->
<!--            <select v-model="selectedCabin.image">-->
<!--              <option v-for="image in Cabin.imageList" :key="image">-->
<!--                {{ image }}-->
<!--              </option>-->
<!--            </select>-->
<!--          </td>-->
<!--        </tr>-->
<!--        <tr>-->
<!--          <td>Price per week:</td>-->
<!--          <td>-->
<!--            <input type="number" v-model="selectedCabin.pricePerWeek">-->
<!--          </td>-->
<!--        </tr>-->
<!--        <tr>-->
<!--          <td>Total availability:</td>-->
<!--          <td>-->
<!--            <input type="number" v-model="selectedCabin.numAvailable">-->
<!--          </td>-->
<!--        </tr>-->
<!--      </table>-->
<!--      <button type="button" class="btn btn-danger" @click="onDelete">Delete</button>-->
<!--    </div>-->
<!--    <div v-else>-->
<!--      <p>Please select a cabin to view details.</p>-->
<!--    </div>-->
<!--  </div>-->
  <p>Disabled error</p>
</template>

<script>

import {Cabin} from "@/models/cabin";

export default {
  name: "cabinsDetail",
  computed: {
    Cabin() {
      return Cabin
    }
  },
  props: {
    selectedCabin: Object,
  },
  methods: {
    onDelete(){
      this.$emit('delete', this.selectedCabin)
    }
  }
}
</script>

<style scoped>
select {
  max-width: 400px;
}
</style>