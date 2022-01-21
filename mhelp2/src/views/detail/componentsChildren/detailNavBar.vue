<template>
    <div class="detailNavBar">
        <nav-bar>
            <div slot="left" class="left" @click="goback">
                <img src="@/assets/img/example/返回.png" alt="">
            </div>
            <div slot="center">
                任务详情
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
            this.$router.back()
        }
    }
}
</script>
<style scoped>
.left img {
    width: 30px;
    height: 30px;
}
</style>
