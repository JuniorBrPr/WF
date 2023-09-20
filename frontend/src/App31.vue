<template>
  <HeaderComponent/>
  <NavbarComponent/>
<!--  <OverviewComponent/>-->
  <cabins-overview/>
</template>

<script>

import HeaderComponent from "@/components/HeaderComponent.vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
// import OverviewComponent from "@/components/cabins/OverviewComponent.vue";
import CabinsOverview from "@/components/cabins/cabinsOverview";
export default {
  name: 'App',
  components: {
    CabinsOverview,
    // OverviewComponent,
    NavbarComponent,
    HeaderComponent
  }
}
</script>