<template>
    <div class="detail">
        <detail-nav-bar></detail-nav-bar>
    </div>
</template>
<script>
import detailNavBar from "views/detail/componentsChildren/detailNavBar.vue"

export default{
    components:{
        detailNavBar
    },
}
</script>
<style scoped>
.detail{
    height: 100vh;
    background: white;
    position: relative;
    z-index: 10;
}
</style>