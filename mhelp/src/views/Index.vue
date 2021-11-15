<template>
<!--  任务首页-->
      <el-main >

        <task-scroll-page></task-scroll-page>

      </el-main>
</template>

<script>
import TaskScrollPage from "@/views/common/TaskScrollPage";
export default {
  name: "Index",
  components:{
    'task-scroll-page':TaskScrollPage
  }
}
</script>

<style scoped>

</style>
