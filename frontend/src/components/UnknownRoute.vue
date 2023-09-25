<template>
<div class="bg-warning">
  <h1 class="text-danger">Oops an error has occured...</h1>
  <h2 class="text-danger">There is no component available at route: {{ this.$route.path }}</h2>
</div>
</template>

<script>
export default {
  name: "UnknownRoute"
}
</script>

<style scoped>

</style>