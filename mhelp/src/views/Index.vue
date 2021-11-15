<template>
<!--  任务首页-->
      <el-main class="me-articles">

        <article-scroll-page></article-scroll-page>

      </el-main>
</template>

<script>
import TaskScrollPage from "@/views/common/TaskScrollPage";
export default {
  name: "Index",
  components:{
    'article-scroll-page':TaskScrollPage
  }
}
</script>

<style scoped>

</style>
