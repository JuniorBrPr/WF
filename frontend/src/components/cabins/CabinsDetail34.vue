<template>
  <div v-if="this.selectedCabin" class="cabins-detail container">
    <h2>Cabin details {{ selectedCabin.id }}</h2>
    <img class="card-image" alt="Cabin image" :src="selectedCabin.image"/>
    <table class="table">
      <tr>
        <td>Type:</td>
        <td>
          <select v-model="/* eslint-disable vue/no-mutating-props */selectedCabin.type">
            <option v-for="type in Cabin.typeList" :key="type">
              {{ type }}
            </option>
          </select>
        </td>
      </tr>
      <tr>
        <td>Location:</td>
        <td>
          <select v-model="selectedCabin.location">
            <option v-for="location in Cabin.locationList" :key="location">
              {{ location }}
            </option>
          </select>
        </td>
      </tr>
      <tr>
        <td>Description:</td>
        <td>
          <input v-model="selectedCabin.description"/>
        </td>
      </tr>
      <tr>
        <td>Image</td>
        <td>
          <select v-model="selectedCabin.image">
            <option v-for="image in Cabin.imageList" :key="image">
              {{ image }}
            </option>
          </select>
        </td>
      </tr>
      <tr>
        <td>Price per week:</td>
        <td>
          <input type="number" v-model="selectedCabin.pricePerWeek">
        </td>
      </tr>
      <tr>
        <td>Total availability:</td>
        <td>
          <input type="number" v-model="selectedCabin.numAvailable">
        </td>
      </tr>
    </table>
    <button type="button" class="btn btn-danger" @click="onDelete">Delete</button>
  </div>
  <div v-else>
    <h2>No cabin selected</h2>
  </div>
</template>

<script>

import {Cabin} from "@/models/cabin";

export default {
  name: "cabinsDetail34",
  props: ['selectedCabin'],
  computed: {
    Cabin() {
      return Cabin
    }
  },
  created() {
    Cabin.copyConstructor(this.selectedCabin)
  },
  methods: {
    onDelete() {
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