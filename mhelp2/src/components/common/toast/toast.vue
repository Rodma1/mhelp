<template>
    <div class="toast" v-show="logingShow">
        {{message}}
    </div>
</template>
<script>
    export default{
        props:{
            message:{
                type:String,
                default(){
                    return ''
                }
            },
            logingShow:{
                type:Boolean,
                default(){
                    return false
                }
            }
        },
        // data:{
        //     message:'',
        //     isShow:false
        // },
        // methods:{
        //     show(message,duration){
        //         this.message=message;
        //         this.isShow=true;
        //         setTimeout(()=>{
        //             this.isShow=false;
        //             this.message='';
        //         },duration)
        //     }
        // }
    }
</script>
<style scoped>
.toast{
    color: #fff;
    background-color: rgba(0,0,0,0.6);
    padding: 8px 10px;
}
</style>