<template>
  <div class="homeSearch">
    <div @click="search">
        <div>搜索任务</div>
        <div>搜索</div>
    </div>
  </div>
</template>
<script>
export default {
    methods:{
        search(){
            console.log(11);
            this.$router.push('/home/homeSearch')
        }
    }
};
</script>


<style scoped>
.homeSearch {
  height: 34px;
  background: rgb(154, 171, 248);
  padding-top: 10px;
}
.homeSearch >div {
  width: 80%;
  height: 23px;
  border-radius:5px;
  background-color: white;
  margin: 0px auto;
  font-size: 13px;
  display: flex;
  line-height: 23px;
}
.homeSearch div:nth-child(1) {
    flex: 1;
    padding-left: 5px;
}
.homeSearch div:nth-child(2){
    width: 50px;
    text-align: center;
    border-left: 1px solid gray;
}
</style>