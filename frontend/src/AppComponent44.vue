<template>
  <HeaderSb/>
  <NavbarSb/>
  <router-view/>
</template>

<script>
import HeaderSb from "@/components/HeaderSb";
import NavbarSb from "@/components/NavbarSb";
import {CabinsAdaptor} from "@/services/CabinsAdaptor";
import {SessionSbService} from "@/services/SessionSbService";
import {shallowReactive} from "vue";
import {CONFIG} from "../app-config";
export default {
  name: "AppComponent44",
  components: {NavbarSb, HeaderSb},

  provide() {
    this.theSessionService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + "/authentication", CONFIG.JWT_STORAGE_ITEM) // TODO FIX RESOUR URL
    )
    return {
      cabinsService: new CabinsAdaptor(CONFIG.BACKEND_URL + "/cabins/"),
      sessionService: this.theSessionService,
    }
  }
}
</script>

<style scoped>

</style>