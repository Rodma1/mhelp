<template>
  <div class="publisHistory" >
    <scroll class="contents" ref="scroll" :probeType="3" v-if="logingShow">
      <publish-list></publish-list>
    </scroll>
    <no-loging v-else></no-loging>
  </div>
</template>
<script>
import publishList from "views/publish/componentsChildren/publishList.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue"
import { itemListenerMixin, backTopMixins ,isLoging} from "mixins/mixins.js";
export default {
  components: {
    publishList,
    scroll,
    noLoging

  },
  mixins: [itemListenerMixin, backTopMixins,isLoging],
  data(){
    return{
      isShow:false
    }
  },
  mounted(){
  },
  methods:{
    // isLoging(){
    //   if(this.$store.state.id){
    //     this.isShow=true
    //   }
    //   else{
    //     this.isShow=false
    //   }
    // }
  }
};
</script>

<style scoped>
.publisHistory {
  background: #f3f3f3;
  padding: 5px;
}
.contents{
    height: 100%;
    overflow: hidden;
}

</style>