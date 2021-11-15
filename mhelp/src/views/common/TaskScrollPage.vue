<template>

  <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
    <task-item v-for="a in tasks" :key="a.id" v-bind="a"></task-item>
  </scroll-page>
</template>

<script>
import TaskItem from '../../components/task/TaskItem'
import ScrollPage from '@/components/scrollpage'
import {getTasks} from '@/api/task'

export default {
  name: "TaskScrollPage",
  props: {
    offset: {
      type: Number,
      default: 100
    },
    page: {
      type: Object,
      default() {
        return {}
      }
    },
    query: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  watch: {
    'query': {
      handler() {
        this.noData = false
        this.tasks = []
        this.innerPage.pageNumber = 1
        this.getTasks()
      },
      deep: true
    },
    'page': {
      handler() {
        this.noData = false
        this.tasks = []
        this.innerPage = this.page
        this.getTasks()
      },
      deep: true
    }
  },
  created() {
    this.getTasks()
  },
  data() {
    return {
      loading: false,
      noData: false,
      innerPage: {
        pageSize: 5,
        pageNumber: 1,
        name: 'a.createDate',
        sort: 'desc'
      },
      tasks: []
    }
  },
  methods: {
    load() {
      this.getTasks()
    },
    view(id) {
      this.$router.push({path: `/view/${id}`})
    },
    getTasks() {
      let that = this
      that.loading = true

      getTasks(that.query, that.innerPage).then(data => {
        // console.log(data)
        let newTasks = data.data
        if (newTasks && newTasks.length > 0) {
          that.innerPage.pageNumber += 1
          that.tasks = that.tasks.concat(newTasks)
        } else {
          that.noData = true
        }

      }).catch(error => {
        if (error !== 'error') {
          that.$message({type: 'error', message: '任务加载失败!', showClose: true})
        }
      }).finally(() => {
        that.loading = false
      })

    }
  },
  components: {
    'task-item': TaskItem,
    'scroll-page': ScrollPage
  }

}
</script>

<style scoped>
.el-card {
  border-radius: 0;
}

.el-card:not(:first-child) {
  margin-top: 10px;

}
</style>
