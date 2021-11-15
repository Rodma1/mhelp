<template>
<!--用户任务-->
  <el-main>

    <task-user-scrollpage></task-user-scrollpage>
  </el-main>

</template>

<script>
import TaskUserScrollPage from "@/views/usertask/TaskUserScrollPage";

export default {
  name: "IndexUserTask",
  components:{
    'task-user-scrollpage':TaskUserScrollPage
  }

}
</script>

<style scoped>

</style>
