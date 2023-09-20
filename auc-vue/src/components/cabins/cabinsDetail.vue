<template>
  <div class="cabins-detail">
    <div v-if="selectedCabin">
      <h2>Cabin Details</h2>
      <img class="card-image" :src="selectedCabin.image"/>
      <p>{{ selectedCabin.id }}</p>
      <p>{{ selectedCabin.type }}</p>
      <p>{{ selectedCabin.location }}</p>
      <p>{{ selectedCabin.description }}</p>
      <p>{{ selectedCabin.pricePerWeek }}</p>
      <p>{{ selectedCabin.numAvailable }}</p>
    </div>
    <div v-else>
      <p>Please select a cabin to view details.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "cabinsDetail",
  props: {
    selectedCabin: Object,
  }
}
</script>

<style scoped>

</style>