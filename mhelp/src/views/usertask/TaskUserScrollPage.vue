<template>

  <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
    <task-item v-for="a in tasks" :key="a.id" v-bind="a" update="true"></task-item>
  </scroll-page>
</template>

<script>
import TaskItem from '../../components/task/TaskItem'
import ScrollPage from '@/components/scrollpage'
import {getUserTasks} from '@/api/task'

export default {
  name: "TaskUserScrollPage",
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
        this.getUserTasks()
      },
      deep: true
    },
    'page': {
      handler() {
        this.noData = false
        this.tasks = []
        this.innerPage = this.page
        this.getUserTasks()
      },
      deep: true
    }
  },
  created() {
    this.getUserTasks()
  },
  data() {
    return {
      loading: false,
      noData: false,
      innerPage: {
        pageSize: 2,
        pageNumber: 1,
        name: 'a.createDate',
        sort: 'desc'
      },
      tasks: []
    }
  },
  methods: {
    load() {
      this.getUserTasks()
    },
    view(id) {
      this.$router.push({path: `/view/${id}`})
    },
    getUserTasks() {
      let that = this
      that.loading = true

      getUserTasks(that.query,that.innerPage,this.$store.state.token).then(data => {
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
