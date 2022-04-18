<template>
    <div class="writeTaskView">
        <task-category class="taskCategory"></task-category>
    </div>
</template>
<script>
import taskCategory from 'views/writeTask/componentsChildren/taskCategory.vue'
export default{
     components:{
        taskCategory

    }
}
</script>

<style scoped>

</style>