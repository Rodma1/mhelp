<template>
  <div class="home">
    <router-view class="routerView"></router-view>
    <home-nav-bar @isShowClick="isShow()" ref="navBar"></home-nav-bar>
    <home-category
      v-if="isActive"
      class="category"
      :category="category"
      @categorySearch="categorySearch"
      @tagsTasks="tagsTasks"
      :tags="tags"
    ></home-category>
    <scroll
      class="contents"
      ref="scroll"
      @scroll="contentScroll"
      :probeType="3"
      :pullUpLoad="true"
      @pullingMore="pullingMore"
    >
      <home-search></home-search>
      <home-task :task="task" :page="page" ref="homeTask"></home-task>
    </scroll>
    <back-top @click.native="backTop" v-show="isShowBackTop"></back-top>
    <toast
      :message="toast"
      :logingShow="!logingShow"
      class="toast"
      @click.native="tologing"
    ></toast>
    <div :class="{ mask: isActive }" @click="isShow"></div>
  </div>
</template> 
<script>
import homeNavBar from "views/home/componentsChildren/homeNavBar.vue";
import homeCategory from "views/home/componentsChildren/homeCategory.vue";
import homeSearch from "views/home/componentsChildren/homeSearch.vue";
import scroll from "components/common/scroll/scroll.vue";
import homeTask from "views/home/componentsChildren/homeTask.vue";
import backTop from "components/content/backTop/backTop.vue";
import toast from "components/common/toast/toast.vue";
import { getTask, getCategory, getTags } from "network/task.js";
import { itemListenerMixin, backTopMixins, isLoging } from "mixins/mixins.js";
// import {getuaccepttasks} from "network/task.js"
import { categoryTask } from "network/task.js";
export default {
  components: {
    homeNavBar,
    homeCategory,
    homeSearch,
    scroll,
    homeTask,
    backTop,
    toast,
  },
  mixins: [itemListenerMixin, backTopMixins, isLoging],
  data() {
    return {
      isActive: false,
      page: {
        pageNumber: 0,
        pageSize: 20,
      },
      task: [],
      category: [],
      tags: [],
      isShowBackTop: false,
      isFixed: false,
      offsetTop: 0,
      toast: "您当前未登录，请先登录！",
      logingShow: false,
      params: { page: 0, pageSize: 10, categoryId: null },
    };
  },
  created() {
    this.getTasks();
    this.getCategory();
    this.getTags();
    // this.getuaccepttasks();
    console.log(this.$route);
  },
  activated() {
    this.$bus.$on("clear", () => {
      this.refreshing();
    });
    this.$bus.$on("schoolTasks", (data) => {
      console.log(this.task);
      this.task = [];
      this.page = {
        pageNumber: 0,
        pageSize: 20,
      };
      var a = this.task.length;
      this.page.pageNumber = this.page.pageNumber + 1;
      for (var j = 0; j < data.length; j++) {
        if (!data[j].status) {
          this.task.push(data[j]);
        }
      }
      console.log(this.task);
      for (var i = a; i < this.task.length; i++) {
        if (this.task[i].images) {
          this.task[i].images = this.task[i].images.split(",");
        }
      }
    });
  },
  computed: {},
  methods: {
    isShow() {
      this.isActive = !this.isActive;
      this.$refs.navBar.isActive = !this.$refs.navBar.isActive;
    },
    getTasks() {
      console.log(1);
      this.page.pageNumber = this.page.pageNumber + 1;
      getTask(this.page)
        .then((res) => {
          console.log(res);
          var a = this.task.length;
          for (var j = 0; j < res.data.length; j++) {
            if (!res.data[j].status) {
              this.task.push(res.data[j]);
            }
          }
          console.log(this.task);
          for (var i = a; i < this.task.length; i++) {
            if (this.task[i].images) {
              this.task[i].images = this.task[i].images.split(",");
            }
          }
        })
        .catch((res) => {
          console.log(res);
        });
    },
    getCategory() {
      getCategory().then((res) => {
        this.category.push(...res.data);
      });
      console.log(this.category);
    },
    getTags() {
      getTags().then((res) => {
        // console.log(res);
        this.tags.push(...res.data);
        console.log(this.tags);
      });
    },
    backTop() {
      this.$refs.scroll.scroller(0, 0, 500);
    },
    contentScroll(position) {
      // console.log(position)
      this.isShowBackTop = -position.y > 1000;
      this.isFixed = -position.y > this.offsetTop;
      if (position.y > 50 && position.y < 51 && position.y > 0) {
        this.page = {
          pageNumber: 0,
          pageSize: 20,
        };
        this.getTasks();
      }
    },
    pullingMore() {
      this.getTasks();
      this.$refs.homeTask.getCollectList();
    },
    refreshing() {
      (this.page = {
        pageNumber: 0,
        pageSize: 20,
      }),
        (this.task = []);
      this.$refs.homeTask.getCollectList();
      this.getTasks();
    },
    categorySearch(index) {
      console.log(this.category[index]);
      this.task = [];
      this.params.categoryId = this.category[index].id;
      categoryTask(this.params).then((res) => {
        console.log(res);
        var a = this.task.length;
        // this.data.searchTaskList.push(...res.data);
        for (var j = 0; j < res.data.length; j++) {
          if (!res.data[j].status) {
            this.task.push(res.data[j]);
          }
        }
        console.log(this.task);
        for (var i = a; i < this.task.length; i++) {
          if (this.task[i].images) {
            this.task[i].images = this.task[i].images.split(",");
          }
        }
        console.log(this.task);
        this.isActive = false;
      });
    },
    tagsTasks(index) {
      console.log(index);
      this.task = [];
    },
    tologing() {
      this.$router.push("/loging");
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
  bottom: 0px;
  top: 44px;
  left: 0px;
  right: 0px;
  width: 100%;
  background-color: rgba(77, 74, 74, 0.2);
  z-index: 10;
}

.category {
  position: absolute;
  top: 44px;
  left: 0px;
  right: 0px;
}
.routerView {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 11;
}
.toast {
  height: 35px;
  position: absolute;
  bottom: 49px;
  left: 0px;
  right: 0px;
  text-align: center;
  line-height: 35px;
}
</style> 
