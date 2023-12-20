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
import {FetchInterceptor} from "@/FetchInterceptor";
export default {
  name: "AppComponent44",
  components: {NavbarSb, HeaderSb},

  provide() {
    this.theSessionService = shallowReactive(
        new SessionSbService(CONFIG.AUTHENTICATION_URL, CONFIG.JWT_STORAGE_ITEM)
    )
    this.theFetchInterceptor =
        new FetchInterceptor(this.theSessionService, this.$router)
    return {
      cabinsService: new CabinsAdaptor(CONFIG.BACKEND_URL + "/cabins/"),
      sessionService: this.theSessionService,
    }
  },
  unmounted() {
    console.log("App.unmounted() has been called.")
    this.theFetchInterceptor.unregister();
  }
}
</script>

<style scoped>

</style>