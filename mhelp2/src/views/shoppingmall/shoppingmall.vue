<template>
    <div class="shoppingmall">
        <nav-bar>
            <div slot="left" @click="goback">
                <img src="@/assets/img/example/返回.png" alt="">
            </div>
            <div slot="center">商城</div>
        </nav-bar>
        <scroll class="contents" :probeType="3" ref="scroll">
            <swiper></swiper>
            <exchange-Goods></exchange-Goods>
        </scroll>
        
    </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue"
import swiper from "views/shoppingmall/componentsChildren/swiper.vue"
import exchangeGoods from "views/shoppingmall/componentsChildren/exchangeGoods.vue"
import scroll from "components/common/scroll/scroll.vue"
import { itemListenerMixin } from "mixins/mixins.js";
export default{
    components:{
        navBar,
        swiper,
        exchangeGoods,
        scroll
    },
    mixins: [itemListenerMixin],
    methods:{
        goback(){
            this.$router.back()
        }
    }
}
</script>

<style scoped>
.shoppingmall{
    height: 100vh;
    position: relative;
    z-index: 10;
    background-color: white;
    /* background: black; */
}
.contents{
    position: absolute;
    top: 44px;
    bottom: 0px;
    left: 0px;
    right: 0px;
    overflow: hidden;
}
img{
    width: 30px;
    height: 30px;
}
</style>