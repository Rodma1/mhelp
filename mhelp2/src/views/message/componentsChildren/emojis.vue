<template>
  <div class="emojis">
    <div v-for="(item, index) in emojis" :key="index" @click="choose(item)">
      {{ item }}
    </div>
    <div id="delete" @click="deleteEmoji">
      <img src="@/assets/img/message/删除.png" alt="" >
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      emojis: [
        "😀",
        "😃",
        "😄",
        "😁",
        "😆",
        "😅",
        "🤣",
        "😂",
        "🙂",
        "🙃",
        "😉",
        "😊",
        "😇",
        "🥰",
        "😍",
        "🤩",
        "😘",
        "😗",
        "😚",
        "😙",
        "🥲",
        "😋",
        "😛",
        "😜",
        "🤪",
        "😝",
        "🤑",
        "🤗",
        "🤭",
        "🤫",
        "🤔",
        "🤐",
        "🤨",
        "😐",
        "😑",
        "😶",
        "😏",
        "😒",
        "🙄",
        "😬",
        "🤥",
        "😌",
        "😔",
        "😪",
        "🤤",
        "😴",
        "😷",
        "🤒",
        "🤕",
        "🤢",
        "🤮",
        "🤧",
        "🥵",
        "🥶",
        "🥴",
        "😵",
        "🤯",
        "🤠",
        "🥳",
        "🥸",
        "😎",
        "🤓",
        "🧐",
        "😕",
        "😟",
        "🙁",
        "☹️",
        "😮",
        "😯",
        "😲",
        "😳",
        "🥺",
        "😦",
        "😧",
        "😨",
        "😰",
        "😥",
        "😢",
        "😭",
        "😱",
        "😖",
        "😣",
        "😞",
        "😓",
        "😩",
        "😫",
        "🥱",
        "😤",
        "😡",
        "😠",
        "🤬",
      ],
    };
  },
  methods: {
    choose(item) {
      this.$bus.$emit("choosed", item);
    },
    deleteEmoji(){
      this.$bus.$emit("delete")
    }
  },
};
</script>

<style scoped>
.emojis {
  /* padding: 10px; */
  height: 250px;
  display: flex;
  background: #f3f3f3;
  flex-wrap: wrap;
  /* justify-content: center; */
  text-align: center;
  align-items: center;
}
.emojis div {
  width: 30px;
  height: 30px;
  font-size: 19px;
  text-align: center;
}
#delete{
  width: 50px;
  height: 30px;
  position: fixed;
  bottom: 5px;
  right: 10px;
  background: white;
}
#delete img{
  width: 30px;
  height: 20px;
}
</style>