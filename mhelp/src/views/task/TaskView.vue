<template>
  <el-table
    :data="tasksummary"
    border
    style="width: 50%" :row-class-name="tableRowClassName">
    <el-table-column
      prop="name"

      width="180">
    </el-table-column>
    <el-table-column
      prop="name"

      width="180">
    </el-table-column>
  </el-table>
</template>

<script>
import {viewTask } from '@/api/task.js'
  export  default  {
  data()
  {
    return {
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
  mounted() {
        this.getTask()
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
        viewTask('1456906922047352833').then(res=>{
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


<style scoped>

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
