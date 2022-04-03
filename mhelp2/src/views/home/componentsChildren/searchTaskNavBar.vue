<template>
  <div class="searchTaskNavBar">
    <div class="center" @click="search">
      <img src="@/assets/img/my/搜索.png" alt="" />
      <div>{{value}}</div>
    </div>
    <div class="right" @click="gohome">
      取消
    </div>
  </div>
</template>
<script>
export default{
    props:{
        value:{
            type:String,
            default:""
        }
    },
    methods:{
        search(){
            this.$router.back();
            console.log(this.value)
            this.$bus.$emit("searchBack",this.value)
        },
        gohome(){
            this.$router.push("/home")
        },

    },
    activated(){
        console.log(11)
    }
}
</script>
<style scoped>
.searchTaskNavBar {
  height: 44px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
}
.right {
  width: 60px;
  text-align: center;
}
.center {
  flex: 1;
  height: 30px;
  margin-left: 10px;
  background: #f6f7f9;
  border-radius: 10px;
  line-height: 30px;
  font-size: 15px;
  display: flex;
  color: black;
}
.center img {
  height: 25px;
  width: 25px;
}
.center div {
  flex: 1;
}
/* .center div input {
  border: 0px;
  outline: 0px;
  width: 100%;
  height: 100%;
  background: #f6f7f9;
  border-radius: 10px;
  color: #252627;
} */
</style>

