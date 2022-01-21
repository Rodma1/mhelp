<template>
  <div class="list">
    <scroll class="contents">
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
      <item></item>
    </scroll>
  </div>
</template>
<script>
import item from "views/taskList/componentsChildren/item.vue"
import scroll from "components/common/scroll/scroll.vue"

export default{
    components:{
        item,
        scroll

    }
}
</script>
<style scoped>
.list {
  position: absolute;
  top: 79px;
  bottom: 0px;
  left: 0px;
  right: 0px;
  padding: 10px 13px;
  /* margin: 10px 10px 10px 0px; */
  /* background: red; */
}
.contents{
    height: 100%;
    overflow: hidden;
}
</style>