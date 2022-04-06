<template>
  <div class="notice">
    <scroll class="contents" v-if="logingShow">
      <Items v-for="(item ,index) in friendList" :key="index" :item="item"></Items>
    </scroll>
    <no-loging class="noLoging" v-else></no-loging>
  </div>
</template> 
<script>
// import noticeList from "views/message/componentsChildren/noticeList.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue";
import { isLoging } from "mixins/mixins.js";
import {getPublishList} from "network/task.js"
import Items from "views/message/componentsChildren/informationItem.vue";
export default {
  components: {
    // noticeList,
    scroll,
    noLoging,
    Items
  },
  mixins: [isLoging],
  mounted() {
    console.log(this.logingShow);
    
  },
  activated(){
    //   console.log(22)
    this.getPublishList()
  },
  data(){
    return{
      friendList:[],
       page: {
        pageNumber: 1,
        pageSize: 30,
      },
    }
  },
  methods:{
    getPublishList(){
      this.friendList=[]
      getPublishList(this.$store.state.token,this.page).then((res)=>{
        for(var i=0;i<res.data.length;i++){
          if(res.data[i].acceptUserId&&res.data[i].status==1){
            this.friendList.push({id:res.data[i].acceptUserId,avatar:res.data[i].avatar,nickname:res.data[i].author})
          }
        }
        console.log(this.friendList);
      })
    }
  }
};
</script>
<style scoped>
.contents {
  height: 100%;
}
</style>
