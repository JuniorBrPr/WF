<template>
  <div class="container text-center">
    <div class="card bg-warning-subtle">
      <div class="card-title"><h1 class="text-danger">Request Error</h1></div>
      <div class="card-body">
        <div class="col">
          <div class="row">
            <h2 class="text-danger">Status: {{ status }}</h2>
          </div>
          <div class="row">
            <h2 class="text-danger">Status Text: {{ statusText }}</h2>
          </div>
          <div class="row">
            <h2 class="text-danger">Message: {{ message }}</h2>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RequestError",
  props: {
    status: String,
    statusText: String,
    message: String,
  }
}
</script>

<style scoped>

</style>