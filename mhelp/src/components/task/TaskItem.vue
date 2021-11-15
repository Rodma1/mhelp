<template>
  <el-card class="me-area" :body-style="{ padding: '16px' }">
    <div class="me-task-header">

      <a @click="view(id)" class="me-task-title">{{title}}</a>
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

      <el-tag v-for="t in tags" :key="t.tagName" size="mini" type="success">{{t.tagName}}</el-tag>

      <span class="me-pull-right me-task-count">
	    	<i class="el-icon-time"></i>&nbsp;{{createDate | format}}
	    </span>

    </div>
  </el-card>
</template>

<script>
import { formatTime } from "../../utils/time";

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
    createDate: String
  },
  data() {
    return {}
  },
  methods: {
    view(id) {
      this.$router.push({path: `/view/${id}`})
    }
  }
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

</style>
