<template>
  <div class="taskList">
    <router-view class="routerView"></router-view>
    <task-list-nav :index="currentIndex"></task-list-nav>
    <tab-control @tabClick="tabClick"></tab-control>
    <list ></list>
  </div>
</template>
<script>
import taskListNav from "views/taskList/componentsChildren/taskListNav.vue";
import tabControl from "views/taskList/componentsChildren/tabControl.vue";
import list from "views/taskList/componentsChildren/list.vue";
import { archive } from "network/task.js";

export default {
  components: {
    taskListNav,
    tabControl,
    list,
  },
  data() {
    return {
      page: {
        pageNumber: 1,
        pageSize: 10,
      },
      archiveList: {
        all: {
          list:[],
          page:0
        },
        incomplete: {
          list:[],
          page:0
        },
        complete: {
          list:[],
          page:0
        },
        collect:{
          list:[],
          page:0
        },
      },
      currentType: "all",
      
      currentIndex:0
    };
  },
  activated() {
    this.archive();
  },
  computed:{
     showTaskList(){
       return this.showTaskList[this.currentType].list;
     }
  },
  methods: {
    archive() {
      archive(this.$store.state.token, 2).then((res) => {
        console.log(res);
        // this.archiveList.incomplete.push(...res.data);
        // this.archiveList.all.push(...res.data)
        // console.log(this.archiveList.incomplete);
      });
    },
    tabClick(index) {
      this.currentIndex=index
      switch (index) {
        case 0:
          this.currentType = "all";
          break;
        case 1:
          this.currentType = "incomplete";
          break;
        case 2:
          this.currentType = "complete";
          break;
        case 3:
            this.currentType ="collect"
      }
    },
  },
};
</script>
<style scoped>
.taskList {
  height: 100vh;
  z-index: 10;
  position: relative;
  background: #f6f6f6;
}
.routerView{
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  
}
</style>
