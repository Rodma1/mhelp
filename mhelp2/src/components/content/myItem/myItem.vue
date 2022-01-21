<template>
    <div class="myItem">
        <div class="left"><slot name="left"></slot></div>
        <div class="right"><slot name="right"></slot></div>
    </div>
</template>
<style scoped>
.myItem{
    width: 100%;
    display: flex;
    height: 80px;
    background-color: #ffffff;
    align-items: center;
}
.right{
    width: 80px;
}
.left{
    flex: 1;
}
</style>