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
        <button class="btn btn-secondary" @click="onSave" :disabled="!hasChanged">Save</button>
        <button class="btn btn-secondary"
                @click="confirm(onReset, true,
                    'Reset','discard unsaved changes in')" :disabled="!hasChanged">
          Reset
        </button>
        <button class="btn btn-secondary"
                @click="confirm(onClear,true,
                    'Clear','discard unsaved changes in')">
          Clear
        </button>
        <button class="btn btn-secondary" @click="confirm(onCancel, true,
                    'Cancel','discard unsaved changes in')">Cancel
        </button>
        <button class="btn btn-danger" @click="confirm(onDelete, false,
        'Delete','delete')" :disabled="hasChanged">
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
import Modal from "bootstrap/js/dist/modal";

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
      const changed = !Cabin.equals(this.selectedCabin, this.copyOfCabin);
      if (changed) {
        this.$emit("changed", changed);
      }
      return changed;
    }
  },
  props: {
    cabins: Array,
    ch
  },
  data() {
    return {
      selectedCabin: null,
      copyOfCabin: null,
    };
  },
  beforeRouteLeave(to, from, next) {
    this.hasChanged ? this.beforeUnload(next) : next;
  },
  beforeUpdate(to, from, next) {
    if (to !== from) {
      this.hasChanged ? this.beforeUnload(next) : next;
    }
  },
  // mounted(to, from, next) {
  //   this.hasChanged ? this.beforeUnload(next) : next;
  // },
  // beforeUnmount( to, from, next) {
  //   this.hasChanged ? this.beforeUnload(next) : next;
  // },
  created() {
    this.selectedCabin = this.$route.params.id ? this.findSelectedCabinFromRoute(this.$route.params.id) : null;
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
      this.selectedCabin = null;
      this.copyOfCabin = null;
      this.$router.push("/cabins/overView34");
    },
    onSave() {
      this.$emit("save", this.copyOfCabin);
      this.selectedCabin = null;
      this.copyOfCabin = null;
      this.$router.push("/cabins/overView34");
    },
    onDelete() {
      this.$emit("delete", this.selectedCabin);
      this.selectedCabin = null;
      this.copyOfCabin = null;
      this.$router.push("/cabins/overView34");
    },
    beforeUnload() {
      if (!this.hasChanged) {
        return;
      }
      this.confirm(() => {}, true, 'Leaving page with unsaved changes',
          'discard unsaved changes in');
    },
    confirm(method, needsChanges, title, message) {
      if (needsChanges && !this.hasChanged) {
        method();
        this.hasChanged = false;
        return;
      }
      const modal = new Modal(this.$refs.modal);
      this.$refs.title.innerText = title;
      this.$refs.modalMessage.innerText = 'Are you sure you want to ' + message + ' cabin: '
          + this.selectedCabin.id + '?'
      modal.show();
      if (modal._isShown) {
        this.$refs.modal.addEventListener('click', (event) => {
          if (event.target.id === 'understood') {
            method();
            modal.hide();
          }
        })
      }
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