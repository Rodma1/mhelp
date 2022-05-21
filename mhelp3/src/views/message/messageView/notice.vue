<template>
  <div class="notice">
    <scroll class="contents" v-if="logingShow">
      <Items
        v-for="(item, index) in friendList"
        :key="index"
        :item="item"
        @click.native="goChat(index)"
      ></Items>
    </scroll>
    <no-loging class="noLoging" v-else></no-loging>
  </div>
</template> 
<script>
// import noticeList from "views/message/componentsChildren/noticeList.vue";
import scroll from "components/common/scroll/scroll.vue";
import noLoging from "components/content/noLoging/nologing.vue";
import { isLoging } from "mixins/mixins.js";
import { getPublishList } from "network/task.js";
import { getOtherUserInfo } from "network/loging.js";
import Items from "views/message/componentsChildren/informationItem.vue";
export default {
  components: {
    // noticeList,
    scroll,
    noLoging,
    Items,
  },
  mixins: [isLoging],
  mounted() {
    console.log(this.logingShow);
  },
  activated() {
    this.getPublishList();
    // setTimeout(()=>{

    // },2000)
   
  },
  data() {
    return {
      friendList: [],
      page: {
        pageNumber: 1,
        pageSize: 30,
      },
      list: [],
    };
  },
  methods: {
    getPublishList() {
      this.list = [];
      getPublishList(this.$store.state.token, this.page).then((res) => {
        console.log(res);
        for (var i = 0; i < res.data.length; i++) {
          if (
            res.data[i].acceptUserId &&
            res.data[i].authorId != res.data[i].acceptUserId
          ) {
            console.log(res.data[i].acceptUserId);
            if (this.list.length == 0) {
              this.list.push({
                id: res.data[i].acceptUserId,
              });
            } else {
              console.log(2);
              var a = this.list.find((item) => {
                return item.id == res.data[i].acceptUserId;
              });
              if (!a) {
                this.list.push({
                  id: res.data[i].acceptUserId,
                });
              }
            }
          }
        }
         this.getFriendList()
      });
    },
    goChat(index) {
      this.$store.commit("deleteCurrentUser");
      this.userId = this.friendList[index].id;
      this.$router.push("/chat/" + this.userId);
      this.$store.commit("saveCurrentUser", this.friendList[index]);
      console.log(this.$store.state.currentUser);
    },
    getFriendList(){
     this.friendList=[]
      for (var j = 0; j < this.list.length; j++) {
          console.log(this.list)
          getOtherUserInfo(this.$store.state.token, this.list[j].id).then(
            (re) => {
              console.log(re);
              this.friendList.push({
                id: re.data.id,
                avatar: re.data.avatar,
                nickname: re.data.nickname,
              });
            }
          );
        }
    }
  },
};
</script>
<style scoped>
.contents {
  height: 100%;
}
</style>
