<template>
  <div class="taskList">
    <home-task-item
      v-for="(item, index) in task"
      :key="index"
      :item="item"
      :collectList="collectList"
      @updateCollectList="updateCollectList"
    ></home-task-item>
  </div>
</template>
<script>
import homeTaskItem from "views/home/componentsChildren/homeTaskItem.vue";
import { getCollectList } from "network/task.js";
export default {
  components: {
    homeTaskItem,
  },
  props: {
    task: {
      type: Array,
      default() {
        return [];
      },
    },
    page: {
      type: Object,
      default() {
        return {};
      },
    },
  },
  data() {
    return {
      collectList: [],
    };
  },
  created() {
    this.getCollectList();
  },
  methods: {
    getCollectList() {
      this.page.pageNumber = this.page.pageNumber + 1;
      getCollectList(this.$store.state.token).then((res) => {
        console.log(res);
        res.data.forEach(i => {
          var a=this.collectList.find(j=>{
            return j==i.id
          })
          if(!a){
            this.collectList.push(i.id)
          }
        });
        console.log(this.collectList)
      });
    },
    updateCollectList(){
      this.collectList=[]
       this.page.pageNumber = this.page.pageNumber - 1;
      this.getCollectList()
     
      console.log(11)
    }
  },
};
</script>
<style scoped>

</style>
