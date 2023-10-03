<template>
  <section v-if="this.selectedCabin" class="position-relative py-4 py-xl-5">
    <div class="container position-relative">
      <div class="row">
        <div class="col">
          <img class="img-fluid" alt="Cabin image" :src="selectedCabin.getImage()"/>
        </div>
        <div class="col-md-6 col-xl-5">
          <div>
            <form class="p-3 p-xl-4">
              <h3>Cabin {{ selectedCabin.id }}</h3>
              <p class="text-muted">TEXT</p>
              <div class="mt-2 mb-3 row">
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
              <div class="row mb-3">
                <div class="col">
                  <label class="form-label" for="pricePerWeek">Price per week</label>
                  <input class="form-control" id="pricePerWeek" type="number" v-model="selectedCabin.pricePerWeek"/>
                </div>
                <div class="col">
                  <label class="form-label" for="numAvailable">Total availability</label>
                  <input class="form-control" id="numAvailable" type="number" v-model="selectedCabin.numAvailable"/>
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label" for="location">Location</label>
                <select class="form-control" id="location" v-model="selectedCabin.location">
                  <option v-for="location in Cabin.locationList" :key="location">
                    {{ location }}
                  </option>
                </select>
              </div>
              <div class="mb-3">
                <label class="form-label" for="description">Description</label>
                <textarea class="form-control" id="description" type="text" rows="3"
                          v-model="selectedCabin.description"/>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>


  <!--          </div>-->
  <!--          <div class="form-group col-md-6">-->
  <!--          </div>-->
  <!--        </div>-->
  <!--        <div class="d-flex flex-row">-->
  <!--          <div class="form-group col-md-6">-->
  <!--            <label for="pricePerWeek">Price per week</label>-->
  <!--            <input class="form-control " id="pricePerWeek" type="number" v-model="selectedCabin.pricePerWeek">-->
  <!--          </div>-->
  <!--          <div class="form-group col-md-6">-->
  <!--            <label for="numAvailable">Total availability</label>-->
  <!--            <input class="form-control" id="numAvailable" type="number" v-model="selectedCabin.numAvailable">-->
  <!--          </div>-->
  <!--        </div>-->
  <!--        <div class="d-flex flex-row">-->
  <!--          <div class="form-group">-->
  <!--            <label for="location">Location</label>-->
  <!--            <select class="form-control" id="location" v-model="selectedCabin.location">-->
  <!--              <option v-for="location in Cabin.locationList" :key="location">-->
  <!--                {{ location }}-->
  <!--              </option>-->
  <!--            </select>-->
  <!--          </div>-->
  <!--        </div>-->
  <!--        <div class="d-flex flex-row">-->
  <!--          <div class="form-group">-->
  <!--            <label for="description">Description</label>-->
  <!--            <input type="text" class="form-control" id="description" v-model="selectedCabin.description"/>-->
  <!--          </div>-->
  <!--        </div>-->
  <!--      </div>-->
  <!--    </form>-->
  <!--      <div class="card-body">-->
  <!--        <table class="table">-->
  <!--          <tr class="">-->
  <!--            <td>Type:</td>-->
  <!--            <td>-->
  <!--              <select v-model="/* eslint-disable vue/no-mutating-props */selectedCabin.type">-->
  <!--                <option v-for="type in Cabin.typeList" :key="type">-->
  <!--                  {{ type }}-->
  <!--                </option>-->
  <!--              </select>-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <td>Location:</td>-->
  <!--            <td>-->
  <!--              <select v-model="selectedCabin.location">-->
  <!--                <option v-for="location in Cabin.locationList" :key="location">-->
  <!--                  {{ location }}-->
  <!--                </option>-->
  <!--              </select>-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <td>Description:</td>-->
  <!--            <td>-->
  <!--              <input v-model="selectedCabin.description"/>-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <td>Image</td>-->
  <!--            <td>-->
  <!--              <select v-model="selectedCabin.image">-->
  <!--                <option v-for="image in Cabin.imageList" :key="image">-->
  <!--                  {{ image }}-->
  <!--                </option>-->
  <!--              </select>-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <td>Price per week:</td>-->
  <!--            <td>-->
  <!--              <input type="number" v-model="selectedCabin.pricePerWeek">-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <td>Total availability:</td>-->
  <!--            <td>-->
  <!--              <input type="number" v-model="selectedCabin.numAvailable">-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--        </table>-->
  <!--        <div class="btn-group" role="group" aria-label="Cabin state actions">-->
  <!--          <button type="button" class="btn btn-danger" :class="{disabled:this.hasChanged}" @click="onSave">-->
  <!--            Save-->
  <!--          </button>-->
  <!--          <button type="button" class="btn btn-danger" @click="onCancel">-->
  <!--            Cancel-->
  <!--          </button>-->
  <!--          <button type="button" class="btn btn-danger" :class="{disabled:this.hasChanged}" @click="onReset">-->
  <!--            Reset-->
  <!--          </button>-->
  <!--          <button type="button" class="btn btn-danger" @click="onClear">-->
  <!--            Clear-->
  <!--          </button>-->
  <!--          <button type="button" class="btn btn-danger" :class="{disabled:!this.hasChanged}" @click="onDelete">-->
  <!--            Delete-->
  <!--          </button>-->
  <!--        </div>-->
  <!--      </div>-->
  <!--  </div>-->
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
.img-thumbnail {
  width: 300px;
  height: 300px;
}
</style>