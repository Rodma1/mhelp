<template>
  <el-main>
    <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="任务标题">
      <el-input v-model="form.title"></el-input>
    </el-form-item>
    <el-form-item label="有效时间">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="描述">
      <el-input type="textarea" v-model="form.summary"></el-input>
    </el-form-item>

    <el-form-item label="内容">
      <el-input type="textarea" v-model="form.editor.body"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="publishShow">立即发布</el-button>
      <el-button type="primary" @click="onSubmit">保存任务</el-button>
      <el-button>取消编辑</el-button>
    </el-form-item>
  </el-form>
    <el-dialog title="摘要 分类 标签"
               :visible.sync="publishVisible"
               :close-on-click-modal=false
               custom-class="me-dialog">

      <el-form :model="form" ref="form" >
        <el-form-item prop="summary">
          <el-input type="textarea"
                    v-model="form.summary"
                    :rows="6"
                    placeholder="请输入摘要">
          </el-input>
        </el-form-item>
        <el-form-item label="文章分类" prop="category">
          <el-select v-model="form.category" value-key="id" placeholder="请选择文章分类">
            <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="文章标签" prop="tags">
          <el-checkbox-group v-model="form.tags">
            <el-checkbox v-for="t in tags" :key="t.id" :label="t.id" name="tags">{{t.tagName}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="publishVisible = false">取 消</el-button>
        <el-button type="primary" @click="publish('form')">发布</el-button>
      </div>
    </el-dialog>
  </el-main>


</template>

<script>
import {publishTask} from '@/api/task'
import {getAllCategorys} from '@/api/category'
import {getAllTags} from '@/api/tag'
export default {
  name: "TaskWrite",
    //自动加载
  mounted() {
    this.getCategorysAndTags()

  },
  data() {
      return {
        publishVisible: false,
        categorys: [],
        tags: [],
        form: {
          title: '',
          category: '',
          summary: '',
          tags: [],
          data1:'',
          data2:'',
          editor:{
            body: '',
          }
        }
      }
    },
    methods: {
      onSubmit() {
        console.log(this.form)
        console.log('submit!');
      },
      //发布前检查
      publishShow() {
        if (!this.form.title) {
          this.$message({message: '标题不能为空哦', type: 'warning', showClose: true})
          return
        }

        if (this.form.title.length > 30) {
          this.$message({message: '标题不能大于30个字符', type: 'warning', showClose: true})
          return
        }

        if (!this.form.editor.body) {
          this.$message({message: '内容不能为空哦', type: 'warning', showClose: true})
          return
        }

        this.publishVisible = true;
      },
      //发布文章
      publish(form){
        let that=this
        this.$refs[form].validate((valid)=>{
          console.log(form)
          if (valid){
            let tags=this.form.tags.map(function (item){
              return {id:item};
            });
            let task = {
              id: this.form.id,
              title: this.form.title,
              summary: this.form.summary,
              category: this.form.category,
              tags: tags,
              body: {
                content: this.form.editor.body,
              }

            }
            this.publishVisible = false;
            let loading = this.$loading({
              lock: true,
              text: '发布中，请稍后...'
            })
            publishTask(task,this.$store.state.token).then((data)=>{
              if (data.success){
                loading.close();
                that.$message({message:'发布成功了',type:'success',showClose:true})
                //后面发布成功后跳转到发布的任务
                // that.$router.push({path: `/view/${data.data.id}`})
              }else{
                that.$message({message:error,type:'发布任务失败:'+data.msg,showClose:true});
              }
            }).catch((error)=>{
              loading.close();
              if (error!=='error'){
                that.$message({message:error,type:'error',showClose:true});
              }
            })
          }else {
            return false;
          }
        });

      },
      //获取所有分类
      getCategorysAndTags() {
        let that = this
        getAllCategorys().then(data => {
          if(data.success){
            that.categorys = data.data
          }else{
            that.$message({type: 'error', message: '文章分类加载失败', showClose: true})
          }

        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章分类加载失败', showClose: true})
          }
        })
        //  获取所有标签
        getAllTags().then(data => {
          if(data.success){
            that.tags = data.data
          }else{
            that.$message({type: 'error', message: '标签加载失败', showClose: true})
          }
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '标签加载失败', showClose: true})
          }
        })

      },


    }
}
</script>

<style scoped>

</style>
