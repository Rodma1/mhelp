<template>
    <div class="wallet">
        <div>金币余额(个)</div>
        <div>{{money}}.00</div>
    </div>
</template>
<script>
export default{
    data(){
        return {

        }
    },
    props:{
        money:{
            type:Number,
            default:0
        }
    }
}
</script>
<style scoped>
.wallet{
    height: 140px;
    margin: 10px;
    background-color: #7bc8f1;
    border-radius: 10px;
}
.wallet div:nth-child(1){
    /* margin-top: 20px; */
    padding-top: 20px;
    text-align: center;
}
.wallet div:nth-child(2){
    padding-top: 10px;
    text-align: center;
    font-size: 40px;
}
</style>
