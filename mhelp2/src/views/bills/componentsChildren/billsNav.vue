<template>
    <div class="billsNav">
        <bill-nav-bar>
            <div slot="left" @click="goBack">
                <img src="@/assets/img/example/返回.png" alt="">
            </div>
            <div slot="center">
                我的账单
            </div>
        </bill-nav-bar>
    </div>
</template>
<script>
import BillNavBar from "components/common/navbar/navbar.vue"
export default{
    components:{
        BillNavBar
    },
    methods:{
        goBack(){
            this.$router.back()
        }
    }
}
</script>
<style scoped>
.billsNav{
    /* background-color: white; */
}
.left img{
    width: 30px;
    height: 30px;
    margin-top: 8px;
}
</style>
