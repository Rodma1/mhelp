<template>
  <div class="notice">
    <scroll class="contents" v-if="logingShow">
      <notice-list></notice-list>
    </scroll>
    <no-loging class="noLoging" v-else></no-loging>
  </div>
</template> 
<script>
import noticeList from "views/message/componentsChildren/noticeList.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue";
import { isLoging } from "mixins/mixins.js";
export default {
  components: {
    noticeList,
    scroll,
    noLoging,
  },
  mixins: [isLoging],
  mounted() {
    console.log(this.logingShow);
  },
  activated(){
    //   console.log(22)
  }
};
</script>
<style scoped>
.contents {
  height: 100%;
}
</style>
