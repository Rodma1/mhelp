<template>
  <div class="pay">
    <div class="box">
      <div class="exist">
        <img src="@/assets/img/home/取消.png" alt="" @click="exist" />
      </div>
      <div>支付</div>
      <div>{{item.money}}金币</div>
      <div>
        <div class="first" v-if="isShow == 0" @click="gopay">确认支付</div>
        <img
          src="@/assets/img/home/加载.png"
          class="second"
          v-else-if="isShow == 1"
        />
        <div class="third" v-else>支付成功</div>
      </div>
    </div>
  </div>
</template>
<script>
import { acceptTask } from "network/task.js";
export default {
  data() {
    return {
      isShow: 0,
      item:{}
    };
  },
  create(){
    this.$bus.$on("getItem",(item)=>{
      this.item=item
    })
    console.log(this.item)
  },
  methods: {
    exist() {
      this.$router.back();
    },
    gopay() {
      this.isShow = 1;
      var id = this.$route.params.id;
      setTimeout(() => {
        this.isShow = 2;
        acceptTask(this.$store.state.token, id,this.item.money).then((res) => {
          console.log(res);
        });
        setTimeout(() => {
          this.$router.back();
        }, 800);
      }, 300);
    },
  },
};
</script>

<style scoped>
.pay {
  background-color: rgba(0, 0, 0, 0.4);
  width: 100%;
  z-index: 20;
}
.box {
  height: 250px;
  width: 80%;
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  right: 50%;
  transform: translate(-50%, -50%);
  /* margin: 0px 40px; */
  border-radius: 10px;
}
.exist img {
  height: 30px;
}
.box {
  text-align: center;
  padding: 10px;
}
.box > div:nth-child(1) {
  text-align: right;
}
.box div:nth-child(2) {
  margin-top: 10px;
  font-size: 18px;
  font-weight: 500;
}
.box div:nth-child(3) {
  height: 30px;
  font-size: 28px;
}
.box div:nth-child(4) {
  height: 40px;
  width: 70%;
  background-color: #1facf8;
  margin: 40px auto;
  font-size: 20px;
  color: white;
  line-height: 40px;
  text-align: center;
}
.first,
.third {
  text-align: center;
}
.second {
  height: 30px;
  animation: rotate 1s infinite;
}
@keyframes rotate {
  from {
    transform: rotateZ(0deg);
  }

  to {
    transform: rotateZ(360deg);
  }
}
</style>