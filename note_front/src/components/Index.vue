<template>
  <div>
    <nav-menu></nav-menu>
    <router-view/>
  </div>
</template>

<script>
  import NavMenu from "./common/NavMenu";
    export default {
      name: "index",
      components:{NavMenu}
    }
</script>

<style scoped>

</style>
