<template>

</template>

<script>

export default {
  name: "Overview31",
  data() {
    return {
      cabins: [],
      lastId: 10000,
    };
  },
  created() {
    for (let i = 0; i < 8; i++) {
      this.cabins.push(
          Cabin.createSampleCabin(this.lastId + Math.floor(Math.random() * 3) + 1));
    }
  },
}
</script>

<style scoped>

</style>