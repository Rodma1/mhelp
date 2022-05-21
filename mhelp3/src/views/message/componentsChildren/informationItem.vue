<template>
  <div class="informationItem">
    <img :src="item.avatar" alt="" @load="itemImageLoad"/>
    <div class="content">
      <div class="netName">{{item.nickname}}</div>
      <div class="newMessage">{{ chatSnapShotMsg }}</div>
    </div>
    <div class="time">
      <div class="timeTime">16:42</div>
      <div class="new" v-if="isShow">{{this.item.noReadMsg.length}}</div>
    </div>
  </div>
</template>
<script>
import { getChatSnapShot } from "network/chatList.js";
export default {
  data() {
    return {
      chatSnapShot: [],
      chatSnapShotMsg:"",
      myId:this.$store.state.id,
      isShow:false
    };
  },
  props: {
    item: {
      type: Object,
      default() {
        return {};
      },
    },
    noReadMsg:{
      type:Object,
      default(){
        return {}
    }
    }
  },
  created() {
    this.findchatSnapShot();
  },
  activated(){
    this.itemImageLoad()
  },
  computed:{

  },
  methods: {
    findchatSnapShot() {
      this.chatSnapShot = getChatSnapShot(this.myId);
      for (var i = 0; i < this.chatSnapShot.length; i++) {
        if (this.item.id == this.chatSnapShot[i].friendId) {
            this.chatSnapShotMsg=this.chatSnapShot[i].msg;
        }
      }
    },
     itemImageLoad() {
      this.$bus.$emit("itemImageLoad");
    },
    noread(){
      if(this.item.noReadMsg){
        this.isShow=true
      }
      else{
        this.isShow=false
      }
    }
  },
};
</script>

<style scoped>
.informationItem {
  height: 70px;
  /* background-color: pink; */
  padding: 10px;
  display: flex;
  align-items: center;
  background-color: #ffffff;
  border-bottom: 1px solid #e8e8e8;
}
.informationItem img {
  width: 70px;
  height: 70px;
}
.content {
  flex: 1;
  height: 55px;
  padding-left: 10px;
}
.netName {
  font-size: 18px;
  color: #111111;
}
.newMessage {
  padding-top: 10px;
  font-size: 12px;
  color: #96939e;
}
.time {
  width: 50px;
  height: 55px;
  color: #96939e;
}
.timeTime {
  /* padding-bottom: 10px; */
  font-size: 12px;
  text-align: center;
}
.new {
  width: 15px;
  height: 15px;
  background-color: #f65452;
  color: white;
  border-radius: 50%;
  text-align: center;
  font-size: 12px;
  margin-top: 20px;
  margin-left: 20px;
}
</style>