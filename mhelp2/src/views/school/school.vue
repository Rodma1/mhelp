<template>
  <div class="school">
    <router-view class="routerView"></router-view>
    <school-nav-bar @click.native="goSearch"></school-nav-bar>
    <scroll
      class="contents"
      ref="scroll"
      @scroll="contentScroll"
      :probeType="3"
    >
      <school-name
        @schoolList="schoolList"
        :findX="findX"
        :findY="findY"
      ></school-name>
    </scroll>
  </div>
</template>
<script>
import schoolNavBar from "views/school/childrenComponents/schoolNavBar.vue";
import schoolName from "views/school/childrenComponents/schoolName.vue";
import scroll from "components/common/scroll/scroll.vue";
import { itemListenerMixin, backTopMixins } from "mixins/mixins.js";
import { pinyin } from "pinyin-pro";
export default {
  components: {
    schoolNavBar,
    schoolName,
    scroll,
  },
  data() {
    return {
      schoolsList: [],
      findX: null,
      findY: null,
    };
  },
  mixins: [itemListenerMixin, backTopMixins],
  mounted() {
    this.scroolToSchool();
  },
  methods: {
    goSearch() {
      this.$router.push("/school/schoolSearchView");
    },
    scroolToSchool() {
      this.$bus.$on("scroolToSchool", (school) => {
        var s = pinyin(school).substr(0, 1).toUpperCase();
        var listLength = 0;
        for (var i = 0; i < this.schoolsList.length; i++) {
          var ls;
          if (s == this.schoolsList[i].arc) {
            for (var j = 0; j < this.schoolsList[i].list.length; j++) {
              if (this.schoolsList[i].list[j].name == school) {
                this.$refs.scroll.scroller(
                  0,
                  -((i + 1) * 32 + (listLength + j) * 46),
                  100
                );
                this.findX = i;
                this.findY = j;
                return;
              }
            }
          } else {
            ls = this.schoolsList[i].list.length;
            listLength = listLength + ls;
          }
        }
        console.log(listLength);
      });
    },
    schoolList(schoolList) {
      // console.log(schoolList);
      this.schoolsList = schoolList;
      console.log(this.schoolsList);
    },
    contentScroll(position) {
      // console.log(position)
      this.isShowBackTop = -position.y > 1000;
      this.isFixed = -position.y > this.offsetTop;
    },
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
.contents {
  position: absolute;
  top: 44px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  overflow: hidden;
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