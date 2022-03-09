<template>
    <div class="messageNavBar">
        <nav-bar class="navBar">
            <div slot="center" class="center">
                <div :class="{active:isActive}" @click="change1">通知</div>
                <div :class="{active:!isActive}" @click="change2">消息</div>
            </div>
        </nav-bar>
    </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
export default{
    components:{
        navBar
    },
    data(){
        return{
            isActive:false
        }
    },
    activated(){
        this.change2()
    },
    methods:{
        change1(){
            this.isActive=true;
            this.$router.push('/message/notice')
        },
        change2(){
            this.isActive=false;
            this.$router.push('/message/information')
        }
    }
}
</script>
<style scoped>
.navBar {
  /* font-size: px; */
  background: rgb(154, 171, 248);
  color: white;
  font-size: 14px;
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
.navBar .active{
   font-size: 19px;
   font-weight: 600;
}
</style>