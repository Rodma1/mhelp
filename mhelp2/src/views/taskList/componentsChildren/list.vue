<template>
  <div class="list">
    <scroll
      class="contents"
      ref="scroll"
      @scroll="contentScroll"
      :probeType="3"
    >
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
    </scroll>
    <back-top @click.native="backTop" v-show="isShowBackTop"></back-top>
  </div>
</template>
<script>
import item from "views/taskList/componentsChildren/item.vue";
import scroll from "components/common/scroll/scroll.vue";
import { backTopMixins } from "mixins/mixins.js";
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
    };
  },
  props:{
    type:{
      type:Array,
      default(){
        return []
      }
    }
  },
  mounted(){
    console.log(this.type)
  },
  mixins: [backTopMixins],
  methods: {
    contentScroll(position) {
      // console.log(position)
      this.isShowBackTop = -position.y > 1000;
      this.isFixed = -position.y > this.offsetTop;
    },
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