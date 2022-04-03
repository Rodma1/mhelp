<template>
    <div class="payment">
         <nav-bar>
            <div slot="left" @click="goback" class="left"><img src="@/assets/img/example/返回.png" alt=""></div>
            <div slot="center">支付</div>
        </nav-bar>
        <div class="order">
            <div>
                ￥20.00
            </div>
            <div>
                充值20元,获取50枚金币
            </div>
        </div>
        <div class="payMethods"></div>

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
.payment{
    height: 100%;
    padding: 10px;
}
img{
    padding-top: 10px;
    width: 30px;
    height: 30px;
}
.order{
    height: 150px;
    background-color: red;
}
.payMethods{
    height: 200px;
    background-color: green;
    margin-top: 20px;
}
</style>
