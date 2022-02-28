<template>
    <div class="toast" v-show="show">
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
            show:{
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
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    color: #fff;
    background-color: rgba(0,0,0,0.7);
    padding: 8px 10px;
}
</style>