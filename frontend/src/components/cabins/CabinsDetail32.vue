<template>
  <div v-if="this.selectedCabin" class="mt-3">
    <div class="card text-center">
      <h2 class="card-header ">Cabin details {{ selectedCabin.id }}</h2>
      <img class="card-image rounded mx-auto d-block" alt="Cabin image" :src="selectedCabin.getImage()"/>
      <div class="card-body">
        <table class="table">
          <tr class="">
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
    </div>
  </div>
  <div v-else class="container">
    <h2>No cabin selected</h2>
  </div>
</template>

<script>


import {Cabin} from "@/models/cabin";

export default {
  name: "cabinsDetail32",
  computed: {
    Cabin() {
      return Cabin
    }
  },
  props: {
    selected: Object,
    cabins: Array
},

  data() {
    return {
      selectedCabin: null
    }
  },

  created() {
    if (this.selected) {
      this.selectedCabin = this.selected
    }
    if (this.cabins) {
      this.selectedCabin = this.findSelectedCabinFromRoute(this.$route.params.id)
    }
  },
  methods: {
    onDelete() {
      this.$emit('delete', this.selectedCabin)
    },
    findSelectedCabinFromRoute(id) {
      for (let i = 0; i < this.cabins.length; i++) {
        if (this.cabins[i].id === parseInt(id)) {
          return this.cabins[i]
        }
      }
      return null
    }
  },
  watch: {
    "$route.params.id"(id) {
      this.selectedCabin = this.findSelectedCabinFromRoute(id)
    },
    "selected"(selected) {
      this.selectedCabin = selected
    }
  }
}
</script>

<style scoped>
select, input {
  min-width: 300px;
}

.card-image {
  width: 20%;
  height: 20%;
}
</style>