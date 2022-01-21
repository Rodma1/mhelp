<template>
  <div class="homeNavBar">
    <nav-bar >
      <div slot="left" class="left" @click="toSchool">
        <img src="@/assets/img/navBar/定位.png" alt="" />
        <div>运城学院</div>
      </div>
      <div slot="center" class="center" @click="isShow">
        <span>全部</span>
        <img src="@/assets/img/navBar/下拉三角.png" alt="" :class="{rotateImg:isActive}"/>
      </div>
      <div slot="right" class="right" @click="toSignIN">
        <img src="@/assets/img/navBar/签到.png" alt="" />
        <div>签到</div>
      </div>
    </nav-bar>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
export default {
  components: {
    navBar,
    
  },
  data(){
      return{
          isActive:false
      }
  },
  methods:{
      isShow(){
          this.$emit('isShowClick');
        this.isActive=!this.isActive
      },
      toSchool(){
        this.$router.push('/school');
      },
      toSignIN(){
        this.$router.push('/signIn');
      }
  }
};
</script>
<style scoped>
.homeNavBar {
  /* font-size: px; */
  background: rgb(154, 171, 248);
  color: white;
  height: 44px;
}
.homeNavBar img {
  height: 30px;
  width: 30px;
}
.rotateImg{
    transform: rotate(180deg);
}
.left，.right {
  height: 44px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.left div,
.right div {
  flex: 1;
  font-size: 8px;
  line-height: 9px;
}
.center {
  position: relative;
}
.center img {
  width: 12px;
  height: 12px;
  position: absolute;
  top: 15px;
  /* padding-top: 20px; */
}
</style>