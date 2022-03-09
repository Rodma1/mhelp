<template>
    <div class="noticeItem">
        <div class="contents">
            你接受了吼吼哈嘿发布的任务
        </div>
        <div class="times">
            2022年2月3日
        </div>
        <div class="tip"></div>
    </div> 
</template>
<script>

</script>
<style scoped>
    .noticeItem{
        padding:0px 10px;
        height: 50px;
        /* background: pink; */
        line-height: 50px;
        border-bottom: 1px solid #dfdfdf;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }
    .contents{
        flex: 3;
        font-family: 'Arical';
        font-size: 17px;
        font-weight: 500;
    }
    .times{
        flex: 1;
        font-size: 8px;
        color: #b1afaf;
    }
    .tip{
        width: 10px;
        height: 10px;
        background-color: red;
        border-radius: 50%;
        position: absolute;
        right: 10px;
        bottom: 30px;
    }
</style>
