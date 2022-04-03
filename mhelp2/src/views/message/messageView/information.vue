<template>
  <div class="information">
    <scroll class="contents" v-if="logingShow">
      <items
        v-for="(item, index) in this.chatList"
        :key="index"
        :item="item"
        @click.native="goChat(index)"
      ></items>
    </scroll>
    <no-loging class="noLoging" v-else></no-loging>
  </div> 
</template> 
<script>
import Items from "views/message/componentsChildren/informationItem.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue";
import { isLoging } from "mixins/mixins.js";
import {noReadMsg} from "network/task.js"
export default {
  components: { 
    Items,
    scroll, 
    noLoging
  },
    mixins: [isLoging],
  data() {
    return {
      chatList: this.$store.state.chatList,
      userId:"",
      noReadMsg:[]
    };
  },
  activated(){
    noReadMsg(this.$store.state.id).then((res)=>{
      console.log(res)
    })
  },
  methods: {
    goChat(index) {
        this.userId=this.$store.state.chatList[index].id;
        this.$router.push("/chat/"+this.userId);
        this.$store.commit("saveCurrentUser",this.$store.state.chatList[index])
    },
  },
};
</script>

<style scoped>
.contents {
  height: 100%;
  overflow: hidden;
}
</style>