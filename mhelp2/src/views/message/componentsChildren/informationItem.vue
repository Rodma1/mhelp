<template>
    <div class="informationItem" @click="goChat">
        <img src="@/assets/img/message/头像.png" alt="">
        <div class="content">
            <div class="netName">吼吼哈嘿</div>
            <div class="newMessage">今天下午帮我开个会</div>
        </div>
        <div class="time">
            <div class="timeTime">16:42</div>
            <div class="new">1</div>
        </div>
    </div>
</template>
<script>
export default{
    methods:{
        goChat(){
            this.$router.push('/chat')
        }
    }
}
</script>

<style scoped>
.informationItem{
    height: 80px;
    /* background-color: pink; */
    padding: 10px;
    display: flex;
    align-items: center;
    border-bottom: 1px solid rgb(206, 204, 204);
}
.informationItem img{
    width: 60px;
    height: 60px;
}
.content{
    flex: 1;
    height: 55px;
    padding-left: 10px;
}
.netName{
    font-size: 22px;
}
.newMessage{
    padding-top: 10px;
    font-size: 12px;
}
.time{
    width: 50px;
    height: 55px;

}
.timeTime{
    /* padding-bottom: 10px; */
    text-align: center;
}
.new{
    width: 15px;
    height: 15px;
    background-color: red;
    color: white;
    border-radius: 50%;
    text-align: center;
    font-size: 12px;
    margin-top: 20px;
    margin-left: 20px;
}
</style>