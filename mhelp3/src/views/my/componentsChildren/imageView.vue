<template>
  <div class="imageView">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/back.png" alt="" />
      </div>
      <div slot="center">图片文字提取</div>
    </nav-bar>
    <div class="content">
      <div class="upload">
        <van-uploader
          v-model="fileList"
          multiple
          :max-count="1"
          :after-read="afterRead"
          class="item"
        ></van-uploader>
      </div>
      <div class="after">
        {{ after }}
      </div>
    </div>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
import axios from "axios";
export default {
  components: {
    navBar,
  },
  data() {
    return {
      fileList: [],
      after: "",
    };
  },
  methods: {
    goback() {
      this.$router.back();
    },
    afterRead(file) {
      file.status = "uploading";
      file.message = "上传中...";
      setTimeout(() => {
        file.status = "done";
        file.message = "";
        var formData = new FormData();
        formData.append("card", file.file);
        axios({
          url: "http://121.5.158.83:8002/student/ocr",
          method: "post",
          headers: {
            Authorization: "",
            "Content-Type": "multipart/form-data",
          },
          data: formData,
        }).then((res) => {
          console.log(res.data);
          this.after = res.data;
        });
      }, 200);
    },
  },
};
</script>
<style scoped>
.imageView {
  height: 100vh;
  position: relative;
  z-index: 12;
  background-color: #f9f9f9;
}
.left img {
  margin-top: 8px;
  width: 30px;
  height: 30px;
}
.upload {
  height: 100px;
  border: 1px solid #4395ff;
}
.content {
  padding: 10px;
}
.after {
  margin-top: 10px;
  border: 0px;
  background-color: #f0f0f0;
  height: 350px;
  padding: 10px;
  font-size: 18px;
  font-weight: 500;
  /* width: 100%; */
}
</style>