<template>
    <div id="home">
      <el-container>
<!--        定义容器头部-->
           <base-header></base-header>
<!--        定义侧边栏容器-->
        <base-aside></base-aside>
      </el-container>
    </div>
</template>

<script>
//导入组件
import BaseHeader from "@/views/BaseHeader";
import BaseAside from "./views/BaseAside";
export default {
  name: "Home",
//  定义组件
  components:{
    'base-header':BaseHeader,
    'base-aside':BaseAside
  }
}
</script>

<style scoped>

</style>
