<template>
  <el-dialog :visible.sync="dialogVisible">
  <el-table
    :data="tasksummary"
    style="width: 100%"
    :row-class-name="tableRowClassName">

    <el-table-column
      prop="name">
    </el-table-column>
    <el-table-column
      prop="task">
    </el-table-column>


  </el-table>
  </el-dialog>
</template>

<script>
import {viewTask } from '@/api/task.js'
  export  default  {
    name: 'TaskView',
    created() {
      this.getTask()
    },
    watch: {
      '$route': 'getTask'
    },
    data()
    {
      return {
        dialogVisible:true,
        tasksummary:[
          {

            name: '任务标题',
            task: ''
          }, {
            name: '详细描述',
            task: ''
          }, {
            name: '发布者',
            task: ''
          }, {
            name: '奖励',
            task: ''
          }
          , {
            name: '发布时间',
            task: ''
          }
        ]
      }
    },

    methods: {
      tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';

      },
      getTask(){
        let that=this
        viewTask(that.$route.params.id).then(res=>{
          console.log(res)
          this.tasksummary[0].task=res.data.title
          this.tasksummary[1].task=res.data.summary
          this.tasksummary[2].task=res.data.author
          this.tasksummary[3].task=''
          this.tasksummary[4].task=res.data.createDate
          // this.tableData[] = res.data.author;
        })
      }
    }
  }
</script>


<style>

  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
