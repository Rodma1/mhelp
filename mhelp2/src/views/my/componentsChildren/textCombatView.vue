<template>
  <div class="textCombatView">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/返回.png" alt="" />
      </div>
      <div slot="center">文本对抗</div>
    </nav-bar>
    <div class="content">
      <textarea class="before" v-model="value"></textarea>
      <div class="upload">
        <van-uploader
          v-model="fileList"
          multiple
          :max-count="9"
          :after-read="afterRead"
          class="item"
        ></van-uploader>
      </div>
      <div class="after" :after="after"></div>
    </div>
    <div class="btns">
      <div class="clear" @click="clear">清除</div>
      <div class="sure" @click="sure">确认</div>
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
      value: "",
      fileList: [],
      after: "",
    };
  },
  methods: {
    goback() {
      this.$router.back();
    },
    clear() {
      this.value = "";
    },
    sure() {
      console.log(this.value);
      // const instance = axios.create();
      axios({
        url: "http://192.168.43.252:8000/student/model",
        method: "post",
        headers: { Authorization: "" },
        data: {
          wenben: this.value,
        },
      }).then((res) => {
        console.log(res);
      });
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
          url: "http://127.0.0.1:8080/student/model",
          method: "post",
          headers: {
            'Authorization': "",
            "Content-Type": "multipart/form-data",
          },
          data: formData,
        }).then((res) => {
          console.log(res);
        });
      }, 200);
    },
  },
};
</script>
<style scoped>
.textCombatView {
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
.content {
  padding: 10px;
}
textarea {
  height: 200px;
  width: 100%;
  resize: none;
  border: 1px solid #4395ff;
}
.btns {
  height: 40px;
  /* background-color: aquamarine; */
  display: flex;
  margin-top: 20px;
}
.btns div {
  flex: 1;
  margin: 0px 20px;
  height: 40px;
  line-height: 40px;
  text-align: center;
}
.after {
  border: 0px;
  background-color: #f0f0f0;
  height: 200px;
  width: 100%;
  margin-top: 10px;
}
.clear {
  background-color: white;
  border: 1px solid #dcdcdc;
}
.sure {
  background-color: #1facf8;
}
.upload {
  background-color: white;
  border: 1px solid #4395ff;
  border-top: 0px;
}
</style>
