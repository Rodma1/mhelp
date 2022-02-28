<template>
    <div class="pakege">
        <nav-bar>
            <div slot="left" @click="goback" class="left"><img src="@/assets/img/example/返回.png" alt=""></div>
            <div slot="center">我的钱包</div>
        </nav-bar>
        <wallet></wallet>
        <get-gold-coin></get-gold-coin>
        <pakege-rules></pakege-rules>
    </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue" 
import wallet from "views/pakege/componentsChildren/wallet.vue"
import getGoldCoin from "views/pakege/componentsChildren/getGoldCoin.vue"
import pakegeRules from "views/pakege/componentsChildren/pakegeRules.vue"

export default{
    components:{
        navBar,
        wallet,
        getGoldCoin,
        pakegeRules

    },
    methods:{
        goback(){
            this.$router.back()
        }
    }
}
</script>

<style scoped>
.pakege{
    height: 100vh;
    position: relative;
    z-index: 10;
    background: white;
}
img{
    padding-top: 10px;
    width: 30px;
    height: 30px;
}
</style>