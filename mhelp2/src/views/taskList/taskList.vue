<template>
  <div class="taskList">
    <router-view class="routerView"></router-view>
    <task-list-nav :index="currentIndex" @goback="goback"></task-list-nav>
    <tab-control @tabClick="tabClick"></tab-control>
    <list :types="showTaskList" v-if="isTask"></list>
    <div class="noTask" v-else>
      <img src="@/assets/img/taskList/暂无任务.png" alt="" />
      <div>暂无此类型任务</div>
    </div>
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
          list: [{ content: "取快递" }, { content: "带饭" }],
          page: 0,
        },
        incomplete: {
          list: [{ content: "开会" }],
          page: 0,
        },
        complete: {
          list: [],
          page: 0,
        },
        collect: {
          list: [],
          page: 0,
        },
      },
      currentType: "all",
      currentIndex: 0,
      isTask:false
    };
  },
  activated() {
    this.archive();
    this.isTaskShow()
    // console.log(this.archiveList[this.currentType].list);
  },
  deactivated(){
  },
  destroyed(){
  },
  mounted() {
    // console.log(this.currentType)
    // this.currentType="all"
    this.isTaskShow()
  },
  computed: {
    showTaskList() {
      return this.archiveList[this.currentType].list;
    },
  },
  methods: {
    archive() {
      archive(this.$store.state.token, 2).then((res) => {
        console.log(res);

      });
    },
    tabClick(index) {
      this.currentIndex = index;
      switch (index) {
        case 0:
          this.currentType = "all";
         this.isTaskShow()
          break;
        case 1:
          this.currentType = "incomplete";
          this.isTaskShow()
          break;
        case 2:
          this.currentType = "complete";
          this.isTaskShow()
          break;
        case 3:
          this.currentType = "collect";
          this.isTaskShow()
          break;
      }
    },
    goback() {
      this.currentType = "all";
    },
    isTaskShow() {
      if (this.archiveList[this.currentType].list==0) {
        this.isTask = false;
      } else {
        this.isTask = true;
      }
    },
  },
};
</script>
<style scoped>
.taskList {
  height: 100vh;
  position: relative;
  z-index: 15;
  background: #f6f6f6;
}
.routerView {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 13;
}
.noTask {
  height: 100%;
}
.noTask img {
  width: 250px;
  height: 180px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 20px;
}
.noTask div {
  position: absolute;
  top: 280px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
