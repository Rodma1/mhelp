<template>
  <div class="my">
    <my-message ref="myMessage" :show="isShow"></my-message>
    <my-task class="myTask" @click.native="goTaskList"></my-task>
    <scroll class="contents" ref="scroll" :probeType="3">
      <my-wallet class="myWallet"></my-wallet>
      <my-shop class="myShop"></my-shop>
      <my-bill class="myBills"></my-bill>
      <text-combat class="textCombat"></text-combat>
      <images></images>
    </scroll>
  </div>
</template>
<script>
// import myNav from 'views/my/componentsChildren/myNav'
import myMessage from "views/my/componentsChildren/myMessage.vue";
import myTask from "views/my/componentsChildren/myTask.vue";
import myWallet from "views/my/componentsChildren/mywallet.vue";
import myShop from "views/my/componentsChildren/myShop.vue";
import myBill from "views/my/componentsChildren/mybills.vue";
import textCombat from "views/my/componentsChildren/textCombat.vue";
import images from "views/my/componentsChildren/image.vue";
import scroll from "components/common/scroll/scroll.vue";

export default {
  data() {
    return {
      isShow: true,
    };
  },
  components: {
    // myNav,
    myMessage,
    myTask,
    myWallet,
    myShop,
    myBill,
    textCombat,
    images,
    scroll,
  },
  created() {},
  activated() {
    this.show();
  },
  methods: {
    goTaskList() {
      this.$router.push("./taskList/0");
    },
    show() {
      localStorage.token ? (this.isShow = false) : (this.isShow = true);
    },
  },
};
</script>
<style scoped>
.my {
  position: relative;
  background: #f8f7f7;
  height: 100vh;
}
.myTask {
  position: absolute;
  top: 150px;
  left: 2.5%;
}
.myWallet,.myShop,.myBills,.textCombat {
  margin-bottom: 10px;
}
.contents{
  position: absolute;
  top: 260px;
  bottom: 44px;
  left: 0px;
  right: 0px;
  overflow: hidden;
}
</style>