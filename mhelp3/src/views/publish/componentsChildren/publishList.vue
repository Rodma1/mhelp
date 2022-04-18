<template>
  <div class="publishList">
      <publish-item v-for="(item,index) in list" :key="index" :item=item></publish-item>
  </div>
</template>
<script>
import publishItem from "views/publish/componentsChildren/publishItem.vue";
export default {
  components: {
    publishItem,
  },
  props:{
    list:{
      type:Array,
      default(){
        return []
      }
    }
  }
};
</script>
<style scoped>
</style>
