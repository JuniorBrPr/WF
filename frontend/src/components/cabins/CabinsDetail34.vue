<template>
  <div v-if="this.selectedCabin" class="row detail">
    <div class="row">
      <div class="col col-4 p5">
        <img class="img-fluid p-5" alt="Cabin image" :src="selectedCabin.getImage()"/>
      </div>
      <div class="col-md-6 col-xl-8">
        <div>
          <form class="p-1 p-xl-4">
            <h3>Cabin {{ selectedCabin.id }}</h3>
            <p class="text-muted">Details</p>
            <div class="mt-1 mb-1 row">
              <div class="col">
                <label class="form-label" for="type">Type</label>
                <select class="form-control" id="type" v-model="selectedCabin.type">
                  <option v-for="type in Cabin.typeList" :key="type">
                    {{ type }}
                  </option>
                </select>
              </div>
              <div class="col">
                <label class="form-label" for="image">Image</label>
                <select class="form-control" id="image" v-model="selectedCabin.image">
                  <option v-for="image in Cabin.imageList" :key="image">
                    {{ image }}
                  </option>
                </select>
              </div>
            </div>
            <div class="row mb-1">
              <div class="col">
                <label class="form-label" for="pricePerWeek">Price per week</label>
                <input class="form-control" id="pricePerWeek" type="number" v-model="selectedCabin.pricePerWeek"/>
              </div>
              <div class="col">
                <label class="form-label" for="numAvailable">Total availability</label>
                <input class="form-control" id="numAvailable" type="number" v-model="selectedCabin.numAvailable"/>
              </div>
            </div>
            <div class="mb-1">
              <label class="form-label" for="location">Location</label>
              <select class="form-control" id="location" v-model="selectedCabin.location">
                <option v-for="location in Cabin.locationList" :key="location">
                  {{ location }}
                </option>
              </select>
            </div>
            <div class="mb-1">
              <label class="form-label" for="description">Description</label>
              <input class="form-control" id="description" type="text" v-model="selectedCabin.description"/>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="row p-2">
      <div class="col btn-group">
        <button class="btn btn-secondary" @click="onSave" :disabled="hasChanged">Save</button>
        <button class="btn btn-secondary" @click="onReset" :disabled="hasChanged">Reset</button>
        <button class="btn btn-secondary" @click="onClear" :disabled="hasChanged">Clear</button>
        <button class="btn btn-secondary" @click="onCancel">Cancel</button>
        <button class="btn btn-danger" @click="onDelete" :disabled="!hasChanged">Delete</button>
      </div>
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
    this.copyOfCabin = Cabin.copyConstructor(this.selectedCabin);
  },
  methods: {
    onClear() {
      this.selectedCabin = new Cabin();
    },
    onReset() {
      for (let key in this.selectedCabin) {
        this.selectedCabin[key] = this.copyOfCabin[key];
      }
    },
    onCancel() {
      this.onReset()
      this.$router.push("/cabins/overView34");
    },
    onSave() {
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
      this.copyOfCabin = Cabin.copyConstructor(this.selectedCabin);
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