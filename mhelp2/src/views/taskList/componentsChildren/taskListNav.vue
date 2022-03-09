<template>
  <div class="taskListNav">
    <div class="left" @click="goBack">
      <img src="@/assets/img/taskList/返回.png" alt="" />
    </div>
    <div class="center" @click="goTaskListSearch">
      <img src="@/assets/img/my/搜索.png" alt="" />
      <div>搜索我接的任务</div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props:{
    index:{
      type:Number,
      default(){
        return 0
      }
    }
  },
  methods: {
    goBack() {
      this.$router.push('/my');
      this.$emit("goback")
    },
    goTaskListSearch(){
      switch (this.index) {
        case 0:
          this.$router.push('/taskList/0/taskListSearch');
          break;
        case 1:
          this.$router.push('/taskList/1/taskListSearch');
          break;
        case 2:
          this.$router.push('/taskList/2/taskListSearch');
          break;
        case 3:
          this.$router.push('/taskList/3/taskListSearch');
          break;
        default:
          break;
      }
       
    }
  },
};
</script>
<style scoped>
.taskListNav {
  height: 44px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
}
.left {
  width: 60px;
  text-align: center;
}
.left img {
  width: 30px;
  height: 30px;
  /* padding-top: 7px; */
}
.center {
  flex: 1;
  height: 30px;
  margin-right: 20px;
  background: #f6f7f9;
  border-radius: 10px;
  line-height: 30px;
  color: #a2a3a5;
  font-size:15px;
  display: flex;
}
.center img{
    height: 25px;
    width: 25px;
}
.center div{
    flex: 1;
}
</style>