<template>
    <div class="myBills" @click="toBills">
        <my-item>
            <div slot="left">
                <img src="@/assets/img/my/账单.png" alt="">
                <span>我的账单</span>
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
        toBills(){
            this.$router.push('./bills')
        }
    }
}
</script>
<style scoped>
.myBills{
    width: 100%;
    align-items: center;
}
.myBills img{
    width: 30px;
    height: 30px;
    padding-left: 20px;
}
.myBills span{
    padding-left: 10px;
    font-size: 18px;
    /* line-height: 90px; */
}
.myBills .right img{
    width: 15px;
    height: 15px;
}
</style>
