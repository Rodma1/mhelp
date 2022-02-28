<template>
    <div class="taskListSearch">
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
.taskListSearchView{
    background: white;
    height: 100vh;
}
</style>
