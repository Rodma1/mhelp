<template>
  <div class="chatTabBar">
    <div class="chatBox">
      <textarea
        type="text"
        name=""
        id=""
        v-model="value"
        @input="judge"
        @click="hide"
        @keyup.enter="send"
      />
    </div>
    <div class="all" @click="goemojis">
      <img src="@/assets/img/message/smell.png" alt="" />
    </div>
    <div class="all">
      <img
        src="@/assets/img/message/add.png"
        alt=""
        v-if="isActive"
        @click="more"
      />
      <img src="@/assets/img/message/send.png" alt="" v-else @click="send" />
    </div>
  </div>
</template>
<script>
// import axios from "axios";
export default {
  data() {
    return {
      isActive: true,
      value: "",
      textarea: null,
    };
  },
  props: {
    isActiveEmojis: {
      type: Boolean,
      default: false,
    },
  },
  components: {},
  mounted() {
    this.textarea = document.querySelector("textarea");
    this.$bus.$on("choosed", (item) => {
      this.value = this.value + item;
      this.isActive = false;
      // this.textarea.focus()
    });
    this.$bus.$on("delete", () => {
      this.value = this.value.slice(0, this.value.length - 2);
      if (!this.value) {
        this.isActive = true;
      }
    });
    // this.init()
  },
  methods: {
    judge(e) {
      // console.log(e.target)
      if (this.value) {
        this.isActive = false;
        this.textarea.focus();
        // console.log(this.value);
        // console.log(this.textarea.scrollTop)
        // console.log(e.target.scrollHeight + "px");
        // this.textarea.style.height = "30px";
        this.textarea.style.height = e.target.scrollHeight + "px";
        if (parseInt(this.textarea.style.height) < 100) {
          this.textarea.style.height = e.target.scrollHeight + "px";
        } else {
          this.textarea.style.height = "100px";
        }
      } else {
        this.isActive = true;
      }
    },
    send() {
      this.init();
      if (this.isActiveEmojis) {
        this.textarea.blur();
      } else {
        this.textarea.focus();
      }
      // axios({
      //   url: "http://127.0.0.1:8000/student/model",
      //   method: "post",
      //   headers: { Authorization: "" },
      //   data: {
      //     wenben: this.value,
      //   },
      // }).then((res) => {
        // this.after = res.data;
        // if (res.data == this.value) {
          this.$emit("send", this.value);
          this.textarea.style.height = "30px";
          this.value = "";
          this.isActive = true;
        // }
        // else{
          // this.value=""
          // this.$toast("您发的信息具有侮辱性词汇，不允许发送")
        // }
      // });

      // this.isActive=true
    },
    goemojis() {
      this.$emit("showEmojis");
    },
    hide() {
      this.$emit("hide");
    },
    more() {
      this.$emit("showMore");
      // console.log(11)
    },
    init() {
      if (this.value) {
        this.isActive = false;
      } else {
        this.isActive = true;
      }
    },
  },
};
</script>
<style scoped>
.chatTabBar {
  background: #e6e4e4;
  display: flex;
  align-items: center;
  padding: 0px 10px;
}
.chatBox {
  flex: 1;
}
.chatBox textarea {
  margin: 8px 0px;
  resize: none;
  caret-color: rgb(70, 70, 241);
  font-size: 18px;
  width: 100%;
  height: 30px;
  outline: 0px;
  border: 0px;
  background: white;
  scroll-behavior: smooth;
  vertical-align: center;
}
.all {
  width: 30px;
  height: 30px;
  margin-left: 5px;
  /* line-height: 50px; */
  /* background: blue; */
}
.all img {
  width: 30px;
  height: 30px;
  /* margin: 10px; */
  /* margin-left: 5px; */
}
</style>
