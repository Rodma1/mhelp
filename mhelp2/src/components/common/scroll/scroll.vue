<template>
    <div  ref="wrapper" class="wrapper">
        <div class="content">
            <slot></slot>
        </div>
    </div>
</template>
<script>
import BScroll from 'better-scroll'
export default{
    data(){
        return{
            scroll:null
        }
    },
    props:{
        probeType:{
            type:Number,
            default:0
        },
        pullUpLoad: {
            type: Boolean,
            default: false
      }
    },
    mounted(){
        // console.log(this.scroll)
       // 1.创建BScroll实例
        this.scroll= new BScroll(this.$refs.wrapper,{
            click:true,
            probeType:this.probeType,
            pullUpLoad:this.pullUpLoad
        })
        //监听滚动位置
        if(this.probeType===2||this.probeType===3){
            this.scroll.on('scroll',(position)=>{
            // console.log(position)
            this.$emit('scroll',position)
        })
        }
        //监听上拉加载
        if(this.pullUpLoad==true){
            this.scroll.on('pullingUp',()=>{
            // console.log("上拉加载更多！！")
            this.$emit('pullingMore')
        })
        }
        
        // console.log(BScroll);
        // this.scroll.hasVerticalScroll = true
        // console.log(this.scroll)
    },
    methods:{
        scroller(x,y,time=300){
            // console.log(typeof(x));
            this.scroll&&this.scroll.scrollTo(x,y,time);
            // console.log(y);
            // console.log(time);
            // console.log(this.scroll.scrollTo(x,y,time))
        },
        refresh(){
            // console.log('-------');
            this.scroll.refresh();
        },
        finishPullUp(){
            this.scroll&&this.scroll.finishPullUp();
        },
        getScrollY(){
            return this.scroll?this.scroll.y:0
        }

    }
}
</script>
<style scoped>
    /* .content{
        height: 5000px;
    } */
</style>
