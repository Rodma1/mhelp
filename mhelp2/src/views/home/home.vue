<template>
  <div class="home">
    <home-nav-bar @isShowClick="isShow()"></home-nav-bar>
    <div :class="{ mask: isActive }" @click="isShow"></div>
    <home-category v-if="isActive" class="category"></home-category>
    <scroll class="contents" ref="scroll" @scroll="contentScroll" :probeType="3">
      <home-search></home-search>
      <home-task :task="task"></home-task>
    </scroll>
    <back-top @click.native="backTop" v-show="isShowBackTop" ></back-top>
  </div>
</template>
<script>
import homeNavBar from "views/home/componentsChildren/homeNavBar.vue";
import homeCategory from "views/home/componentsChildren/homeCategory.vue";
import homeSearch from "views/home/componentsChildren/homeSearch.vue";
import scroll from "components/common/scroll/scroll.vue";
import homeTask from "views/home/componentsChildren/homeTask.vue";
import backTop from "components/content/backTop/backTop.vue"
import { getTask, getCategory, getTags } from "network/task.js";

// import {getuaccepttasks} from "network/task.js"
export default {
  components: {
    homeNavBar,
    homeCategory,
    homeSearch,
    scroll,
    homeTask,
    backTop,
  },
  data() {
    return {
      isActive: false,
      page: {
        pageNumber: 0,
        pageSize: 9,
      },
      task: [],
      category: [],
      tags: [],
      isShowBackTop:false,
      isFixed:false,
      offsetTop:0,
    };
  },
  created() {
    this.getTasks();
    this.getCategory();
    this.getTags();
    // this.getuaccepttasks();
  },
  computed: {},
  methods: {
    isShow() {
      this.isActive = !this.isActive;
    },
    getTasks() {
      this.page.pageNumber = this.page.pageNumber + 1;
      getTask(this.page)
        .then((res) => {
          console.log(res);
          this.task.push(...res.data);
          console.log(this.task);
        })
        .catch((res) => {
          console.log(res);
        });
    },
    getCategory() {
      getCategory().then((res) => {
        console.log(res);
        this.category.push(...res.data);
        console.log(this.category);
      });
    },
    getTags() {
      getTags().then((res) => {
        console.log(res);
        this.tags.push(...res.data);
        console.log(this.tags);
      });
    },
    // getuaccepttasks() {
    //   getuaccepttasks(this.page).then((res) => {
    //     console.log(res);
    //   });
    // },
    backTop(){
      this.$refs.scroll.scroller(0,0,500);
    },
    contentScroll(position){
      // console.log(position)
      this.isShowBackTop=(-position.y)>1000
      this.isFixed=(-position.y)>this.offsetTop
    },
  },
};
</script>
<style scoped>
.home {
  height: 100vh;
  position: relative;
  background-color: #f3f3f3;
}
.contents {
  width: 100%;
  position: absolute;
  top: 44px;
  bottom: 49px;
  overflow: hidden;
}
.mask {
  position: absolute;
  bottom: 49px;
  top: 44px;
  width: 100%;
  background-color: rgba(77, 74, 74, 0.2);
  z-index: 9;
}

.category {
  position: absolute;
  top: 44px;
}
</style>
