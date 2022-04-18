<template>
    <div class="publishItem">
       <div class="title">
            <img :src="item.avatar" alt="" @load="itemImageLoad">
            <div class="center">
                <div class="name">{{item.author}}</div>
                <div class="time">{{item.createDate}}</div>
            </div>
            <div class="remarks">
                {{msg}}
            </div>
        </div>
        <div class="content">
            <div class="type">
                {{item.title}}
            </div>
            <div class="description">
                {{item.summary}}
            </div>
            <ul class="picture">
                <li v-for="(i,index) in item.images" :key="index">
                    <img :src="i" alt="" @load="itemImageLoad">
                </li>
            </ul>
        </div>
        <div class="bottom">
            <div class="deltask" v-if="isShow1" @click="delTask">删除任务</div>
            <div class="finishTask" v-if="isShow2" @click="finishTask">完成任务</div>
        </div>
        
    </div>
</template>
<script>
import {finishTask,delTask} from "network/task.js"

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
        return{
            msg:"",
            isShow1:false,
            isShow2:false,

        }
    },
    mounted(){
        this.which()
    },
    methods:{
        which(){
            if(this.item.status==2){
                this.msg="已完成";
                this.isShow1=true;
                this.isShow2=true;
            }
            else if(this.item.status==1){
                this.msg="正在完成"
                this.isShow1=false
                this.isShow2=true
            }
            else{
                this.msg="待接"
                this.isShow1=true;
                this.isShow2=false 
            }
        },
        itemImageLoad(){
            this.$bus.$emit("itemImageLoad");
        },
        finishTask(){
            finishTask(this.$store.state.token,this.item.id).then((res)=>{
                console.log(res)
                this.$toast("完成任务")
                this.$bus.$emit("clear")
            })
        },
        delTask(){
            delTask(this.$store.state.token,this.item.id).then((res)=>{
                console.log(res)
                this.$toast("删除任务成功")
                this.$bus.$emit("clear")
            })
        }
    }
}
</script>
<style scoped>
.publishItem{
    background: rgb(255, 255, 255); 
    margin: 5px 5px;
    margin-bottom: 10px;
    padding: 0px 8px;
    padding-bottom: 10px;
    position: relative;
}
.title{
    height: 50px;
    display: flex;
    padding: 5px 0px 0px 0px;
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
    text-align: center;
    color: #fa5656;
    font-size: 15px;
    font-weight: 600;
    /* text-align: right; */
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
    /* height: 30px; */
    font-weight: 500;
    font-size: 18px;
    /* line-height: 30px; */
}
.description{
    font-weight: 400;
    font-size: 15px;
    /* font-family: 'Courier New', Courier, monospace; */
    /* margin-bottom: px; */
}
.picture{
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    
    /* margin: 0px 10px; */
    /* justify-content: center; */
}
/* 设置为换行，每个元素占1/3或指定宽度，除每行最后一个元素（3n）都设置margin-right并通过预留间隔； */
.picture li{
    width: 32%;
    height: 105px;
    margin-top: 5px;

}
.picture li:not(:nth-child(3n)) {
  margin-right: 2%;
}
/* 对于只有一张图片情况，只需用css选择器判断为一张图片时，改变图片大小即可;
选择器逻辑：元素为 倒数第一个元素  && 第一个元素 时，则可判断只有一个元素：对其样式单独设置覆盖原样式即可 */
.picture li:nth-last-child(1):first-child{
  width: 170px;
  height: 170px;
}

.picture li:nth-last-child(2):first-child , ul li:nth-last-child(2):first-child ~ li{
    width: 48%;
    height: 150px;
}
/* 对于四张图片的情况时，图片需呈 两行两列布局：这里就需对此种情况下的第二张图片添加margin-right实现三列变两列：
选择器逻辑： 元素为 倒数第4个&&  第一个的元素 时， 判断为共有四个元素，
再选择 其后的 同级元素 的第 2n 个后添加margin-right属性； */

.picture li:nth-last-child(4):first-child ~ li:nth-child(2n){
  margin-right: 0%;
}
.picture li:nth-last-child(4):first-child , ul li:nth-last-child(4):first-child ~ li{
  width: 49%;
  height: 150px;
  margin-right: 5px;
}
.picture img{
    /* width: calc(100%/3.9); */
    width: 100%;
    height: 100%;
    /* flex: 1; */
    margin-right: 0.5%;
    margin-bottom: 5px;
    /* margin-top: 10px; */
}
.finishTask{
    width: 100px;
    height: 30px;
    background-color: #1facf8; 
    margin-left: 20px;
    text-align: center;
    line-height: 30px;
    color: white;
    /* border-radius: 8px; */
}
.deltask{
     width: 100px;
    height: 30px;
     margin-left: 20px;
    text-align: center;
    line-height: 30px; 
    color: #1facf8;
    border: 1px solid #1facf8;
}
.bottom{
    margin-top: 10px; 
    display: flex;
    justify-content: flex-end;
}
</style>