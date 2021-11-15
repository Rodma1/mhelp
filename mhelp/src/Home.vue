<template>
    <div id="home">
      <el-container>
<!--        定义容器头部-->
        <base-header></base-header>
<!--        定义侧边栏容器-->

        <div class="box">
          <div class="aside">
            <base-aside></base-aside>
          </div>
          <div class="main">
            <!-- 容器 -->
            <router-view class="me-container"/>

          </div>
        </div>
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
    'base-footer':BaseFooter,
  },

}
</script>

<style scoped>
  .box{
    height: 100%;
    width: 100%;
    background-color: orange;
    display: flex;
  }
  .box .aside{
    width: 300px;
  }
  .main {
    flex: 1;
  }

  .me-container{
    margin: 100px auto 140px;
  }
</style>
