<template>
  <div class="searchNavBar">
    <div class="center">
      <img src="@/assets/img/my/搜索.png" alt="" />
      <div>
        <input
          type="text"
          name=""
          id=""
          placeholder="任务类型/任务标签"
          v-model="value"
          @input="judge"
          ref="inputBox"
        />
      </div>
    </div>
    <div class="right" @click="action">
      {{ msg }}
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      value: "",
      msg: "取消",
    };
  },
  mounted() {
    this.$bus.$on('historySearch',(item)=>{
      // console.log(item);
      this.value=item;
      this.msg="搜索"
    })
    //  this.judge()
  },
  methods: {
    judge() {
      if(this.value){
         this.msg = "搜索";
         
      }
      else{
        this.msg="取消"
      }
    },
    action(){
      if(this.msg=="取消"){
        this.$router.back();
      }
      else{
        this.$emit("search",this.value);
        this.value=""
        this.msg="取消"
      }
    }
  },
};
</script>

<style scoped>
.searchNavBar {
  height: 44px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
}
.right {
  width: 60px;
  text-align: center;
}
.center {
  flex: 1;
  height: 30px;
  margin-left: 10px;
  background: #f6f7f9;
  border-radius: 10px;
  line-height: 30px;
  color: #a2a3a5;
  font-size: 15px;
  display: flex;
}
.center img {
  height: 25px;
  width: 25px;
}
.center div {
  flex: 1;
}
.center div input {
  border: 0px;
  outline: 0px;
  width: 100%;
  height: 100%;
  background: #f6f7f9;
  border-radius: 10px;
  color: #252627;
}
</style>