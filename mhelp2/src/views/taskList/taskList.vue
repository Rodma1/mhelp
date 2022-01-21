<template>
    <div class="taskList">
        <task-list-nav></task-list-nav>
        <tab-control></tab-control>
        <list></list>
    </div>
</template>
<script>
import taskListNav from "views/taskList/componentsChildren/taskListNav.vue"
import tabControl from "views/taskList/componentsChildren/tabControl.vue"
import list from "views/taskList/componentsChildren/list.vue"
import {archive} from "network/task.js"

export default{
    components:{
        taskListNav,
        tabControl,
        list
      
    },
    data(){
        return{
            page:{
                pageNumber:1,
                pageSize:10
            },
            archiveList:{
                all:[],
                incomplete:[],
                complete:[]
            }
                
        }
    },
    activated(){
        this.archive()
    },
    methods:{
        archive(){
           archive(this.$store.state.token,2).then((res)=>{
               console.log(res)
                // this.archiveList.incomplete.push(...res.data);
                // this.archiveList.all.push(...res.data)
                // console.log(this.archiveList.incomplete);

            })
        }
    }
}
</script>
<style scoped>
.taskList{
    height: 100vh;
    z-index: 10;
    position: relative;
    background: #f6f6f6;
}
</style>
