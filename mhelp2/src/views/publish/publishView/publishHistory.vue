<template>
  <div class="publisHistory" >
    <scroll class="contents" ref="scroll" :probeType="3" v-if="logingShow">
      <publish-list :list="list"></publish-list>
    </scroll>
    <no-loging v-else></no-loging>
  </div>
</template>
<script>
import publishList from "views/publish/componentsChildren/publishList.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue"
import { itemListenerMixin, backTopMixins ,isLoging} from "mixins/mixins.js";
import {getPublishList} from "network/task.js"
export default {
  components: {
    publishList,
    scroll,
    noLoging

  },
  mixins: [itemListenerMixin, backTopMixins,isLoging],
  data(){
    return{
      isShow:false,
       page: {
        pageNumber: 1,
        pageSize: 30,
      },
      list:[],
      friendList:[]
    }
  },
  mounted(){
    this.getPublishList()
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
    getPublishList(){
      this.friendList=[]
      getPublishList(this.$store.state.token,this.page).then((res)=>{
        this.list=res.data;
        console.log(this.list)
        for(var i=0;i<this.list.length;i++){
          if(this.list[i].images){
            this.list[i].images=this.list[i].images.split(",")
          }
          if(this.list[i].acceptUserId){
            this.friendList.push(this.list[i].acceptUserId)
          }
        }
        console.log(this.friendList);
        
      })
    }
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