<template>
  <div class="homeTaskItem">
    <div class="title">
      <img :src="item.avatar" alt="" @load="itemImageLoad" />
      <div class="center">
        <div class="name">{{ item.author }}</div>
        <!-- <div class="tags">{{item.tags[0].tagsName}}</div> -->
        <div class="time">{{ item.createDate }}</div>
      </div>
      <div class="remarks">
        <img src="@/assets/img/home/更多.png" alt="" />
      </div>
    </div>
    <div class="content">
      <div class="type">
        {{ item.title }}
      </div>
      <div class="description">
        {{ item.summary }}
      </div>
      <ul class="picture">
        <li v-for="(i, index) in item.images" :key="index">
          <img :src="i" alt="" @load="itemImageLoad" />
        </li>
      </ul>
    </div>
    <div class="other">
      <div @click="goCollect" :class="{ isCollect: isCollect2 }">
        <span>收藏</span>
      </div>
      <div>
        <span>{{ item.money }}金币</span>
      </div>
      <div @click="acceptTask">
        <span>接任务</span>
      </div>
    </div>
    <toast :message="toast" :logingShow="isCollect" class="toast"></toast>
  </div>
</template>
<script>
import { goCollect } from "network/task.js";
import toast from "components/common/toast/toast.vue";

export default {
  components: {
    toast,
  },
  props: {
    item: {
      type: Object,
      default() {
        return {};
      },
    },
    collectList: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  data() {
    return {
      isActive: true,
      count: 2,
      isFinish: false,
      msg: "收藏",
      toast: "收藏成功",
      isCollect: false,
      isCollect2: false,
    };
  },
  created() {},
  mounted() {
    for (var i = 0; i < this.collectList.length; i++) {
      if (this.collectList[i] == this.item.id) {
        this.isCollect2 = true;
      }
    }
  },
  methods: {
    acceptTask() {
      if (this.$store.state.id) {
        if (this.item.authorId !== this.$store.state.id) {
          var path;
          if (this.$route.name == "home") {
            path = "/home/pay/" + this.item.id;
            this.$router.push(path);
          } else {
            path = this.$route.fullPath +"/pay/"+ this.item.id;
            this.$router.push(path);
          }
        } else {
          console.log("自己不能接自己发的任务");
        }
      } else {
        this.$router.push("/loging");
        this.$bus.$emit("getItem", this.item);
      }
    },
    itemImageLoad() {
      this.$bus.$emit("itemImageLoad");
    },
    goCollect() {
      // console.log(this.collectList)
      var a = this.collectList.find((i) => {
        console.log(i);
        return i == this.item.id;
      });
      if (a) {
        goCollect(this.$store.state.token, this.item.id).then((res) => {
          console.log(res);
          this.toast = "取消收藏";
          this.isCollect = true;
          setTimeout(() => {
            this.isCollect = false;
          }, 1000);
          this.isCollect2 = false;
          this.$emit("updateCollectList");
        });
      } else {
        goCollect(this.$store.state.token, this.item.id).then((res) => {
          console.log(res);
          this.toast = "收藏成功";
          this.isCollect = true;
          setTimeout(() => {
            this.isCollect = false;
          }, 1000);
          this.isCollect2 = true;
          this.$emit("updateCollectList");
        });
      }
    },
  },
};
</script>

<style scoped>
.homeTaskItem {
  /* height: 150px; */
  background: white;
  margin: 10px 5px;
  padding: 0px 8px;
  padding-bottom: 10px;
  position: relative;
}
.title {
  height: 50px;
  /* background: pink; */
  display: flex;
  padding: 5px 0px 0px 0px;
}
.title img {
  width: 40px;
  height: 40px;
}
.title div img {
  width: 30px;
  height: 30px;
}
.title > div:nth-of-type(1) {
  flex: 1;
  /* background-color: blue; */
}
.title > div:nth-of-type(2) {
  width: 60px;
  /* background-color: purple; */
  text-align: right;
}
.name {
  font-size: 18px;
}
.center {
  margin-left: 10px;
}
.time,
.tags {
  color: #757373;
  font-size: 12px;
}

.type {
  /* height: 30px; */
  font-weight: 500;
  font-size: 18px;
  /* line-height: 30px; */
}
.description {
  font-weight: 400;
  font-size: 15px;
  /* font-family: 'Courier New', Courier, monospace; */
  /* margin-bottom: px; */
}
.picture {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: 100%;
  /* margin: 0px 10px; */
  /* justify-content: center; */
}
/* 设置为换行，每个元素占1/3或指定宽度，除每行最后一个元素（3n）都设置margin-right并通过预留间隔； */
.picture li {
  width: 32%;
  height: 105px;
  margin-top: 5px;
}
.picture li:not(:nth-child(3n)) {
  margin-right: 2%;
}
/* 对于只有一张图片情况，只需用css选择器判断为一张图片时，改变图片大小即可;
选择器逻辑：元素为 倒数第一个元素  && 第一个元素 时，则可判断只有一个元素：对其样式单独设置覆盖原样式即可 */
.picture li:nth-last-child(1):first-child {
  width: 170px;
  height: 170px;
}

.picture li:nth-last-child(2):first-child,
ul li:nth-last-child(2):first-child ~ li {
  width: 48%;
  height: 150px;
}
/* 对于四张图片的情况时，图片需呈 两行两列布局：这里就需对此种情况下的第二张图片添加margin-right实现三列变两列：
选择器逻辑： 元素为 倒数第4个&&  第一个的元素 时， 判断为共有四个元素，
再选择 其后的 同级元素 的第 2n 个后添加margin-right属性； */

.picture li:nth-last-child(4):first-child ~ li:nth-child(2n) {
  margin-right: 0%;
}
.picture li:nth-last-child(4):first-child,
ul li:nth-last-child(4):first-child ~ li {
  width: 49%;
  height: 150px;
  margin-right: 5px;
}
.picture img {
  /* width: calc(100%/3.9); */
  width: 100%;
  height: 100%;
  /* flex: 1; */
  margin-right: 0.5%;
  margin-bottom: 5px;
  /* margin-top: 10px; */
}
.other {
  height: 30px;
  /* background-color: yellow; */
  display: flex;
  border: 2px solid #eeeeee;
  margin-top: 8px;
  border-radius: 10px;
}
.other div {
  flex: 1;
  text-align: center;
  line-height: 30px;
}
.other .isCollect {
  color: #1facf8;
  font-weight: 600;
}
.other div:nth-child(2) {
  border-left: 2px solid #eeeeee;
  border-right: 2px solid #eeeeee;
}
.other div:nth-child(3) {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #1facf8;
  color: white;
}
.other img {
  height: 30px;
  width: 30px;
}
.title .remarks img {
  width: 20px;
  height: 20px;
}
.remarks {
  /* margin-right:px ; */
  margin-top: 10px;
}
.show {
  position: relative;
  height: 200px;
  background: red;
  z-index: 9;
}
.toast {
  width: 50%;
  height: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
</style>