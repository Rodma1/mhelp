<template>
    <div class="myWallet" @click="topakege">
        <my-item>
            <div slot="left">
                <img src="@/assets/img/my/钱包.png" alt="">
                <span>我的钱包</span>
            </div>
            <div slot="right" class="right">
                <img src="@/assets/img/my/右箭头.png" alt="">
            </div>
        </my-item>
    </div>
</template>
<script>
import myItem from "components/content/myItem/myItem.vue"
export default{
    components:{
        myItem
    },
    methods:{
        topakege(){
            this.$router.push('./pakege')
        }
    }
}
</script>
<style scoped>
.myWallet{
    width: 100%;
    
}
.myWallet img{
    width: 28px;
    height: 28px;
    padding-left: 20px;
}
.myWallet span{
    padding-left: 10px;
    font-size: 18px;
    /* line-height: 90px; */
}
.myWallet .right img{
    width: 15px;
    height: 15px;
}
</style>
