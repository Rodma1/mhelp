<template>
    <div class="writeTask">
        <write-task-nav></write-task-nav>
        <router-view></router-view>
    </div>
</template>
<script>
import writeTaskNav from 'views/writeTask/componentsChildren/writeTaskNav'


export default{
    components:{
        writeTaskNav,
    }
}
</script>
<style scoped>
/* .writeTask{
    height: 100vh;
    background: rgba(255,0,0,0.1);
} */
.taskCategory{
    margin-top: 20px;
}
</style>