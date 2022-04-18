<template>
  <div class="homeSearchTasks">
    <router-view class="routerView"></router-view>
    <search-task-nav-bar :value="params.words"></search-task-nav-bar>
    <scroll
      class="contents"
      ref="scroll"
      @scroll="contentScroll"
      :probeType="3"
      :pullUpLoad="true" 
      @pullingMore="pullingMore"
    >
      <search-task class="searchTasks" :task="params.searchTaskList"></search-task>
    </scroll>
    <back-top @click.native="backTop" v-show="isShowBackTop"></back-top>
  </div>
</template>
<script>
import searchTaskNavBar from "views/home/componentsChildren/searchTaskNavBar.vue"
import scroll from "components/common/scroll/scroll.vue";
import searchTask from "views/home/componentsChildren/homeTask.vue";
import backTop from "components/content/backTop/backTop.vue";
import {itemListenerMixin,backTopMixins}from "mixins/mixins.js"
import { searchTasks } from "network/task.js";
export default {
  components: {
    searchTaskNavBar,
    scroll,
    searchTask,
    backTop,
  },
  data() {
    return {
      isShowBackTop: false,
      params: {
        page: 0,
        pageSize: 10,
        words: "",
        schoolId: 1,
        searchTaskList: [],
      },
    };
  },
  mixins:[itemListenerMixin,backTopMixins],
  props: {
  },
  created() {
    console.log(this.$route)
    this.params.words=this.$route.params.value
  },
  mounted(){
    this.getSearchTasks()
    console.log(this.params.searchTaskList)
  },
  methods: {
    contentScroll(position) {
      this.isShowBackTop = -position.y > 1000;
      this.isFixed = -position.y > this.offsetTop;
    },
    backTop() {
      this.$refs.scroll.scroller(0, 0, 500);
    },
    pullingMore(){
      this.$emit("pullingMore")
    },
     getSearchTasks() {
      this.params.page = this.params.page + 1;
      searchTasks(this.params).then((res) => {
        console.log(res);
        var a = this.params.searchTaskList.length;
        // this.data.searchTaskList.push(...res.data);
         for(var j=0;j<res.data.length;j++){
            if(!res.data[j].status){
              this.params.searchTaskList.push(res.data[j])
            }
          }
          console.log(this.params.searchTaskList)
        for (var i = a; i < this.params.searchTaskList.length; i++) {
          if (this.params.searchTaskList[i].images) {
            this.params.searchTaskList[i].images =
              this.params.searchTaskList[i].images.split(",");
          }
        }
      });
    },
  },
};
</script>

<style scoped>
.homeSearchTasks {
  height: 100vh;
  background-color: #f3f3f3;
}
.contents {
  width: 100%;
  position: absolute;
  top: 44px;
  bottom: 0px;
  overflow: hidden;
}
.searchTasks {
  height: 100%;
}
.routerView {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 15;
}
</style>