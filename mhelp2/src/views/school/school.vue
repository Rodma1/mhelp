<template>
  <div class="school">
    <school-nav-bar></school-nav-bar>
    <school-name></school-name>
  </div>
</template>
<script>
import schoolNavBar from "views/school/childrenComponents/schoolNavBar.vue"
import schoolName from "views/school/childrenComponents/schoolName.vue"

export default {
  components: {
    schoolNavBar,
    schoolName
  },
  data(){
    return{
      
    }
  },
  mounted(){
   
  },
  
};
</script>
<style scoped>
.school {
  height: 100vh;
  position: relative;
  z-index: 10;
  background-color: white;
}

</style>