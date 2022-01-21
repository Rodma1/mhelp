<template>
  <div class="publishTost">
    <div class="content">
      <div class="title">发布任务</div>
      <div class="cancle" @click="cancle">
        <img src="@/assets/img/example/取消.png" alt="" />
      </div>
      <div class="item discribe">
        <div>备注:</div>
        <textarea
          placeholder="可为空"
          v-model="params.remark"
        ></textarea>
      </div>
      <div class="item">
        <div>分类:</div>
        <select v-model="params.category">
          <option v-for="(item, index) in category" :key="index" >
            {{ item }}
          </option>
        </select>
      </div>
      <div class="item">
        <div>标签:</div>
        <select v-model="params.tags">
          <option v-for="(item, index) in tags" :key="index" :currentTags="item" >
            {{ item }}
          </option>
        </select>
      </div>
      <div class="item">
        <div>价格:</div>
        <section>
          <input type="text" v-model="params.price" /><span>金币</span>
        </section>
      </div>
      <div class="publish" @click="publish">发布</div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      params: {
        remark: "",
        tags: "",
        category: "",
        price: "",
      },
      category: [
        "拿取快递",
        "求借东西",
        "食堂打包",
        "技术顾问",
        "学习帮助",
        "活动组队",
        "二手闲置",
        "寻找树洞",
        "其他",
      ],
      tags: ["聊天", "吃饭", "逛街", "玩耍"],
      currentCategory:'',
      currentTags:''
    };
  },
  props: {
    active: {
      type: Boolean,
      default() {
        return false;
      },
    },
  },
  created() {
  },
  // updated(){
  //   console.log(this.params)
  // },

  methods: {
    cancle() {
      this.$emit("change");
    },

    // judge() {
    //   if (this.remark.length >= 50) {
    //     console.log("此处内容不得大于50个字符");
    //   }
    // },
    publish(){
      console.log(this.params.category,this.params.tags)
      if(this.params.category&&this.params.tags&&this.params.price){
        this.$emit('publish')
      }
      else{
        this.$toast('分类标签和价格不可为空，必选')
      }
    }
  },
};
</script>

<style scoped>
.publishTost {
  /* width: 100%; */
  position: absolute;
  left: 0px;
  top: 0px;
  bottom: 0px;
  right: 0px;
  background-color: rgba(0, 0, 0, 0.6);
  height: 100vh;
  /* display: none; */
}
.content {
  width: 90%;
  position: absolute;
  margin: 0px 3%;
  padding: 2%;
  height: 350px;
  background: white;
  top: 50%;
  transform: translateY(-50%);
  border-radius: 8px;
}
.title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 10px;
}
.cancle img {
  width: 25px;
  height: 25px;
  position: absolute;
  right: 20px;
  top: 10px;
  /* background-color: red; */
}
.content .discribe {
  height: 70px;
  /* line-height: 70px; */
}
.content .discribe div {
  line-height: 70px;
}
.item {
  height: 50px;
  /* background-color: yellow; */
  margin-bottom: 10px;
  display: flex;
  position: relative;
}
.item div {
  width: 60px;
  line-height: 50px;
  font-size: 18px;
}
.item textarea {
  width: 80%;
  height: 100%;
  outline: none;
  border: 0px;
  position: absolute;
  left: 60px;
  top: 0px;
  resize: none;
  border-radius: 10px;
  border: 2px solid #f3f3f3f3;
}
.item input[type="text"] {
  width: 50px;
  border: 0px;
  height: 30px;
  border-bottom: 2px solid red;
}
select {
  border: 2px solid #f3f3f3f3;
  margin-top: 10px;
  width: 150px;
  height: 30px;
  background-color: white;
}

section {
  margin-top: 10px;
  text-align: center;
}
.publish {
  margin-top: 10px;
  width: 200px;
  height: 35px;
  background-color: #1facf8;
  text-align: center;
  /* border-radius: 10px; */
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  line-height: 35px;
  color: white;
}
</style>