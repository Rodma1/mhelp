<template>
  <div class="check">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/back.png" alt="" />
      </div>
      <div slot="center">修改密码</div>
    </nav-bar>
    <div class="upload">
      <div>
        <div class="tips">上传学生证</div>
        <van-uploader
          v-model="fileList"
          multiple
          :max-count="1"
          :after-read="afterRead"
          class="item"
        ></van-uploader>
        <!-- <div class="success" v-if="isShow">
          <img src="@/assets/img/example/成功.png" alt="" v-if="isSuccess" />
          <img src="@/assets/img/example/错误.png" alt="" v-else />
        </div> -->
      </div>

      <div>
        <div class="tips">上传脸部照片</div>

        <van-uploader
          v-model="fileList1"
          multiple
          :max-count="1"
          :after-read="afterRead2"
          class="item"
        ></van-uploader>
        <!-- <div class="success" v-if="isShow2">
          <img src="@/assets/img/example/成功.png" alt="" v-if="isSuccess2" />
          <img src="@/assets/img/example/错误.png" alt="" v-else />
        </div> -->
      </div>
    </div>
    <div class="checkBtn" @click="checking">
      <img src="@/assets/img/example/load.png" alt="" v-if="isCheck">
      <div v-else>{{ msg }}</div>
    </div>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
import axios from "axios";
import {setCheck} from "network/token.js"
export default {
  components: {
    navBar,
  },
  data() {
    return {
      fileList: [
        // { url: "https://img01.yzcdn.cn/vant/leaf.jpg" },
        // Uploader 根据文件后缀来判断是否为图片文件
        // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
        // { url: "https://cloud-image", isImage: true },
      ],
      fileList1: [],
      card: null,
      isSuccess: false,
      isSuccess2: false,
      msg: "校验",
      isCheck:false
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
        console.log(formData);
        axios({
          url: "http://121.5.158.83:8002/student/train",
          method: "post",
          headers: { Authorization: "" },
          data: formData,
        }).then((res) => {
          console.log(res)
          if (res.data == "ok") {
            this.isSuccess = true;
            console.log(this.isSuccess2)
          } else {

            this.isSuccess = false;
          }
        });
      }, 200);
    },
    afterRead2(file) {
      file.status = "uploading";
      file.message = "上传中...";
      setTimeout(() => {
        file.status = "done";
        file.message = "";
        var formData = new FormData();
        formData.append("card", file.file);
        axios({
          url: "http://121.5.158.83:8002/student/detec",
          method: "post",
          headers: { Authorization: "" },
          data: formData,

        }).then((res) => {
        console.log(res)
          if (res.data == "ture") {
            this.isSuccess2 = true;
          } else {
            this.isSuccess2 = false;
          }
        });
      }, 200);
    },
    checking() {
      if (this.isSuccess2 == true) {
        this.isCheck=true
        setTimeout(()=>{
          this.isCheck=false;
        },800)
        this.msg="校验成功"
        this.$bus.$emit("check")
        setCheck(this.isSuccess2)
      }
      else{
        this.isCheck=true
        setTimeout(()=>{
          this.isCheck=false;
        },800)
        this.msg="校验失败"
        this.$toast("检车上传是否是学生证和本人照片")
      }
      
    },
  },
};
</script>
<style scoped>
.check {
  position: relative;
  z-index: 15;
  background-color: white;
  height: 100vh;
}
.left img {
  width: 30px;
  height: 30px;
}
.upload {
  height: 300px;
  padding: 20px;
}
.upload > div {
  position: relative;
}
.tips {
  margin-bottom: 10px;
  font-size: 20px;
  font-weight: 500;
}
.item {
  height: 100px;
  /* background-color: #f3f3f3; */
  width: 100%;
  padding: 10px 0px;
}
.checkBtn {
  width: 80%;
  height: 40px;
  background-color: #3dbafddc;
  margin: 0px auto;
  font-size: 20px;
  text-align: center;
  line-height: 40px;
  color: white;
}
.checkBtn img{
  height: 30px;
  width: 30px;
  animation: rotate 1s infinite;
}
.success {
  height: 50px;
  width: 50px;
  position: absolute;
  right: 20%;
  top: 50px;
}
.success img {
  height: 50px;
  width: 50px;
}
@keyframes rotate {
  from {
    transform: rotateZ(0deg);
  }

  to {
    transform: rotateZ(360deg);
  }
}
</style>
