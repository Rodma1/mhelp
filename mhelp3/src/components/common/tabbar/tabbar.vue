<template>
    <div id="TapBar">
      <slot></slot>
    </div>
</template> 
<script>
  export default{
    name: 'TabBar'
  }
</script>
<style scoped>
  /* @import './assets/css/base.css'; */
  #TapBar{
    display: flex;
    background: #f6f6f6f6;

    position: fixed;
    bottom: 0px;
    left: 0px;
    right: 0px;
    box-shadow: 0px -1px 1px rgba(100, 100, 100, 0.1);
  }
</style>
