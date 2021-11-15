<template>
<!--任务归档-->
  <div  :data-title="title">
    <el-main>
    <el-container>

      <el-aside class="me-area">
        <ul class="me-month-list">
          <li v-for="a in archives" :key="a.year + a.month" class="me-month-item">
            <el-badge :value="a.count">
              <el-button @click="changeArchive(a.year, a.month)" size="small">{{a.year +'年' + a.month + '月'}}
              </el-button>
            </el-badge>
          </li>
        </ul>

      </el-aside>


      <el-main class="me-tasks">
        <div class="me-month-title">{{currentArchive}}</div>

        <task-scroll-page v-bind="task"></task-scroll-page>

      </el-main>
    </el-container>
    </el-main>
  </div>
</template>

<script>
  import TaskScrollPage from '@/views/common/TaskScrollPage'
  import {listArchives} from '@/api/task'

  export default {
    name: "TaskArchive",
    components: {
      TaskScrollPage
    },
    created() {
      this.listArchives()
    },

    watch: {
      '$route'() {
        if (this.$route.params.year && this.$route.params.month) {
          this.task.query.year = this.$route.params.year
          this.task.query.month = this.$route.params.month
        }
      }
    },
    data() {
      return {
        task: {
          query: {
            month: this.$route.params.month,
            year: this.$route.params.year
          }
        },
        archives: []
      }
    },
    computed: {
      title (){
        return this.currentArchive + ' - 任务归档 - 神的孩子都在歌唱'
      },
      currentArchive (){
        if(this.task.query.year && this.task.query.month){
          return `${this.task.query.year}年${this.task.query.month}月`
        }
        return '全部'
      }
    },
    methods: {

      changeArchive(year, month) {
        // this.currentArchive = `${year}年${month}月`
        // this.task.query.year = year
        // this.task.query.month = month

        this.$router.push({path: `/archives/${year}/${month}`})
      },
      listArchives() {
        listArchives().then((data => {
          this.archives = data.data
        })).catch(error => {
          this.$message({type: 'error', message: '任务归档加载失败!', showClose: true})
        })
      }
    }
  }
</script>

<style scoped>

<!--文章长度-->
.el-container {
  /*width: 900px;*/
}

.el-aside {
  /*position: fixed;*/
  margin-right: 50px;
  width: 160px !important;
  line-height: 300px;
}

.el-main {
  padding: 4px;
  line-height: 16px;
}

.me-month-list {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  list-style-type: none;
}

.me-month-item {
  margin-top: 18px;
  padding: 4px;
  font-size: 18px;
  color: #5FB878;
}

.me-order-list {
  float: right;
}

.me-month-title {
  margin-left: 4px;
  margin-bottom: 12px;
}
</style>
