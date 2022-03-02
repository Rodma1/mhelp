<template>
  <div class="schoolName">
    <van-index-bar :index-list="schoolIndexBar">
      <div v-for="(item,index) in schoolIndexBar" :key="index">
        <van-index-anchor :index="item" />
        <van-cell :title="item" v-for="(item,index) in schoolNames" :key="index"/>
      </div>
        
        <!-- <van-cell title="文本" />
        <van-cell title="文本" /> -->

      <!-- <van-index-anchor index="B" />
  <van-cell title="文本" />
  <van-cell title="文本" />
  <van-cell title="文本" /> -->

      ...
    </van-index-bar>
  </div>
</template>
<script>
import { schools } from "network/task.js";
import { pinyin } from "pinyin-pro";
export default {
  data() {
    return {
      foo: "bar",
      schools: [],
      schoolIndexBar: [],
      schoolNames:[]
    };
  },
  mounted() {
    schools().then((res) => {
      console.log(res);
      this.schools = res.data;

      console.log(this.schools)
      // console.log( pinyin('汉语拼音').substr(0,1).toUpperCase())
      pinyin("汉语拼音");
      for (var i = 0; i < this.schools.length; i++) {
         this.schoolNames[i]=this.schools[i].name
        var school = pinyin(this.schools[i].name).substr(0, 1).toUpperCase();
        this.schoolIndexBar[i] = school;
      }
      this.schoolIndexBar = this.schoolIndexBar.sort();
      console.log(this.schoolIndexBar);
      // v-for="(item,index) in schoolIndexBar" :key="index"
    });
  },
};
</script>

<style scoped>
.van-index-anchor {
  background-color: #00000000;
}
.schoolItem {
  background-color: white;
}
.schoolItem > div {
  height: 50px;
  margin: 0px 20px;
  line-height: 50px;
  border-bottom: 1px solid gray;
}
</style>
