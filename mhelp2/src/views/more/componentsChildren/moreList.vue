<template>
    <div class="moreList">
        <div class="item">
            <div>头像</div>
            <div><img src="@/assets/img/example/头像.jpg" alt=""></div>
            <div><img src="@/assets/img/my/右箭头.png" alt=""></div>
            
        </div>
        <div class="item">
            <div>昵称</div>
            <div>吼吼哈嘿</div>
            <div><img src="@/assets/img/my/右箭头.png" alt=""></div>
            
        </div>
        <div class="item">
            <div>所在学校</div>
            <div>运城学院</div>
            <div><img src="@/assets/img/my/右箭头.png" alt=""></div>
            
        </div>
        <div class="item">
            <div>修改密码</div>
            <div>......</div>
            <div><img src="@/assets/img/my/右箭头.png" alt=""></div>
            
        </div>
    </div>
</template>
<script>

</script>
<style scoped>
.moreList{
    background: #f4f4f4;
    position: absolute;
    top: 44px;
    bottom: 0px;
    left: 0px;
    right: 0px;
    /* margin-top: 10px; */
    padding-top: 10px;
}
.item{
    /* margin-top: 10px; */
    height: 50px;
    background: white;
    padding: 0px 8px;
    display: flex;
    border-bottom: 1px solid #e4e4e4;
}
.item div{
    line-height: 50px;
}
.item div:nth-child(1){
    width: 100px;
    /* background: blue; */
}
.item div:nth-child(2){
    flex: 1;
    text-align: right;
    /* background: red; */
}
.item div:nth-child(3){
    width: 60px;
    text-align: right;
    /* background: green; */
}
.item div:nth-child(3) img{
    width: 15px;
    height: 15px;
}
.item div img{
        margin-right: 10px;
        width: 40px;
        height: 40px;
}
</style>