<template>
  <div class="calendar">
    <div class="title">
      <div @click="lastMonth">上个月</div>
      <div>{{ currentYear }}年{{ currentMonth + 1 }}月</div>
      <div @click="nextMonth">下个月</div>
    </div>
    <div class="content">
      <ul class="week">
        <li>日</li>
        <li>一</li>
        <li>二</li>
        <li>三</li>
        <li>四</li>
        <li>五</li>
        <li>六</li>
      </ul>
      <ul class="day">
        <li v-for="count in currentWeek" :key="count"></li>
        <li v-for="(item, index) in days" :key="index" class="days" >{{ item }}</li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      days: [], //当前月份的天数
      currentWeek: 1, //当前月份1号是星期几
      currentYear: 1970,
      currentMonth: 1,
      isActive:false,
      currentIndex:0
    };
  },
  created() {
    this.init();
    this.mgetDate();
  },
  computed:{
     
  },
  methods: {
    init() {
      var date = new Date();
      this.currentYear = date.getFullYear(); //获取当前年份
      this.currentMonth = date.getMonth(); //获取当前月份
    },
    mgetDate() {
      //由于JavaScript中day的范围为1~31中的值，所以当设为0时，会向前 一天，也即表示上个月的最后一天。
      //js中月份的范围是0-11,0代表一月份，11代表12月份
      var d = new Date(this.currentYear, this.currentMonth + 1, 0);
      var e = new Date(this.currentYear, this.currentMonth, 1);
      var dates = d.getDate(); //获取当前月份的天数
      this.currentWeek = e.getDay();
      for (var i = 1; i <= dates; i++) {
        this.days[i - 1] = i;
      }
    },
    lastMonth() {
      this.currentMonth--;
      if (this.currentMonth < 0) {
        this.currentYear--;
        this.currentMonth = 11;
      }
      console.log(this.currentMonth);
      this.mgetDate();
    },
    nextMonth() {
      this.currentMonth++;
      if (this.currentMonth > 11) {
        this.currentYear++;
        this.currentMonth = 0;
      }
      console.log(this.currentMonth);
      this.mgetDate();
    },
    
  },
};
</script>

<style scoped>
.calendar {
  /* height: 200px; */
  background-color: white;
  border-radius: 10px;
  margin: 0px 10px;
}
.title {
  height: 34px;
  /* background-color: pink; */
  display: flex;
  text-align: center;
  line-height: 34px;
  border-bottom: 1px solid #e4e2e2;
}
.title div:nth-child(1),
.title div:nth-child(3) {
  width: 60px;
  /* background-color: blue; */
}
.title div:nth-child(2) {
  flex: 1;
}
.content .week {
  list-style-type: none;
  display: flex;
  margin-top: 8px;
}
.week li {
  flex: 1;
  text-align: center;
}
.day {
  display: flex;
  flex-wrap: wrap;
}
.day li {
  width: 48.5px;
  text-align: center;
  height: 35px;
  line-height: 35px;
}
.signed{
  background-image: url(../../../assets/img/signIn/签到.png);
  background-size: 30px 30px;
  background-repeat: no-repeat;
  background-position: center;
}

</style>
