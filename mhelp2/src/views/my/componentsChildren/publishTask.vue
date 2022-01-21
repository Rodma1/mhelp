<template>
    <div class="publishTask">
        <ul class="title">
            <li>我发布的任务</li>
            <li>全部</li>
        </ul>
        <ul class="content">
            <li>
                <img src="@/assets/img/my/已完成.png" alt="">
                <div>已被完成</div>
            </li>
            <li>
                <img src="@/assets/img/my/未完成.png" alt="">
                <div>未被完成</div>
            </li>
            <li>
                <img src="@/assets/img/my/收藏.png" alt="">
                <div>被收藏</div>
            </li>
            <li>
                <img src="@/assets/img/my/签到.png" alt="">
                <div>签到</div>
            </li>
        </ul>
    </div>
</template>
<style scoped>
.myTask{
    width: 95%;
    height: 100px;
    background-color: #ffffff;
    border-radius: 8px;
}
.myTask ul{
    list-style-type: none;
    display: flex;
    align-items: center;
    text-align: center;
}
.myTask ul li{
    flex: 1;
    /* height: 60px; */
}
.myTask img{
    width: 24px;
    height: 24px;
}
.title{
    height: 40px;
}
.title li:nth-child(1){
    text-align: left;
    padding-left: 10px;
    font-weight: 600;
    font-size: 18px;
}
.title li:nth-child(2){
    text-align: right;
    padding-right: 10px;
    font-size: 16px;
}
.content{
    height: 60px;
}
</style>