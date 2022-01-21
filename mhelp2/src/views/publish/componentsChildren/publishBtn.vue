<template>
    <div class="publishBtn">
        <div @click="publish">发布</div>
    </div>
</template>
<script>
// import {publishTask} from "network/task.js"
export default{
    methods:{
        publish(){
            var date=new Date();
            console.log(date);
            // publishTask(this.params).then((res)=>{
            //     console.log(res)
            // })
            // this.$router.push('/home')
        }
    },
    props:{
        params:{
            type:Object,
            default(){
                return {}
            }
        }
    }
}
</script>

<style scoped>
.publishBtn{
    height: 50px;
    /* background: yellow; */
    margin: 10px;
    position: relative;
}
.publishBtn div{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 200px;
    height: 35px;
    background-color: #1facf8;
    font-size: 20px;
    text-align: center;
    line-height: 35px;
    color: white;
}
</style>