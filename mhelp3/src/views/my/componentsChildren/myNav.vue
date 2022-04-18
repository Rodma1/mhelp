<template>
  <div class="myNav">
    <nav-bar>
      <div slot="center">hahahaa</div>
    </nav-bar>
    
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
export default {
  components: {
    navBar,
  }
};
</script>
<style scoped>
.myNav {
  background: rgb(154, 171, 248);
  color: white;
}
</style>