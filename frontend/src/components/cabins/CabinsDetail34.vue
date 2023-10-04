<template>
  <div v-if="this.copyOfCabin" class="row detail justify-content-center  p-0 px-3 mt-1">
<!--    <div class="row ">-->
      <div class="col col-3 col-auto align-self-center">
        <img class="img-fluid" alt="Cabin image" :src="this.copyOfCabin.getImage()"/>
      </div>
      <div class="col-md-6 col-xl-8">
        <div>
          <form class="p-1 p-xl-4">
            <h3>Cabin {{ selectedCabin.id }}</h3>
            <p class="text-muted">Details</p>
            <div class="mt-1 mb-1 row">
              <div class="col">
                <label class="form-label" for="type">Type</label>
                <select class="form-control" id="type" v-model="this.copyOfCabin.type">
                  <option v-for="type in Cabin.typeList" :key="type">
                    {{ type }}
                  </option>
                </select>
              </div>
              <div class="col">
                <label class="form-label" for="image">Image</label>
                <select class="form-control" id="image" v-model="this.copyOfCabin.image">
                  <option v-for="image in Cabin.imageList" :key="image">
                    {{ image }}
                  </option>
                </select>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label class="form-label" for="pricePerWeek">Price per week</label>
                <input class="form-control" id="pricePerWeek" type="number" v-model="this.copyOfCabin.pricePerWeek"/>
              </div>
              <div class="col">
                <label class="form-label" for="numAvailable">Total availability</label>
                <input class="form-control" id="numAvailable" type="number" v-model="this.copyOfCabin.numAvailable"/>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label class="form-label" for="location">Location</label>
                <select class="form-control" id="location" v-model="this.copyOfCabin.location">
                  <option v-for="location in Cabin.locationList" :key="location">
                    {{ location }}
                  </option>
                </select>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label class="form-label" for="description">Description</label>
                <input class="form-control" id="description" type="text" v-model="this.copyOfCabin.description"/>
              </div>
            </div>
          </form>
        </div>
      </div>
      <div class="row p-2 m-0">
        <div class="col btn-group">
          <button class="btn btn-secondary" @click="onSave" :disabled="hasChanged">Save</button>
          <button class="btn btn-secondary" @click="onReset" :disabled="hasChanged">Reset</button>
          <button class="btn btn-secondary" @click="onClear">Clear</button>
          <button class="btn btn-secondary" @click="onCancel">Cancel</button>
          <button class="btn btn-danger" @click="onDelete" :disabled="!hasChanged">Delete</button>
        </div>
<!--      </div>-->
    </div>
  </div>
  <div v-else class="container">
    <h2>No cabin selected</h2>
  </div>
</template>

<script>
import {Cabin} from "@/models/cabin";

export default {
  name: "cabinsDetail34",
  computed: {
    Cabin() {
      return Cabin;
    },
    hasChanged() {
      if (this.selectedCabin === null || this.copyOfCabin === null) {
        return false;
      }
      return Cabin.equals(this.selectedCabin, this.copyOfCabin);
    }
  },
  props: ["cabins"],

  data() {
    return {
      selectedCabin: null,
      copyOfCabin: null,
    };
  },

  created() {
    this.selectedCabin = this.$route.params.id ? this.findSelectedCabinFromRoute(this.$route.params.id) : null;
    console.log(this.selectedCabin);
    this.copyOfCabin = this.selectedCabin ? this.selectedCabin.copyConstructor(this.selectedCabin) : null;
  },
  methods: {
    onClear() {
      this.copyOfCabin = new Cabin(this.copyOfCabin.id);
    },
    onReset() {
      this.copyOfCabin = this.selectedCabin.copyConstructor(this.selectedCabin);
    },
    onCancel() {
      this.onReset()
      this.$router.push("/cabins/overView34");
    },
    onSave() {
      this.$emit("save", this.copyOfCabin);
      this.$router.push("/cabins/overView34");
    },
    onDelete() {
      this.$emit("delete", this.selectedCabin);
      this.$router.push("/cabins/overView34");
    },
    findSelectedCabinFromRoute(id) {
      for (let i = 0; i < this.cabins.length; i++) {
        if (this.cabins[i].id === parseInt(id)) {
          return this.cabins[i];
        }
      }
      return null;
    },
  },
  watch: {
    "$route.params.id"(id) {
      this.selectedCabin = this.findSelectedCabinFromRoute(id);
      this.copyOfCabin = this.selectedCabin ? this.selectedCabin.copyConstructor(this.selectedCabin) : null;
    },
  },
};
</script>

<style scoped>
.img-fluid {
  max-width: 75%;
  max-height: 75%;
}

</style>