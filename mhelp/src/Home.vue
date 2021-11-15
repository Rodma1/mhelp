<template>
    <div id="home">
      <el-container>
<!--        定义容器头部-->
           <base-header></base-header>
<!--        定义侧边栏容器-->
        <base-aside></base-aside>
<!--        定义容器底部-->
        <base-footer></base-footer>
      </el-container>
    </div>
</template>

<script>
//导入组件
import BaseHeader from "@/views/BaseHeader";
import BaseAside from "./views/BaseAside";
import BaseFooter from "./components/BaseFooter";
export default {
  name: "Home",
//  定义组件
  components:{
    'base-header':BaseHeader,
    'base-aside':BaseAside,
    'base-footer':BaseFooter
  }
}
</script>

<style scoped>

</style>
