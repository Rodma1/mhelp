<template>
  <div class="list">
    <scroll
      class="contents"
      ref="scroll"
      @scroll="contentScroll"
      :probeType="3"
      :pullUpLoad="true" 
      @pullingMore="pullingMore"
    >
      <item v-for="(item, index) in types" :key="index" :item="item"></item>
    </scroll>
    
    <back-top @click.native="backTop" v-show="isShowBackTop"></back-top>
  </div>
</template>
<script>
import item from "views/taskList/componentsChildren/item.vue";
import scroll from "components/common/scroll/scroll.vue";
import { backTopMixins,itemListenerMixin } from "mixins/mixins.js";
import backTop from "components/content/backTop/backTop.vue";
export default {
  components: {
    item,
    scroll,
    backTop,
  },
  data() {
    return {
      isShowBackTop: false,
      count: 5,
      isTask: false,
    };
  },
  props: {
    types: {
      type: Array,
      default() {
        return [];
      },
    },
  },

  mixins: [backTopMixins,itemListenerMixin],
  methods: {
    contentScroll(position) {
      // console.log(position)
      this.isShowBackTop = -position.y > 1000;
      this.isFixed = -position.y > this.offsetTop;
    },
    pullingMore(){
      this.$emit("pullingMore")
    }
  },
};
</script>
<style scoped>
.list {
  position: absolute;
  top: 79px;
  bottom: 0px;
  left: 0px;
  right: 0px;
  padding: 10px 13px;
  /* margin: 10px 10px 10px 0px; */
  /* background: red; */
}
.contents {
  height: 100%;
  overflow: hidden;
}

</style>