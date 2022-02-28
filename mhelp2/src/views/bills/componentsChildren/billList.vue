<template>
  <div class="billList">
    <scroll class="contents">
      <bills-item></bills-item>
      <bills-item></bills-item>
      <bills-item></bills-item>
      <bills-item></bills-item>
    </scroll>
  </div>
</template>
<script>
import billsItem from "views/bills/componentsChildren/billsItem.vue";
import scroll from "components/common/scroll/scroll.vue";

export default {
  components: {
    billsItem,
    scroll,
  },
};
</script>
<style scoped>
.contents{
    height: 100%;
    overflow: hidden;
}
</style>