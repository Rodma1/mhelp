<template>
  <div class="information">
    <scroll class="contents" v-if="logingShow">
      <items
        v-for="(item, index) in this.friendList"
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
import { noReadMsg } from "network/task.js";
import { getuaccepttasks } from "network/task.js";
export default {
  components: {
    Items,
    scroll,
    noLoging,
  },
  mixins: [isLoging],
  mounted() {},
  data() {
    return {
      chatList: this.$store.state.chatList,
      userId: "",
      noReadMsg: [],
      page: {
        pageNumber: 1,
        pageSize: 10,
      },
      friendList: [],
    };
  },
  activated() {
    noReadMsg(this.$store.state.id).then((res) => {
      console.log(res);
    });
    this.getfriendList();
  },
  methods: {
    goChat(index) {
      this.userId = this.friendList[index].id;
      this.$router.push("/chat/" + this.userId);
      this.$store.commit("saveCurrentUser",this.friendList[index]);
      console.log(this.friendList[index])
    },
    getfriendList() {
      this.friendList = [];
      getuaccepttasks(this.$store.state.token, this.page).then((res) => {
        var friend;
        for (var i = 0; i < res.data.length; i++) {
          friend = {
            id: "",
            avatar: "",
            nickname: "",
          };
          if (res.data[i].status == 1&&res.data[i].author) {
            friend.id = res.data[i].acceptUserId;
            friend.avatar = res.data[i].avatar;
            friend.nickname = res.data[i].author;
            this.friendList.push(friend);
          }
        }
        console.log(this.friendList)
      });
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