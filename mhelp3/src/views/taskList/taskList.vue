<template>
  <div class="taskList">
    <router-view class="routerView"></router-view>
    <task-list-nav :index="currentIndex" @goback="goback"></task-list-nav>
    <tab-control @tabClick="tabClick"></tab-control>
    <list :types="showTaskList" v-if="isTask" @pullingMore="pullingMore"></list>
    <div class="noTask" v-else>
      <img src="@/assets/img/taskList/noTask.png" alt="" />
      <div>暂无此类型任务</div>
    </div>
  </div>
</template> 
<script>
import taskListNav from "views/taskList/componentsChildren/taskListNav.vue";
import tabControl from "views/taskList/componentsChildren/tabControl.vue";
import list from "views/taskList/componentsChildren/list.vue";
import { getuaccepttasks, getCollectList } from "network/task.js";
export default {
  components: {
    taskListNav,
    tabControl,
    list,
  },
  data() {
    return {
      page: {
        pageNumber: 0,
        pageSize: 20,
      },
      archiveList: {
        all: {
          list: [],
          page: 0,
        },
        incomplete: {
          list: [],
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
      currentIndex: null,
      isTask: true,
    };
  },
  activated() {
     this.getuaccepttasks();
    this.getCollectList();
  },
  mounted() {
  },
  computed: {
    showTaskList() {
      return this.archiveList[this.currentType].list;
    },
  },
  methods: {
    getuaccepttasks() {
      this.page.pageNumber = this.page.pageNumber + 1;
      var a = this.archiveList.all.list.length;
      getuaccepttasks(this.$store.state.token, this.page).then((res) => {
        console.log(res);
        this.archiveList.all.list.push(...res.data);
        for (var i = a; i < res.data.length; i++) {
          if (this.archiveList.all.list[i].images) {
            this.archiveList.all.list[i].images =
              this.archiveList.all.list[i].images.split(",");
          }
          if (
            this.archiveList.all.list[i].status == 1 ||
            this.archiveList.all.list[i].status == 0 ||
            this.archiveList.all.list[i].status == null
          ) {
            this.archiveList.incomplete.list.push(this.archiveList.all.list[i]);
          } else {
            this.archiveList.complete.list.push(this.archiveList.all.list[i]);
          }
          this.tabClick(parseInt(this.$route.params.id))
        }
      });
    },
    getCollectList() {
      this.page.pageNumber = this.page.pageNumber + 1;
      var a = this.archiveList.all.list.length;
      getCollectList(this.$store.state.token).then((res) => {
        console.log(res);
        this.archiveList.collect.list.push(...res.data);
        for (var i = a; i < res.data.length; i++) {
          if (this.archiveList.collect.list[i].images) {
            this.archiveList.collect.list[i].images =
              this.archiveList.collect.list[i].images.split(",");
          }
        }
        this.tabClick(parseInt(this.$route.params.id))
      });
    },
    tabClick(index) {
      this.currentIndex = index;
      switch (index) {
        case 0:
          this.currentType = "all";
          this.isTaskShow();
          break;
        case 1:
          this.currentType = "incomplete";
          this.isTaskShow();
          break;
        case 2:
          this.currentType = "complete";
          this.isTaskShow();
          break;
        case 3:
          this.currentType = "collect";
          this.isTaskShow();
          break;
      }
    },
    goback() {
      this.currentType = "all";
    },
    isTaskShow() {
      if (this.archiveList[this.currentType].list.length) {
        this.isTask = true;
      } else {
        this.isTask = false;
      }
    },
    pullingMore() {
      this.getuaccepttasks();
      this.getCollectList();
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
