<template>
  <div class="fillDetails">
    <div class="title">
      <div>任务标题</div>
      <input type="text" v-model="params.title" />
    </div>
    <div class="summary">
      <textarea placeholder="输入正文..." v-model="params.content"></textarea>
      <div>
        <van-uploader
          v-model="fileList"
          multiple
          :max-count="9"
          :after-read="afterRead"
        ></van-uploader>
      </div>
    </div>
    <div class="publishBtn">
      <div @click="publish">发布</div>
    </div>
  </div>
</template>
<script>
import { uploadImage } from "network/upload.js";

export default {
  components: {},
  data() {
    return {
      params: {
        title: "",
        content: "",
      },
      fileList: [
        { url: "https://img01.yzcdn.cn/vant/leaf.jpg" },
        // Uploader 根据文件后缀来判断是否为图片文件
        // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
        // { url: "https://cloud-image", isImage: true },
      ],
    };
  },
  created() {},
  methods: {
    publish() {
      if (this.$store.state.id) {
        if (this.params.title && this.params.content) {
          this.$emit("isShow");
        } else {
          this.$toast("标题和内容均不能为空");
          // Toast('内容和文字均不能为空')
        }
      }
      else{
        this.$router.push("/loging")
      }
    },
    afterRead(file) {
      file.status = "uploading";
      file.message = "上传中...";
      var formData = new FormData();
      console.log(file);
      console.log(file.file);
      formData.append("file", file.file);
      setTimeout(() => {
        file.status = "done";
        file.message = "";
        uploadImage(this.$store.state.token, formData).then((res) => {
          console.log(res);
        });
      }, 200);
    },
  },
};
</script>
<style scoped>
.fillDetails {
  /* height: calc(100%-44px); */
  padding: 10px;
  background: #f3f3f3;
}
.title {
  height: 50px;
  background: white;
  display: flex;
  line-height: 50px;
}
.title div {
  padding-left: 10px;
  font-size: 18px;
  /* margin-right: 20px; */
}
.title input[type="text"] {
  flex: 1;
  outline: none;
  border: 0px;
  text-align: right;
  padding: 0px 30px;
}
.summary {
  height: 430px;
  /* background: green; */
  margin-top: 10px;
  position: relative;
  /* padding: 5px; */
}
.summary textarea {
  /* padding: 0px 10px; */
  height: 160px;
  width: 100%;
  border: 0px;
  resize: none;
  /* margin:0px;
    padding: 0px; */
}
.summary div {
  /* padding: 0px 10px; */
  width: 100%;
  height: 270px;
  background: white;
  position: absolute;
  bottom: 0px;
  left: 0px;
}
.summary div img {
  width: 70px;
  height: 70px;
}
.publishBtn {
  height: 50px;
  /* background: yellow; */
  margin: 10px;
  position: relative;
}
.publishBtn div {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 200px;
  height: 35px;
  background-color: #1facf8;
  font-size: 20px;
  text-align: center;
  line-height: 35px;
  color: white;
}
</style>
