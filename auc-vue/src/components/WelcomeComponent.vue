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
          <li>Item 1</li>
          <li>Item 2</li>
          <li>Item 3</li>
          <li>Item 4</li>
        </ul>
      </div>
    </aside>
    <main >
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
      <h4>Heading</h4>
      <p>Paragraph</p>
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
  transform: scale(1.1);
}
</style>