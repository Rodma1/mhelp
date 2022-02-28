<template>
    <div class="myShop" @click="toshoppingmall">
        <my-item>
            <div slot="left">
                <img src="@/assets/img/my/商城.png" alt="">
                <span>我的商城</span>
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
        toshoppingmall(){
            this.$router.push('/shoppingmall')
        }
    }
}
</script>
<style scoped>
.myShop{
    width: 100%;
    align-items: center;
}
.myShop img{
    width: 30px;
    height: 30px;
    padding-left: 20px;
}
.myShop span{
    padding-left: 10px;
    font-size: 18px;
    /* line-height: 90px; */
}
.myShop .right img{
    width: 15px;
    height: 15px;
}
</style>