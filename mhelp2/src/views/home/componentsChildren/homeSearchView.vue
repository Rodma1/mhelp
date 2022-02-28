<template>
    <div class="homeSearchView">
        <search></search>
    </div>
</template>
<script>

import search from "components/content/search/search.vue"
export default{
    components:{
        search
    }
}
</script>

<style scoped>
.homeSearchView{
    background: white;
    height: 100vh;
}
</style>