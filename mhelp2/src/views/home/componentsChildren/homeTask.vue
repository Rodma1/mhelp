<template>
  <div class="taskList">
    <home-task-item v-for="(item,index) in task" :key="index" :item='item'></home-task-item>
  </div>
</template>
<script>
import homeTaskItem from "views/home/componentsChildren/homeTaskItem.vue";
export default {
  components: {
    homeTaskItem,
  },
  props:{
    task:{
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
