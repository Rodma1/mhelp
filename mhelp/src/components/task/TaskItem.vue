<template>
  <el-card class="me-area" :body-style="{ padding: '16px' }">
    <div class="me-task-header">
      <a @click="dialogVisible=true" class="me-task-title">{{title}}</a>
      <el-tag >{{Status()}}</el-tag>

        <el-button v-if="weight > 0" class="me-task-icon" type="text">置顶</el-button>
      <span class="me-pull-right me-task-count">
	    	<i class="me-icon-comment"></i>&nbsp;{{commentCounts}}
	    </span>
      <span class="me-pull-right me-task-count">
	    	<i class="el-icon-view"></i>&nbsp;{{viewCounts}}
	    </span>
    </div>

    <div class="me-artile-description">
      {{summary}}
    </div>

    <div class="me-task-footer">
	  	<span class="me-task-author">
	    	<i class="me-icon-author"></i>&nbsp;{{author}}
	    </span>
      <el-button type="primary" v-if="update" @click="write">修改</el-button>
      <el-tag v-for="t in tags" :key="t.tagName" size="mini" type="success">{{t.tagName}}</el-tag>

      <span class="me-pull-right me-task-count">
	    	<i class="el-icon-time"></i>&nbsp;{{createDate | format}}
	    </span>

    </div>

    <el-dialog :visible.sync="dialogVisible">
      <el-table
        :data="tasksummary"
        style="width: 100%"  :row-class-name="tableRowClassName">

        <el-table-column
          prop="name">
        </el-table-column>
        <el-table-column
          prop="task">
        </el-table-column>

      </el-table>
      <el-button type="primary" round @click="postAcceptTask">接受任务</el-button>
    </el-dialog>
  </el-card>


</template>

<script>
import { formatTime } from "../../utils/time";
import {acceptTask} from "../../api/task";

export default {
  name: 'TaskItem',
  props: {
    id: String,
    weight: Number,
    title: String,
    commentCounts: Number,
    viewCounts: Number,
    summary: String,
    author: String,
    tags: Array,
    createDate: String,
    //任务状态
    status:Number,
    update:false,
  },

  data() {
    return {

      dialogVisible:false,
      tasksummary:[
        {

          name: '任务标题',
          task: this.title
        }, {
          name: '详细描述',
          task: this.summary
        }, {
          name: '发布者',
          task: this.author
        }, {
          name: '奖励',
          task: ''
        }
        , {
          name: '发布时间',
          task: this.createDate
        }
      ]
    }
  },
  methods: {
    view(id) {
      this.$router.push({name:' ',params:{id:id}})
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';

    },
    //任务状态
    Status(){
      if (this.status===0){
        return '未解决'
      }
      else if (this.status===1){
        return '进行中'
      }
      else{
        return '已完成'
      }
    },
  //  接受任务
    postAcceptTask(){
      let that = this
      if (this.$store.state.token==null){
        that.$message({message:'请先登录哦'})
      }
      acceptTask(this.id,this.$store.state.token).then(data => {
        that.$message({type: 'success', message: '任务接受成功', showClose: true})
      }).catch(error => {
        if (error !== 'error') {
          that.$message({type: 'error', message: '任务接受失败', showClose: true})
        }
      })
    },
  //  修改用户，跳转到发布连接
    write() {
      this.$router.push({name:'publish',params:{update:"true",id:this.id}});
    },
  },
}
</script>

<style scoped>

.me-task-header {
  /*padding: 10px 18px;*/
  padding-bottom: 10px;
}

.me-task-title {
  font-weight: 600;
}

.me-task-icon {
  padding: 3px 8px;
}

.me-task-count {
  color: #a6a6a6;
  padding-left: 14px;
  font-size: 13px;
}

.me-pull-right {
  float: right;
}

.me-artile-description {
  font-size: 13px;
  line-height: 24px;
  margin-bottom: 10px;
}

.me-task-author {
  color: #a6a6a6;
  padding-right: 18px;
  font-size: 13px;
}

.el-tag {
  margin-left: 6px;
}
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

</style>
