<template>
  <div class="moreList">
    <div class="item" >
      <div>头像</div>
      <div><img src="@/assets/img/example/头像.jpg" alt="" /></div>
      <div @click="uploadAvatar"><img src="@/assets/img/my/右箭头.png" alt="" /></div>
    </div>
    <div class="item" @click="changeNickname">
      <div>昵称</div>
      <div>吼吼哈嘿</div>
      <div><img src="@/assets/img/my/右箭头.png" alt="" /></div>
    </div>
    <div class="item" @click="chooseSchool">
      <div>所在学校</div>
      <div>运城学院</div>
      <div><img src="@/assets/img/my/右箭头.png" alt="" /></div>
    </div>
    <div class="item" @click="changePassword" > 
      <div>修改密码</div>
      <div>......</div>
      <div><img src="@/assets/img/my/右箭头.png" alt="" /></div>
    </div>
    <div class="mask" v-if="isShow" @click="cancle">
      <div class="upLoad">
        <div>
          <img src="@/assets/img/example/相机.png" alt="">
          <div>拍照</div>
          <input
            type="file"
            accept="image/*"
            capture="camera"
            class="upLoadImage"
          />
        </div>
        <div>
          <img src="@/assets/img/example/图片.png" alt="">
          <div>从相册中选择</div>
          <input
            type="file"
            accept="image/*"
            class="upLoadImage"
          />
        </div>
      </div>
    </div>
    <div class="exist" @click="exist">退出登录</div>
    
  </div>
</template>
<script>

export default {
  data(){
    return{
      isShow:false
    }
  },
  methods: {
    exist() {
      this.$store.dispatch("logout").then((res) => {
        console.log(res);
        this.$router.push("/home");
      });
    },
    uploadAvatar(){
      this.isShow=true
    },
    cancle(){
      this.isShow=false
    },
    chooseSchool(){
      this.$router.push("/school")
    },
    changePassword(){
      this.$router.push("/more/changePassword")
    },
    changeNickname(){
      this.$router.push("/more/changeNickname")
    }
  },
};
</script>
<style scoped>
.moreList {
  background: #f4f4f4;
  position: absolute;
  top: 44px;
  bottom: 0px;
  left: 0px;
  right: 0px;
  /* margin-top: 10px; */
  padding-top: 10px;
}
.item {
  /* margin-top: 10px; */
  height: 50px;
  background: white;
  padding: 0px 8px;
  display: flex;
  border-bottom: 1px solid #e4e4e4;
}
.item div {
  line-height: 50px;
}
.item div:nth-child(1) {
  width: 100px;
  /* background: blue; */
}
.item div:nth-child(2) {
  flex: 1;
  text-align: right;
  /* background: red; */
}
.item div:nth-child(3) {
  width: 60px;
  text-align: right;
  /* background: green; */
}
.item div:nth-child(3) img {
  width: 15px;
  height: 15px;
}
.item div img {
  margin-right: 10px;
  width: 40px;
  height: 40px;
}
.exist {
  width: 250px;
  height: 30px;
  background: rgb(219, 11, 11);
  margin: 50px auto;
  color: white;
  text-align: center;
  line-height: 30px;
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
.upLoad > div >img{
  width: 30px;
  height: 30px;
  margin: 0px 10px;
}
.mask {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 11;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.3);
}
.upLoadImage {
  display: block;
  width: 100%;
  height: 50px;
  font-size: 999px;
  filter: alpha(opacity=0);
  min-height: 100%;
  min-width: 100%;
  opacity: 0;
  position: absolute;
  right: 0;
  text-align: right;
  top: 0;
  cursor: pointer;
}

</style>