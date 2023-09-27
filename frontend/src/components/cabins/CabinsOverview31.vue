<template>
  <table>
    <thead>
    <tr>
      <th scope="col">Id:</th>
      <th scope="col">Type:</th>
      <th scope="col">Location:</th>
      <th scope="col">Description:</th>
      <th scope="col">Price p/wk:</th>
      <th scope="col">Total available:</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="cabin in cabins" :key="cabin.id">
      <td>{{ cabin.id }}</td>
      <td>{{ cabin.type }}</td>
      <td>{{ cabin.location }}</td>
      <td>{{ cabin.description }}</td>
      <td>{{ cabin.pricePerWeek }}</td>
      <td>{{ cabin.numAvailable }}</td>
    </tr>
    </tbody>
  </table>
  <button @click="onNewCabin()">
    Nieuw
  </button>
</template>

<script>
import {Cabin} from '@/models/cabin.js';

export default  {
  name: "OverviewComponent",
  data() {
    return {
      cabins: [],
      lastId: 10000,
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
    onNewCabin(){
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      this.cabins.push(
          Cabin.createSampleCabin(this.lastId)
      )
    }
  }
}
</script>

<style scoped>
table {
  width: 100%;
}

thead {
  background-color: orange;
  color: white;
}

tbody {
  background-color: white;
}

tr {
  border-bottom: 1px solid #ccc;
}

th,
td {
  border-right: 1px solid #ccc;
  padding: 8px;
}

tr td:last-child,
tr th:last-child {
  border-right: none;
}

button{
  background: yellow;
  color: red;
  float: right;
}
</style>
