<template>
    <div class="signInNav">
        <nav-bar>
            <div slot="left" @click="goback">
                <img src="@/assets/img/example/返回.png" alt="">
            </div>
            <div slot="center">
                签到
            </div>
        </nav-bar>
    </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue"
export default{
    components:{
        navBar
    },
    methods:{
        goback(){
            this.$router.back();
        }
    }
}
</script>
<style scoped>
.signInNav{
   background: #3dbafddc;
   color: white;
   line-height: 44px;
}
.signInNav img{
    width: 30px;
    height: 30px;
    padding-top: 10px;
}
</style>