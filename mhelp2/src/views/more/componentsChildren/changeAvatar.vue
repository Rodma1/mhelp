<template>
  <div class="changeAvatar" @click="goback">
    <form action="">
      <div class="upLoad"  enctype="multipart/form-data">
        <div>
          <img src="@/assets/img/example/相机.png" alt="" />
          <div>拍照</div>
          <input
            type="file"
            name="images"
            accept="image/*"
            capture="camera"
            class="upLoadImage first"
            @change="changeAvatar"
            action="/insertImage"
          />
        </div>
        <div>
          <img src="@/assets/img/example/图片.png" alt="" />
          <div>从相册中选择</div>
          <input type="file" accept="image/*" class="upLoadImage second" />
        </div>
      </div>
    </form>
    
  </div>
</template>
<script>
import { uploadAvatar } from "network/upload.js";
export default {
  data() {
    return {
      avatar: [],
      updateMsg: {
        avatar: [],
      },
    };
  },
  methods: {
    goback() {
      this.$router.back();
    },
    changeAvatar(e) {
      console.log("aa");
      console.log(e);
      var file = e.target.files;
      console.log(e.target.value)
      console.log(file);
      this.avatar = file;
      console.log(this.avatar);
      var formData = new FormData();
      formData.append("avatar", formData);
      // formData.append("name",e.target.value)
      console.log(e.target.files[0]);
      console.log(formData.get("avatar"));
      uploadAvatar(this.$store.state.token, e).then((res) => {
        console.log(res);
      });
    },
  },
};
</script>
<style scoped>
.changeAvatar {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  /* height: 100vh; */
  z-index: 11;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.3);
}
.upLoad {
  width: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -90%);
}
.upLoad > div {
  width: 80%;
  height: 45px;
  background-color: white;
  margin: 0px auto;
  display: flex;
  align-items: center;
  /* line-height: 45px; */
}
.upLoad > div > img {
  width: 30px;
  height: 30px;
  margin: 0px 10px;
}
.upLoadImage {
  display: block;
  width: 100%;
  height: 45px;
  font-size: 999px;
  filter: alpha(opacity=0);
  opacity: 0;
  position: absolute;
  right: 0;
  text-align: right;
  top: 0;
  cursor: pointer;
}
.frist {
  top: 0px;
}
.second {
  top: 45px;
}
</style>