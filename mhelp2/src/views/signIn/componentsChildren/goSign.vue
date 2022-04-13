<template>
  <div class="goSign">
    <div class="signBtn" @click="sign" v-if="!isSign">点击签到</div>
    <div class="signBtn" v-else>已签到</div>
    <span>你已经签到10天了！</span>
  </div>
</template>s
<script>
import { sign } from "network/sign.js";
export default {
  data() {
    return {
      currentDay:0
    };
  },
  props:{
    isSign:{
      type:Number,
      default(){
        return 0
      }
    }
  },
  mounted(){
    // this.todaysign()
    // console.log(this.isSign)
  },
  methods: {
    sign() {
        sign(this.$store.state.token).then((res) => {
          console.log(res);
          var date = new Date();
          this.currentDay = date.getDate();
          this.$bus.$emit('sign',this.currentDay) 
        });
         
    },
    // todaysign(){
    //   todaysign(this.$store.state.token).then((res)=>{
    //     // console.log(res) 
    //     this.isSign=res.data
    //     // console.log(this.isSign)
    //   })
    // } 
  },
};
</script>
<style scoped>
.goSign {
  height: 140px;
  /* background: #426eb7; */
  position: relative;
  color: black;
  background-image: url("../../../assets/img/signIn/太空发展.png");
  background-size: 60% 100%;
  background-repeat: no-repeat;
  background-position: center;
}
.signBtn {
  width: 120px;
  height: 40px;
  border: 2px solid black;
  text-align: center;
  line-height: 40px;
  position: absolute;
  top: 20px;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 20px;
  font-size: 20px;
  font-weight: 500;

}
.goSign span {
  position: absolute;
  top: 70px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
