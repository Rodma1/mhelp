<template>
    <div class="homeTaskItem">
        <div class="title">
            <img src="@/assets/img/example/头像.jpg" alt="">
            <div class="center">
                <div class="name">{{item.author}}</div>
                <!-- <div class="tags">{{item.tags[0].tagsName}}</div> -->
                <div class="time">{{item.createDate}}</div>
            </div>
            <div class="remarks">
                <img src="@/assets/img/home/备注.png" alt="">
                <!-- <div v-if="isActive" class="show">dddddddddddddddddddddddddddddddddddddddddddddddddddddd</div> -->
            </div>
            
        </div>
        <div class="content">
            <div class="type">
                {{item.title}}
            </div>
            <div class="description">
                {{item.content}}
            </div>
            <div class="picture">
                <img src="@/assets/img/example/图片1.jpg" alt="">
                <img src="@/assets/img/example/图片2.jpg" alt="">
                <img src="@/assets/img/example/图片2.jpg" alt="">
            </div>
        </div>
        <div class="other">
            <div>
                <img src="@/assets/img/home/赞.png" alt="">
                <span>收藏</span>
            </div>
            <div>
                <img src="@/assets/img/home/价格.png" alt="">
                <span>价格</span>
            </div>
            <div @click="acceptTask">
                <img src="@/assets/img/home/接受.png" alt="">
                <span>接任务</span>
            </div>    
        </div>
    </div>
</template>
<script>
import {acceptTask} from "network/task.js"
export default{
    props:{
        item:{
            type:Object,
            default(){
                return {}
            }
        }
    },
    data(){
        return {
            isActive:true
        }
    },
    methods:{
        acceptTask(){
            acceptTask(this.$store.state.token).then((res)=>{
                console.log(res)
            })
        }
    }
}
</script>

<style scoped>
.homeTaskItem{
    /* height: 150px; */
    background: rgb(255, 255, 255);
    margin: 10px 10px;
    padding: 0px 10px;
    padding-bottom: 10px;
    border-radius: 10px;
}
.title{
    height: 50px;
    /* background: pink; */
    display: flex;
    padding: 10px 0px 0px 0px;
}
.title img{
    width: 40px;
    height: 40px;
}
.title div img{
    width: 30px;
    height: 30px;
}
.title > div:nth-of-type(1){
    flex: 1;
    /* background-color: blue; */
}
.title > div:nth-of-type(2){
    width: 60px;
    /* background-color: purple; */
    text-align: right;
}
.name{
    font-size: 18px;
}
.center{
    margin-left: 10px;
}
.time,.tags{
    color: #757373;
    font-size: 12px;
}
.type{
    height: 30px;
    font-weight: 500;
    line-height: 30px;
}
.description{
    font-weight: 400;
    font-family: 'Courier New', Courier, monospace;
}
.picture{
    display: flex;
    flex-wrap: wrap;
    margin: 0px 10px;
    /* justify-content: center; */
}
.picture img{
    width: 90px;
    height: 90px;
    margin-right: 10px;
    margin-bottom: 10px;
    margin-top: 10px;
}
.other{
    height: 50px;
    /* background-color: yellow; */
    display: flex;
    border: 2px solid #eeeeee;
    /* margin-bottom: 10px; */ 
    border-radius: 10px;
}
.other div{
   
    flex: 1;
    /* background: purple; */
    text-align: center;
    line-height: 50px;
    padding-top: 10px;
}
.other div:nth-child(2){
    border-left: 2px solid #eeeeee;
    border-right: 2px solid #eeeeee;

}
.other img{
    height: 30px;
    width: 30px;
}
.title .remarks img{
    width: 20px;
    height: 20px;
}
.remarks{
    margin-right:10px ;
    margin-top:10px ;
}
.show{
    position: relative;
    height: 200px;
    background: red;
    z-index: 9;
}
</style>