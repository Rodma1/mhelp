<template>
    <div class="message">
        <message-nav-bar></message-nav-bar>
        <router-view></router-view>
    </div>
</template>
<script>
import messageNavBar from "views/message/componentsChildren/messageNavBar.vue";
export default{
    components:{
        messageNavBar
    }
}
</script>
<style scoped>
.navBar {
  /* font-size: px; */
  background: rgb(154, 171, 248);
  color: white;
}
.center{
    display: flex;
}
.center div{
    flex: 1;
}
.center div:nth-child(1){
    text-align: right;
    padding-right: 10px;
}
.center div:nth-child(2){
    text-align: left;
    padding-left: 10px;
}
</style>
