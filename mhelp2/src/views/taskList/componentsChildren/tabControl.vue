<template>
    <div class="tabControl">
        <ul>
             <li v-for="(item,index) in data" :key="index" :class="{active:currentIndex==index}" @click="change(index)">{{item}}</li>
        </ul>
    </div>
</template>
<script>
export default{
    data(){
        return{
            data:[
                '全部',
                '未完成',
                '已完成',
                '收藏'
            ],
            currentIndex:0
        }
    },
    computed:{
    },
    activated(){

        this.getParams()
    },
    methods:{
        getParams(){
            this.currentIndex=this.$route.params.id
        },
        change(index){
            this.currentIndex=index;
            this.$emit('tabClick',index)
            switch(index){
                case 0:this.$router.push('/taskList/0');
                    break
                case 1:this.$router.push('/taskList/1')
                    break
                case 2:this.$router.push('/taskList/2')
                    break
                case 3:this.$router.push('/taskList/3')
                    break
            }
        }
    }
}
</script>
<style scoped>
.tabControl ul{
    display: flex;
    height: 35px;
    background: white;
    /* justify-content: center; */
}
.tabControl ul li{
    flex: 1;
    line-height: 35px;
    text-align: center;
    font-size: 14px;
    /* font-weight: 550; */
    /* background: red; */
}
.active{
    color: rgb(154, 171, 248);
    font-weight: 600;
}
</style>
