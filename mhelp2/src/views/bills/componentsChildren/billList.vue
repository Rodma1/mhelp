<template>
  <div class="billList">
    <scroll class="contents">
      <bills-item v-for="(item,index) in billlist" :key="index" :item="item"></bills-item>
    </scroll>
  </div>
</template>
<script>
import billsItem from "views/bills/componentsChildren/billsItem.vue";
import scroll from "components/common/scroll/scroll.vue";
export default {
  components: {
    billsItem,
    scroll,
  },
  data(){
    return{
      billlist:[
        {
          name:"哎咿呀",
          money:3,
          id:"0110000000000000",
          time:"2022年1月12日"
        },
        {
          name:"吼吼哈嘿",
          money:2,
          id:"0110000002000401",
          time:"2022年3月15日"
        },
        {
          name:"嘻嘻嘻",
          money:2,
          id:"0110000002000401",
          time:"2022年3月21日"
        }
      ]
    }
  },
  mounted(){
    this.$bus.$on("addbill",(i)=>{
      var params={
        name:"hahaha",
          money:i,
          id:"0110000002000523",
          time:"2022年4月14日"
      }
      this.billlist.push(params)
    })
  }
};
</script>
<style scoped>
.contents{
    height: 100%;
    overflow: hidden;
}
</style>