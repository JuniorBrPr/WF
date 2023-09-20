<script>
export default {
  name: "WelcomeComponent"
}
</script>

<template>
  <div class="d-flex">
    <aside>
      <div>
        <h4>Pick a date</h4><input type="date"/>
      </div>
      <div>
        <h4>Pick a location:</h4>
        <ul>
          <li>Egmond Aan Zee</li>
          <li>Zandvoort</li>
          <li>Brugge</li>
          <li>Zeeland</li>
        </ul>
      </div>
    </aside>
    <main>
      <div>
        <h3 class="text-start">Photo Gallery:</h3>
      </div>
      <div>
        <img class="m-1 image" src="../assets/h1.png"/>
        <img class="m-1 image" src="../assets/h2.png"/>
        <img class="m-1 image" src="../assets/h.png"/>
        <img class="m-1 image" src="../assets/h1.png"/>
        <img class="m-1 image" src="../assets/h.png"/>
        <img class="m-1 image" src="../assets/h1.png"/>
        <img class="m-1 image" src="../assets/h2.png"/>
        <img class="m-1 image" src="../assets/h3.png"/>
        <img class="m-1 image" src="../assets/h2.png"/>
        <img class="m-1 image" src="../assets/h3.png"/>
        <img class="m-1 image" src="../assets/h.png"/>
        <img class="m-1 image" src="../assets/h3.png"/>
      </div>
    </main>
    <aside>
      <h4>Beach activities</h4>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tellus tortor, tincidunt at eleifend at,
        sollicitudin vitae nulla. Nunc a posuere erat. Sed rutrum, purus at dapibus mattis, est purus sagittis augue,
        eget aliquet leo velit sed nibh. Maecenas sodales mi erat. Nunc consectetur, ipsum a ullamcorper commodo, quam
        orci viverra erat, id cursus mi massa vel neque. Morbi placerat ut mauris in volutpat. Aenean efficitur sagittis
        diam, ac ultrices diam tincidunt eu. Nulla et semper nisl.
      </p>
    </aside>
  </div>
</template>

<style scoped>
aside {
  width: 30%;
  background-color: #f0f0f0;
  padding: 10px;
}

.image {
  width: 250px;
  height: 200px;
}

.image:hover {
  transform: scale(0.9);
}
</style>