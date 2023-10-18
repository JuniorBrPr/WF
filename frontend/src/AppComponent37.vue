<template>
  <HeaderComponent/>
  <NavbarComponent/>
  <router-view/>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent";
import NavbarComponent from "@/components/NavbarComponent";
import {CabinsAdaptor} from "@/services/CabinsAdaptor";
import {CONFIG} from "../app-config";

export default {
  components: {NavbarComponent, HeaderComponent},
  name: "AppComponent37",
  provide() {
    return {
      // stateless data services adaptor singletons
      cabinsService: new CabinsAdaptor(CONFIG.BACKEND_URL + "/cabins/")
    }
  }
}
</script>

<style scoped>

</style>