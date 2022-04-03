<template>
  <div class="homeSearchTasks">
    <search-task-nav-bar :value="value"></search-task-nav-bar>
    <scroll
      class="contents"
      ref="scroll"
      @scroll="contentScroll"
      :probeType="3"
    >
      <search-task class="searchTasks" :task="searchTaskList"></search-task>
    </scroll>
    <back-top @click.native="backTop" v-show="isShowBackTop"></back-top>
  </div>
</template>
<script>
import searchTaskNavBar from "views/home/componentsChildren/searchTaskNavBar.vue"
import scroll from "components/common/scroll/scroll.vue";
import searchTask from "views/home/componentsChildren/homeTask.vue";
import backTop from "components/content/backTop/backTop.vue";
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
    };
  },
  props: {
    searchTaskList: {
      type: Array,
      default() {
        return [];
      },
    },
    value:{
      type:String,
      default:""
    }
  },
  mounted() {
    //   this.$bus.$on("searched",(task)=>{
    //       console.log(11);
    //       this.task=task;
    //       console.log(this.task)
    //   })
  },
  methods: {
    contentScroll(position) {
      this.isShowBackTop = -position.y > 1000;
      this.isFixed = -position.y > this.offsetTop;
    },
    backTop() {
      this.$refs.scroll.scroller(0, 0, 500);
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
</style>