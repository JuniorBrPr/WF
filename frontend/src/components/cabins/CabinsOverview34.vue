<template>
  <div class="col px-5 text-center">
    <div class="row">
      <div class="col col-auto align-self-center">
        <button class="btn btn-lg btn-success" @click="onNewCabin()">
          New Cabin
        </button>
      </div>
      <div class="col col-10">
        <div class="row flex-nowrap overflow-auto p-2">
          <div class="cabin col col-2 border border-secondary-subtle rounded-2 justify-content-center mx-1 "
               v-for="cabin in cabins" :key="cabin.id" @click="selectCabin(cabin)"
               :class="{active:selectedCabin === cabin}">
            <div class="row justify-content-center">
              <img class="card-img rounded" :src="cabin.getImage()" alt="Card image cap">
            </div>
            <div class="mt-1">
              <h6 class="fw-medium">Cabin {{ cabin.id }}</h6>
              <p class="fw-light">
                <strong>Type:</strong> {{ cabin.type }}
                <br><strong>Location:</strong> {{cabin.location }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <router-view :cabins="cabins" @delete="onDelete" @save="onSave"/>
    </div>
  </div>
</template>

<script>
import {Cabin} from '@/models/cabin.js';

export default {
  name: "cabinsOverview34",
  computed: {
    Cabin() {
      return Cabin
    }
  },
  components: {},
  data() {
    return {
      cabins: [],
      lastId: 10000,
      selectedCabin: null,
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
    this.selectedCabin = this.findSelectedCabinFromRoute(this.$route);
  },

  methods: {
    selectCabin(cabin) {
      if (this.selectedCabin !== cabin) {
        this.selectedCabin = cabin;
        this.$router.push(this.$route.matched[1].path + '/' + cabin.id);
      } else {
        this.selectedCabin = null;
        this.$router.push(this.$route.matched[1].path);
      }
    },
    onNewCabin() {
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      let newCabin = Cabin.createSampleCabin(this.lastId)

      this.cabins.push(
          newCabin
      )
      this.selectedCabin = newCabin
      this.$router.push("/cabins/overView34/" + this.lastId);
    },
    onDelete() {
      let index = this.cabins.indexOf(this.selectedCabin);
      if (index > -1) {
        this.cabins.splice(index, 1)
        this.selectedCabin = null
      }
    },
    onSave(cabin) {
      console.log(cabin);
      let index = this.cabins.indexOf(this.selectedCabin);
      console.log("Index" + index);
      if (index > -1) {
         this.cabins[index] = cabin;
      }
    },
    findSelectedCabinFromRoute($route) {
      let cabinId = $route.params.id;
      for (let i = 0; i < this.cabins.length; i++) {
        if (this.cabins[i].id === parseInt(cabinId)) {
          console.log(cabinId);
          return this.cabins[i];
        }
      }
      return null;
    }
  }
}
</script>

<style scoped>
.card-img {
  width: 50%;
  height: 50%;
}

.cabin {
  min-width: 100px;
  min-height: fit-content;
  max-width: 200px;
  max-height: 250px;
}

.active {
  background-color: lightgreen;
}
</style>
