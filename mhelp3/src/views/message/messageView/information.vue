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
  mounted() {
    this.getfriendList();
    this.noread();
  },
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
    this.getfriendList();
    this.noread();
  },
  methods: {
    goChat(index) {
      this.$store.commit("deleteCurrentUser")
      this.userId = this.friendList[index].id;
      this.$router.push("/chat/" + this.userId);
      this.$store.commit("saveCurrentUser", this.friendList[index]);
      console.log(this.$store.state.currentUser);
    },
    getfriendList() {
      this.friendList = [];
      getuaccepttasks(this.$store.state.token, this.page).then((res) => {
        console.log(res);
        var friend;
        for (var i = 0; i < res.data.length; i++) {
          friend = {
            id: "",
            avatar: "",
            nickname: "",
            noread: [],
          };
          if (
            res.data[i].authorId &&
            res.data[i].authorId != this.$store.state.id
          ) {
            var a = this.friendList.find((item) => {
              return item.id == res.data[i].authorId;
            });
            if (!a) {
              friend.id = res.data[i].authorId;
              friend.avatar = res.data[i].avatar;
              friend.nickname = res.data[i].author;
              this.friendList.push(friend);
            }
          }
        }
      });
    },
    noread() {
      noReadMsg(this.$store.state.id).then((res) => {
        this.noReadMsg = [];
        console.log(res)
        for (var i = 0; i < res.data.length; i++) {
          if (this.noReadMsg.length == 0) {
            var a = {
              id: "",
              msg: [],
            };
            a.id = res.data[i].sendUserId;
            a.msg.push(res.data[i].msg);
            this.noReadMsg.push(a);
            // console.log(this.noReadMsg)
          } else {
            var cur;
            this.noReadMsg.forEach((item) => {
              cur = [];
              if (item.id == res.data[i].sendUserId&&res.data[i].sendUserId!==this.$store.state.id) {
                item.msg.push(res.data[i].msg)
                return;
              } else {
                a = {
                  id: "",
                  msg: [],
                };
                a.id = res.data[i].sendUserId;
                a.msg.push(res.data[i].msg);
              }
              cur.push(a);
            });
            this.noReadMsg.push(...cur);
          }
        }
        // console.log(this.noReadMsg);
        // console.log(this.friendList);
        this.noReadMsg.forEach((l) => {
          if (this.friendList.length > 1) {
            for (var v = 0; v < this.friendList.length; v++) {
              if (this.friendList[v].id == l.id) {
                // console.log(this.friendList[v]);
                this.friendList[v].noread = l.msg;
              }
            }
          }
          else{
            this.friendList[0].noread=l.msg
          }
        });
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