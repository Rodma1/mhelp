<template>
    <div class="exchangeGoods">
        <div>礼品兑换</div>
        <div class="goods">
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
            <div>
                <div></div>
            </div>
        </div>
    </div>
</template>
<script>

export default{
    components:{
    }
}
</script>

<style scoped>
.exchangeGoods{
    background-color: orangered;
    margin: 10px;
}
.exchangeGoods> div:nth-child(1){
    height: 50px;
    background-color: yellow;
    font-size: 18px;
    font-weight: 500;
    text-align: center;
    line-height: 50px;
}
.goods{
    display: flex;
    flex-wrap: wrap;
    /* padding: 10px; */
}
.goods div{
    height: 200px;
    width: 40%;
    margin: 10px 5%;
    /* margin: 10px; */
    background-color: green;
    border-radius: 8px;
}
.goods >div>div:nth-child(1){
    width: 100%;
    height: 150px;
    background-color: blue;
    margin: 0px;
}
</style>