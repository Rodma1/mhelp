<template>
    <div class="getGoldCoin">
        <div>获取金币 </div>
        <div class="buy">
            <div>
                
            </div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</template>
<script>

</script>

<style scoped>
.getGoldCoin{
    margin: 10px;
    /* height: 300px; */
    background: red;
}
.getGoldCoin >div:nth-child(1){
    height: 60px;
    background: yellow;
    line-height: 60px;
    font-size: 18px;
    font-weight: 600;
}
.buy{
    display: flex;
    flex-wrap: wrap;
    /* padding: 10px; */
}
.buy div{ 
    position: relative;
    width: 47.5%;
    height: 100px;
    background-color: purple;
    border-radius: 15px;
    margin-top: 10px;
}
.buy div:nth-child(2),.buy div:nth-child(4),.buy div:nth-child(6){
    margin-left: 5%;
    
}
</style>
