<template>
  <div class="school">
    <school-nav-bar></school-nav-bar>
    <school-name></school-name>
  </div>
</template>
<script>
import schoolNavBar from "views/school/childrenComponents/schoolNavBar.vue"
import schoolName from "views/school/childrenComponents/schoolName.vue"
import {schools} from "network/task.js"
export default {
  components: {
    schoolNavBar,
    schoolName
  },
  data(){
    return{
      schools:[]
    }
  },
  mounted(){
    schools().then((res)=>{
      console.log(res)
      this.schools=res.data
      console.log(this.schools)
    })
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