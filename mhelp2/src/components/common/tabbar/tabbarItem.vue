<template>
    <div class="item-tapBar" @click="itemClick">
        <div v-if="!isActive">
            <slot name="item-icon"></slot>
        </div>
        <div v-else>
            <slot name="item-icon-active" ></slot>
        </div>
        <div :style="activeStyle">
            <slot name="item-text" ></slot>
        </div>
    </div>
</template>
<script>
    export default{
        name:'TabBarItem',
         props:{
            path:String,
            activeColor:{
                type:String,
                default:'red'
            }
        },
        data(){
            return {
                // isActive:false
            }
        },
        computed:{
            isActive(){
                // console.log(this.$route.path.indexOf(this.path))
                // console.log(this.$route.path.indexOf(this.path)!==-1)
                // console.log(0!==1)
                return this.$route.path.indexOf(this.path)!==-1

            },
            activeStyle(){
                return this.isActive ? {color:this.activeColor}:{}
            }
        },
        methods:{
            itemClick(){
                // console.log('itemclick');
                this.$router.push(this.path);
                // console.log(this.activeColor)
                // console.log(this.$route.components)
            }
        }
    }
</script>
<style scoped>
 .item-tapBar{
    flex: 1;
    text-align: center;
    height: 49px;
    font-size: 14px;
  }
  .item-tapBar img{
      width: 24px;
      height: 24px;
      margin-top: 3px;
      vertical-align: middle;
  }
 
</style>
