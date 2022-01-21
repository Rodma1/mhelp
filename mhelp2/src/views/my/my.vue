<template>
  <div class="my">
    <!-- <my-nav class="myNav"></my-nav> -->
    <my-message ref="myMessage" :show="isShow"></my-message>
    <my-task class="myTask" @click.native="goTaskList"></my-task>
    <my-wallet class="myWallet"></my-wallet>
    <my-shop class="myShop"></my-shop>
    <my-bill class="myBills"></my-bill>
  </div>
</template>
<script>
// import myNav from 'views/my/componentsChildren/myNav'
import myMessage from "views/my/componentsChildren/myMessage.vue";
import myTask from "views/my/componentsChildren/myTask.vue";
import myWallet from "views/my/componentsChildren/mywallet.vue";
import myShop from "views/my/componentsChildren/myShop.vue";
import myBill from "views/my/componentsChildren/mybills.vue";
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
    myBill
  },
  created() {},
  activated() {
      this.show()
  },
  methods: {
    goTaskList() {
      this.$router.push("./taskList/0");
    },
    show() {
      localStorage.token ? this.isShow=false : this.isShow=true;
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
.myWallet {
  position: absolute;
  top: 280px;
  left: 0px;
}
.myShop {
  position: absolute;
  top: 370px;
  left: 0px;
}
.myBills{
  position: absolute;
  top: 460px;
  left: 0px;
}
</style>