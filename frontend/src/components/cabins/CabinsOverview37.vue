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
      <router-view :cabins="cabins" @cabinDeleted="refreshCabins" @cabinUpdate="refreshCabins"/>
    </div>
  </div>
</template>

<script>
import {Cabin} from '@/models/cabin.js';

export default {
  name: "cabinsOverview37",
  inject: ['cabinsService'],
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
  async created() {
    this.cabins = await this.cabinsService.asyncFindAll();
  },
  mounted() {
    window.addEventListener('beforeunload', this.beforeUnload);
  },
  beforeUnmount() {
    window.removeEventListener('beforeunload', this.beforeUnload);
  },
  methods: {
    selectCabin(cabin) {
      if (this.findSelectedCabinFromRoute() !== cabin) {
        this.$router.push("/cabins/overView37/" + cabin.id);
      } else {
        this.$router.push("/cabins/overView37/");
      }
    },
    async onNewCabin() {
      // this.lastId = this.lastId + Math.floor(Math.random() * 3) + 1

      let newCabin = Cabin.createSampleCabin(0)
      const savedCabin = await this.cabinsService.asyncSave(newCabin)
      console.log(savedCabin)

      this.cabins.push(newCabin)
      this.$router.push(this.$route.matched[1].path + "/" + savedCabin.id);
      console.log(this.$route.matched[1].path + "/" + savedCabin.id)
      this.$nextTick(() => {
        this.$refs.scrollPanel.scrollLeft = this.$refs.scrollPanel.scrollWidth;
      })
    },
    findSelectedCabinFromRoute() {
      let cabinId = this.$route.params.id;
      for (let i = 0; i < this.cabins.length; i++) {
        if (this.cabins[i].id === parseInt(cabinId)) {
          return this.cabins[i];
        }
      }
      this.$router.push("/cabins/overView37/");
      return null;
    },
    async refreshCabins() {
      this.cabins = await this.cabinsService.asyncFindAll()
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
