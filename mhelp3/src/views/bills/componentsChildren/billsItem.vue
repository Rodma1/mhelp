<template>
  <div class="billsItem">
    <div class="title">
      <img src="@/assets/img/message/头像.png" alt="" @load="itemImageLoad"/>
      <div>吼吼哈嘿</div>
    </div>
    <div class="middle">
      <div class="money">￥ {{item.money}}金币</div>
      <div class="type">付款金额</div>
    </div>
    <div class="bottom">
      <div class="item">
        <div class="left">交易单号</div>
        <div class="right">{{item.id}}</div>
      </div>
      <div class="item">
        <div class="left">交易时间</div>
        <div class="right">{{item.time}}</div>
      </div>
      <div class="item">
        <div class="left">交易状态</div>
        <div class="right">成功支付</div>
      </div>
    </div>
  </div>
</template>
<script>
export default{
  data(){
    return{
      
    }
  },
  props:{ 
    item:{
      type:Object,
      default(){
        return {}
      }
    }
  },
  methods:{
    itemImageLoad() {
      this.$bus.$emit("itemImageLoad");
    },
  }
}
</script>
<style scoped>
.billsItem {
  height: 200px;
  background-color: white;
  margin: 15px 10px 35px 10px;
  border-radius: 10px;
}
.title {
  height: 50px;
  text-align: center;
  position: relative;
}
.title img {
  position: absolute;
  width: 50px;
  height: 50px;
  top: -25px;
  left: 50%;
  transform: translateX(-50%);
}
.title div {
  font-size: 15px;
  position: absolute;
  top: 25px;
  left: 50%;
  transform: translateX(-50%);
}
.middle {
  height: 60px;
  /* background-color: green; */
  text-align: center;
}
.middle .type {
  font-size: 12px;
  color: #878787;
}
.middle .money {
  font-size: 30px;
  font-weight: 500;
}
.item {
  height: 29px;
  /* background: red; */
  border-bottom: 1px solid #e8e8e8;
  font-size: 13px;
  line-height: 29px;
  margin: 0px 10px;
  display: flex;
}

.item :nth-child(3) {
  border-bottom: 0px;
}
.item .left{
    width: 100px;
    /* background: red; */
    text-align: left;
}
.item .right{
    flex: 1;
    text-align: right;
}
</style>
