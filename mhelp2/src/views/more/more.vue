<template>
    <div class="more">
       <more-nav-bar></more-nav-bar>
       <more-list></more-list>
    </div>
</template>
<script>
import moreNavBar from "views/more/componentsChildren/moreNavBar.vue"
import moreList from "views/more/componentsChildren/moreList.vue"

export default{
    components:{
        moreNavBar,
        moreList
    }
}
</script>
<style scoped>
.more{
    height: 100vh;
    position: relative;
    z-index: 10;
    background-color: white;
}

</style>