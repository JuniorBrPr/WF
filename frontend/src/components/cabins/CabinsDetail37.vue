<template>
  <div v-if="this.copyOfCabin" class="row detail justify-content-center  p-0 px-3 mt-1">
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true" ref="modal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel" ref="title">Modal title</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" ref="modalMessage">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" id="understood">OK</button>
          </div>
        </div>
      </div>
    </div>
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
        <button class="btn btn-outline-success fs-5 fw-bold" @click="onSave" :disabled="!hasChanged"
                :class="{'bg-success-subtle': hasChanged}">
          Save
        </button>
        <button class="btn btn-outline-primary fs-5 fw-bold" :class="{'bg-primary-subtle': hasChanged}"
                @click="confirm(onReset, true,'Reset','discard unsaved changes in')"
                :disabled="!hasChanged">
          Reset
        </button>
        <button class="btn btn-outline-primary bg-primary-subtle fs-5 fw-bold"
                @click="confirm(onClear,true,'Clear','discard unsaved changes in')">
          Clear
        </button>
        <button class="btn btn-outline-primary bg-primary-subtle fs-5 fw-bold"
                @click="confirm(onCancel, true,'Cancel','discard unsaved changes in')">
          Cancel
        </button>
        <button class="btn btn-outline-danger fs-5 fw-bold"
                :class="{'bg-danger-subtle': !hasChanged}"
                @click="confirm(onDelete, false,'Delete','delete')"
                :disabled="hasChanged">
          Delete
        </button>
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
  name: "cabinsDetail37",
  inject: ['cabinsService'],
  computed: {
    Cabin() {
      return Cabin;
    },
    hasChanged() {
      return !Cabin.equals(this.selectedCabin, this.copyOfCabin);
    }
  },
  async created() {
    this.selectedCabin = await this.findSelectedCabinFromRoute()
    this.copyOfCabin = this.selectedCabin ? Cabin.copyConstructor(this.selectedCabin) : null;
  },
  data() {
    return {
      copyOfCabin: null,
      selectedCabin: null,
    };
  },
  beforeRouteUpdate(to, from, next) {
    this.beforeUnload(null, next);
  },
  beforeRouteLeave(to, from, next) {
    this.beforeUnload(null, next);
  },
  async mounted() {
    window.addEventListener('beforeunload', this.beforeUnload);
  },
  beforeUnmount() {
    window.removeEventListener('beforeunload', this.beforeUnload);
  },
  methods: {
    onClear() {
      this.copyOfCabin = new Cabin(this.copyOfCabin.id);
    },
    onReset() {
      this.copyOfCabin = Cabin.copyConstructor(this.selectedCabin);
    },
    onCancel() {
      this.onReset()
      this.$router.push("/cabins/overView37");
    },
    async onSave() {
      await this.cabinsService.asyncSave(this.copyOfCabin)
      this.$emit('cabinUpdate')
    },
    async onDelete() {
      await this.cabinsService.asyncDeleteById(this.copyOfCabin.id)
      this.$router.push("/cabins/overView37");
      this.$emit('cabinDeleted')
    },
    confirm(method, needsChanges, title, message) {
      if (needsChanges && !this.hasChanged) {
        method();
        return;
      }
      if(!this.selectedCabin){
        return;
      }
        if (confirm('Are you sure you want to ' + message + ' cabin: ' + this.selectedCabin.id + '?')) {
          method();
      }
    },
    beforeUnload(event, next) {
      this.confirm(() => {
            if (next) next();
            if (event) {
              event.preventDefault();
              event.returnValue = '';
            }
          }, true,
          'Discarding unsaved changes', 'discard unsaved changes in')
    },
    async findSelectedCabinFromRoute() {
      let cabinId = this.$route.params.id;
      let cabin = null;
      if(cabinId) {
        cabin =  await this.cabinsService.asyncFindById(cabinId)
      }
      this.selectedCabin = cabin;
      return cabin;
    }
  }
  ,
  watch: {
    async "$route.params.id"() {
      this.selectedCabin = await this.findSelectedCabinFromRoute()
      this.copyOfCabin = this.selectedCabin ? Cabin.copyConstructor(this.selectedCabin) : null;
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