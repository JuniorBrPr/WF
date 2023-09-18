<template>
  <HeaderComponent/>
  <NavbarComponent/>
  <OverviewComponent/>
</template>

<script>

import HeaderComponent from "@/components/HeaderComponent.vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import OverviewComponent from "@/components/cabins/OverviewComponent.vue";
export default {
  name: 'App',
  components: {
    OverviewComponent,
    NavbarComponent,
    HeaderComponent
  }
}
</script>