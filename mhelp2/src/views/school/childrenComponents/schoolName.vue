<template>
  <div class="schoolName">
    <van-index-bar :index-list="schoolIndexBar">
      <div v-for="(item, index) in schoolList" :key="index" class="schoolItem">
        <van-index-anchor :index="item.arc" />
        <div v-for="(i, idx) in item.list" :key="idx">
          <van-cell
            :title="i.name"
            @click="chooseSchool(index, idx)"
            class="item"
            :class="{ active: isActive(index, idx) }"
          />
        </div>
      </div>
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
      schoolNames: [],
      schoolList: [],
      updateMsg:{
        school:''
      },
    };
  },
  props: {
    findX: {
      type: Number,
      default: null,
    },
    findY: {
      type: Number,
      default: null,
    },
  },
  computed: {
    isActive() {
      return function (index, idx) {
        if (index == this.findX && idx == this.findY) {
          return true;
        } else {
          return false;
        }
      };
    },
  },
  mounted() {
    this.school();
  },
  updated() {
    this.itemImageLoad();
  },

  methods: {
    //数组去重
    unique(origin) {
      const result = [];
      const set = new Set();
      for (const i of origin) {
        if (!set.has(i)) {
          result.push(i);
          set.add(i);
        }
      }
      return result;
    },
    school() {
      schools().then((res) => {
        console.log(res);
        this.schools = res.data;
        // console.log(this.schools);
        for (var i = 0; i < this.schools.length; i++) {
          this.schoolNames[i] = this.schools[i].name;
          var school = pinyin(this.schools[i].name).substr(0, 1).toUpperCase();
          // console.log(school);
          this.schoolIndexBar[i] = school;
        }
        this.schoolIndexBar = this.schoolIndexBar.sort();
        this.schoolIndexBar = this.unique(this.schoolIndexBar);
        // console.log(this.schoolIndexBar);
        this.schoolIndexBar.forEach((item) => {
          let obj = {
            arc: "",
            list: [],
          };
          let list = [];
          this.schools.forEach((i) => {
            if (pinyin(i.name).substr(0, 1).toUpperCase() == item) {
              list.push(i);
            }
          });
          obj.arc = item;
          obj.list = list;
          this.schoolList.push(obj);
        });
        this.$emit("schoolList", this.schoolList);
      });
    },
    chooseSchool(index, idx) {
      console.log(index, idx);
      console.log(this.schoolList[index].list[idx]);
      this.updateMsg.school=this.schoolList[index].list[idx].name
      console.log(this.updateMsg.school)
      this.$store.dispatch('updateUserInfo',this.updateMsg).then(()=>{
        this.exist()
      })
    },
    itemImageLoad() {
      this.$bus.$emit("itemImageLoad");
    },
     exist() {
      this.$store.dispatch("logout");
      setTimeout(() => {
        console.log(2)
        this.$router.push("/loging");
      }, 200);
    },
  },
};
</script>
<style scoped>
.schoolName {
  padding: 0px 20px;
}
.van-index-anchor {
  background-color: #00000000;
}
.schoolItem {
  background-color: white;
}
.item {
  border-bottom: 1px solid #eeeeee;
}
.active {
  font-size: 16px;
  font-weight: 600;
  color: rgb(154, 171, 248);
}
</style>
