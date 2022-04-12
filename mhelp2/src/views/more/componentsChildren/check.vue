<template>
  <div class="check">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/返回.png" alt="" />
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
      </div>
      <div>
        <div class="tips">上传脸部照片</div>

        <van-uploader
          v-model="fileList1"
          multiple
          :max-count="1"
          :after-read="afterRead"
          class="item"
        ></van-uploader>
      </div>
    </div>
    <div class="checkBtn">校验</div>
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
      fileList: [
        // { url: "https://img01.yzcdn.cn/vant/leaf.jpg" },
        // Uploader 根据文件后缀来判断是否为图片文件
        // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
        // { url: "https://cloud-image", isImage: true },
      ],
      fileList1: [],
      card:null
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
        let reader = new FileReader();
        reader.readAsBinaryString(file.file);
        console.log(reader);
       reader.onload=function(){
           console.log(this.result)

            // var startNum = card.indexOf("base64,")
            // console.log(startNum)
            // startNum = startNum*1 + 7
            //  card = card.slice(startNum);
            //  console.log(card)
            this.card={card:this.result}
            // console.log(JSON.parse(this.card))
            console.log(this.card)
       }
          axios({
            url: "http://192.168.43.252:8000/student/train",
            method: "post",
            headers: { Authorization: "" },
            data: {
                card:JSON.parse(this.card) 
            },
          }).then((res) => {
            console.log(res);
          });
      }, 200);
    },
    afterRead2(file) {
      file.status = "uploading";
      file.message = "上传中...";
      setTimeout(() => {
        file.status = "done";
        file.message = "";
        let reader = new FileReader();
        reader.readAsBinaryString(file.file);
        reader.onload=function(){
           console.log(this.result)

            // var startNum = card.indexOf("base64,")
            // console.log(startNum)
            // startNum = startNum*1 + 7
            //  card = card.slice(startNum);
            //  console.log(card)
            this.card={card:this.result}
            // console.log(JSON.parse(this.card))
            console.log(this.card)
       }
        axios({
          url: "http://192.168.43.252:8000/student/detec",
          method: "post",
          headers: { Authorization: "" },
          data: {
            card:JSON.parse(this.card) 
          },
        }).then((res) => {
          console.log(res);
        });
        console.log(2);
      }, 200);
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
</style>
