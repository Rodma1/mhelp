<template>
  <div class="publisHistory">
    <scroll
      class="contents"
      ref="scroll"
      :probeType="3"
      v-if="logingShow"
      :pullUpLoad="true"
      @pullingMore="pullingMore"
      @scroll="contentScroll"
    >
      <publish-list :list="list"></publish-list>
    </scroll>
    <no-loging v-else></no-loging>
  </div>
</template>
<script>
import publishList from "views/publish/componentsChildren/publishList.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue";
import { itemListenerMixin, backTopMixins, isLoging } from "mixins/mixins.js";
import { getPublishList } from "network/task.js";
export default {
  components: {
    publishList,
    scroll,
    noLoging,
  },
  mixins: [itemListenerMixin, backTopMixins, isLoging],
  data() {
    return {
      isShow: false,
      page: {
        pageNumber: 0,
        pageSize: 20,
      },
      list: [],
      friendList: [],
    };
  },
  mounted() {
    this.getPublishList();
    this.$bus.$on("clear",()=>{
      this.refreshing()
    })
  },
  methods: {
    getPublishList() {
      this.page.pageNumber = this.page.pageNumber + 1;
      // this.friendList=[]
      var a = this.list.length;
      getPublishList(this.$store.state.token, this.page).then((res) => {
        console.log(res)
        this.list.push(...res.data);
        for (var i = a; i < this.list.length; i++) {
          if (this.list[i].images) {
            this.list[i].images = this.list[i].images.split(",");
          }
          if (this.list[i].acceptUserId) {
            this.friendList.push(this.list[i].acceptUserId);
          }
        }
        console.log(this.friendList);
      });
    },
    pullingMore() {
      this.getPublishList();
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
        this.getPublishList();
      }
    },
    refreshing() {
      this.page = {
        pageNumber: 0,
        pageSize: 20,
      },
      this.list  = [];
      this.getPublishList()
    },
  },
};
</script>

<style scoped>
.publisHistory {
  background: #f3f3f3;
  padding: 5px;
}
.contents {
  height: 100%;
  overflow: hidden;
}
</style>