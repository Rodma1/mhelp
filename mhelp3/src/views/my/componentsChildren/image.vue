<template>
    <div class="image" @click="goImageView">
        <my-item>
            <div slot="left">
                <img src="@/assets/img/my/wordOut.png" alt="">
                <span>图片文字识别</span>
            </div>
            <div slot="right" class="right">
                <img src="@/assets/img/my/rightHead.png" alt="">
            </div>
        </my-item>
    </div>
</template>
<script>
import myItem from "components/content/myItem/myItem.vue"
export default {
     components:{
        myItem
    },
     methods:{
        toImageView(){
            this.$router.push('/shoppingmall')
        },
        goImageView(){
            this.$router.push("/imageView")
        }
    }
}
</script>
<style scoped>
    .image{
    width: 100%;
    align-items: center;
}
.image img{
    width: 30px;
    height: 30px;
    padding-left: 20px;
}
.image span{
    padding-left: 10px;
    font-size: 18px;
    /* line-height: 90px; */
}
.image .right img{
    width: 15px;
    height: 15px;
}
</style>