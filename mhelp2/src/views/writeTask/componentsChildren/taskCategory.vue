<template>
    <ul class="taskCategory">
        <li @click="toPublish">
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li><li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
        <li>
            <img src="@/assets/img/writeTask/餐具.png" alt=""><br>
            <span>食堂打包</span>
        </li>
    </ul>
</template>
<script>
export default{
    data(){
        return{
            
        }
    },
    methods:{
        toPublish(){
            this.$router.push('/publish');
        }
    }
}
    
</script>
<style scoped>
.taskCategory{
    /* width: 100%; */
    display: flex;
    flex-wrap: wrap;
    list-style-type: none;
    padding: 10px ;
    justify-content: center;
}
.taskCategory li{
   width: 100px;
    height: 100px;
    /* background: blue; */
    margin-left: 10px;
    margin-bottom: 10px;
    text-align: center;
    /* border-radius: 50%; */
}
.taskCategory li:nth-child(1){
    margin-left: 0px;
}
.taskCategory li img{
    width: 80px;
    height: 80px;
    border-radius: 50%;
    background-color: rgb(168, 204, 252);
}
</style>
