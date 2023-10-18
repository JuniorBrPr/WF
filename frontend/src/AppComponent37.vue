
<template>
    <HeaderComponent/>
    <NavbarComponent/>
    <router-view/>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent";
import NavbarComponent from "@/components/NavbarComponent";
export default {
  name: "AppComponent33",
  components: {NavbarComponent, HeaderComponent}
}
</script>

<style scoped>

</style>