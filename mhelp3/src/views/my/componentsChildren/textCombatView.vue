<template>
  <div class="textCombatView">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/back.png" alt="" />
      </div>
      <div slot="center">文本对抗</div>
    </nav-bar>
    <div class="content">
      <textarea class="before" v-model="value"></textarea>
      <!-- <div class="upload">
        <van-uploader
          v-model="fileList"
          multiple
          :max-count="1"
          :after-read="afterRead"
          class="item"
        ></van-uploader>
      </div> -->
      <div class="after" >{{after}}</div>
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
      this.after=""
    },
    sure() {
      //  var csrftoken = this.getCookie('csrftoken');
      console.log(this.value);
      // const instance = axios.create();
      axios({
        url: "http://121.5.158.83:8002/student/model",
        method: "post",
        headers: { 
          // "X-CSRFToken":csrftoken
          },
        data: {
          wenben: this.value,
        },
      }).then((res) => {
        this.after=res.data
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
          url: "http://127.0.0.1:8000/student/ocr",
          method: "post",
          headers: {
            'Authorization': "",
            "Content-Type": "multipart/form-data",
          },
          data: formData,
        }).then((res) => {
          console.log(res.data)
          this.after=res.data
        });
      }, 200);
    },
//     getCookie(name) {
//     var cookieValue = null;
//     if (document.cookie && document.cookie != '') {
//         var cookies = document.cookie.split(';');
//         for (var i = 0; i < cookies.length; i++) {
//             var cookie = jQuery.trim(cookies[i]);
//             // Does this cookie string begin with the name we want?
//             if (cookie.substring(0, name.length + 1) == (name + '=')) {
//                 cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
//                 break;
//             }
//         }
//     }
//     return cookieValue;
// }
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
    font-size: 18px;
  font-weight: 500;
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
  font-size: 18px;
  font-weight: 500;
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
