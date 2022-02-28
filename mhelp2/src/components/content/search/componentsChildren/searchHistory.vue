<template>
  <div class="searchHistory" v-if="isShowHis">
    <div class="title">
      <span>历史搜索</span>
      <img src="@/assets/img/message/删除.png" alt="" @click="delHistory"/>
    </div>
    <ul>
      <li v-for="(item, index) in searchHistoryList" :key="index" @click="goSearch">
        {{ item }}
      </li>
    </ul>
    <div class="del" v-if="isShow">
      <div class="delTip">
        <div class="content">确定删除全部历史记录？</div>
        <div class="btn">
          <div @click="cancle">取消</div>
          <div @click="del">确定</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      searchHistoryList: [
        "取快递",
        "带饭",
        "组队",
        "dddddddddddddddddddddddddddddddddddddddddddddddd",
      ],
      isShow:false,
      isShowHis:false
    };
  },
  props:{
    value:{
      type:String,
      default:""
    }
  },
  mounted(){
    if(this.searchHistoryList){
      this.isShowHis=true;
    }
    else{
      this.isShowHis=false
    }
  },
  methods: {
    delHistory() {
      this.isShow=true;
    },
    cancle(){
      this.isShow=false
    },
    del(){
      this.isShow=false;
      this.searchHistoryList=[],
      this.isShowHis=false
    },
    goSearch(){
      
    }
  },
};
</script>

<style scoped>
.searchHistory {
  max-height: 150px;
  min-height: 50px;
  /* background-color: red; */
  padding: 10px;
}
.title {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 10px;
  position: relative;
}
.title img {
  width: 20px;
  height: 20px;
  position: absolute;
  right: 0px;
  top: 0px;
}
ul {
  display: flex;
  flex-wrap: wrap;
}
li {
  min-width: 50px;
  max-width: 100%;
  height: 25px;
  background: #f7f7f7;
  overflow: hidden;
  margin-right: 10px;
  margin-bottom: 10px;
  text-align: center;
  font-size: 14px;
  line-height: 25px;
}
.del {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 11;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
}
.delTip {
  padding: 10px;
  width: 90%;
  height: 130px;
  background: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.delTip .content {
  height: 80px;
  /* background: red; */
  line-height: 80px;
}
.delTip .btn {
  height: 30px;
  display: flex;
}
.delTip .btn div {
  padding: 10px;
  text-align: right;
  color: rgb(95, 95, 211);
}
.delTip .btn div:nth-child(1) {
  flex: 2;
}
.delTip .btn div:nth-child(2) {
  flex: 1;
}
</style>
