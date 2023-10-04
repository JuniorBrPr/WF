<template>
  <div class="col px-5 text-center border border-2 border-light-subtle rounded-4 mx-2">
    <div class="row pt-1">
      <div class="col col-auto align-self-center">
        <button class="btn btn-lg btn-success" @click="onNewCabin()">
          New Cabin
        </button>
      </div>
      <div class="col col-10">
        <div class="row flex-nowrap overflow-auto p-2 border border-2 border-light-subtle rounded-4" ref="scrollPanel">
          <div class="cabin col col-2 border border-secondary-subtle rounded-2 justify-content-center mx-1 "
               type="button"
               v-for="cabin in cabins" :key="cabin.id" @click="selectCabin(cabin)"
               :class="{active:this.findSelectedCabinFromRoute() === cabin}">
            <div class="row justify-content-center">
              <img class="card-img rounded" :src="cabin.getImage()" alt="Card image cap">
            </div>
            <div class="mt-1">
              <h6 class="fw-medium">Cabin {{ cabin.id }}</h6>
              <p class="fw-light">
                <strong>Type:</strong> {{ cabin.type }}
                <br><strong>Location:</strong> {{ cabin.location }}
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
    },
  },
  components: {},
  data() {
    return {
      cabins: [],
      lastId: 10000,
      isActive: true
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
    selectCabin(cabin) {
      if (this.findSelectedCabinFromRoute() !== cabin) {
        this.$router.push("/cabins/overView34/" + cabin.id);
      } else {
        this.$router.push("/cabins/overView34/");
      }
    },
    onNewCabin() {
      this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1
      let newCabin = Cabin.createSampleCabin(this.lastId)
      this.cabins.push(newCabin)
      this.$router.push("/cabins/overView34/" + this.lastId);
      this.$nextTick(() => {
        this.$refs.scrollPanel.scrollLeft = this.$refs.scrollPanel.scrollWidth;
      })
    },
    onDelete() {
      let index = this.cabins.indexOf(this.findSelectedCabinFromRoute());
      if (index > -1) {
        this.cabins.splice(index, 1)
      }
    },
    onSave(cabin) {
      let index = this.cabins.indexOf(this.findSelectedCabinFromRoute());
      if (index > -1) {
        this.cabins[index] = cabin;
      }
    },
    findSelectedCabinFromRoute() {
      let cabinId = this.$route.params.id;
      for (let i = 0; i < this.cabins.length; i++) {
        if (this.cabins[i].id === parseInt(cabinId)) {
          return this.cabins[i];
        }
      }
      this.$router.push("/cabins/overView34/");
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
