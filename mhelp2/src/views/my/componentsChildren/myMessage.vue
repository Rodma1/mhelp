<template>
  <div class="myMessage">
    <div class="noLoging" @click="goLoging" v-if="show">
      <img src="@/assets/img/my/头像.png" alt="" class="profile" />
      <div class="name">未登录</div>
      <img src="@/assets/img/my/右箭头.png" alt="" class="arrow" />
    </div>
    <div class="loging" v-else @click="tomore">
      <img :src="this.$store.state.avatar" alt="" class="profile" />
      <div class="name">{{this.$store.state.nickname}}</div>
      <div class="school">运城学院</div>
      <img src="@/assets/img/my/右箭头.png" alt="" class="arrow" />
    </div>
  </div>
</template>
<script>
export default{
  data(){
    return{
      isActive:true,
    }
  },
  props:{
    show:{
      type:Boolean,
      default:true
    }
  },
  computed:{
  },
  methods:{
    goLoging(){
      this.$router.push('/loging')
    },
    tomore(){
      if(this.$store.state.account==''){
        this.$router.push('/loging')
      }
      else{
        this.$router.push('/more')
      }
      
    }
  }
}
</script>

<style scoped>
   .myMessage {
  height: 180px;
  border-radius: 0px 0px 15px 15px;
} 
.noLoging {
  height: 200px;
  position: relative;
  background: rgb(173, 171, 171);
  /* border-radius: 0px 0px 50px 30px; */
}
.loging {
  height: 200px;
  position: relative;
  background: rgb(154, 171, 248);
  /* border-radius: 0px 0px 30px 30px; */
}
.profile {
  width: 80px;
  height: 80px;
  position: absolute;
  left: 25px;
  top: 30px;
}
.myMessage .name {
  font-size: 25px;
  position: absolute;
  left: 110px;
  top: 50px;
}
.myMessage .school {
  font-size: 12px;
  position: absolute;
  top: 85px;
  left: 110px;
}
.arrow {
  position: absolute;
  right: 30px;
  top: 60px;
  width: 30px;
  height: 30px;
}
</style>


