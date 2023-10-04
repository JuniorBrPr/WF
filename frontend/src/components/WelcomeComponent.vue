<script>
export default {
  name: "WelcomeComponent"
}
</script>

<template>
  <div class="row border border-2 border-light-subtle rounded-4 mx-2">
    <div class="col-md-2 border border-2 border-light-subtle rounded-4">
      <div class="row">
        <label for="date" class="form-label h4 text-center">Pick a date</label>
        <input type="date" id="date" class="form-control"/>
      </div>
      <div class="row mt-2">
        <h4>Pick a location:</h4>
        <ul class="col list-group">
          <li class="list-group-item" role="button">Egmond Aan Zee</li>
          <li class="list-group-item" role="button">Zandvoort</li>
          <li class="list-group-item" role="button">Brugge</li>
          <li class="list-group-item" role="button">Zeeland</li>
        </ul>
      </div>
    </div>
    <main class="col-md-6 col-xl-8">
      <div class="row">
        <h3 class="text-center">Photo Gallery:</h3>
      </div>
      <div class="row justify-content-center">
        <img class="m-1 img-fluid" src="../assets/h1.png"/>
        <img class="m-1 img-fluid" src="../assets/h2.png"/>
        <img class="m-1 img-fluid" src="../assets/h.png"/>
        <img class="m-1 img-fluid" src="../assets/h1.png"/>
        <img class="m-1 img-fluid" src="../assets/h.png"/>
        <img class="m-1 img-fluid" src="../assets/h1.png"/>
        <img class="m-1 img-fluid" src="../assets/h2.png"/>
        <img class="m-1 img-fluid" src="../assets/h3.png"/>
        <img class="m-1 img-fluid" src="../assets/h2.png"/>
        <img class="m-1 img-fluid" src="../assets/h3.png"/>
        <img class="m-1 img-fluid" src="../assets/h.png"/>
        <img class="m-1 img-fluid" src="../assets/h3.png"/>
      </div>
    </main>
    <div class="col-md-2 border border-2 border-light-subtle rounded-4">
      <h4 class="mt-2">Beach activities</h4>
      <p class="mt-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tellus tortor, tincidunt at eleifend at,
        sollicitudin vitae nulla. Nunc a posuere erat. Sed rutrum, purus at dapibus mattis, est purus sagittis augue,
        eget aliquet leo velit sed nibh. Maecenas sodales mi erat. Nunc consectetur, ipsum a ullamcorper commodo, quam
        orci viverra erat, id cursus mi massa vel neque. Morbi placerat ut mauris in volutpat. Aenean efficitur sagittis
        diam, ac ultrices diam tincidunt eu. Nulla et semper nisl.
      </p>
    </div>
  </div>
</template>

<style scoped>
.img-fluid {
  max-width: 200px;
}

.img-fluid:hover, .list-group-item:hover{
  transform: scale(0.95);
  background: lightgreen;
  border-radius: 5px;
}
</style>