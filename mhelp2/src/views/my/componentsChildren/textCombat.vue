<template>
    <div class="textCombat" @click="toTextCombat">
        <my-item>
             <div slot="left">
                <img src="@/assets/img/my/文本.png" alt="">
                <span>文本对抗</span>
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
        toTextCombat(){
            this.$router.push("/textCombat")
        }
    }
}
</script>
<style scoped>
.textCombat{
    width: 100%;
}
.textCombat img{
    width: 28px;
    height: 28px;
    padding-left: 20px;
}
.textCombat span{
    padding-left: 10px;
    font-size: 18px;
    /* line-height: 90px; */
}
.textCombat .right img{
    width: 15px;
    height: 15px;
}
</style>
