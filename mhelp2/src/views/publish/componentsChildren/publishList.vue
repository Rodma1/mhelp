<template>
  <div class="publishList">
      <publish-item></publish-item>
      <publish-item></publish-item>
      <publish-item></publish-item>
  </div>
</template>
<script>
import publishItem from "views/publish/componentsChildren/publishItem.vue";
export default {
  components: {
    publishItem,
  },
};
</script>
<style scoped>
</style>
