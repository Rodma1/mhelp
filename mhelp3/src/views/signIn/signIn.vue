<template>
  <div class="signIn">
    <sign-in-nav></sign-in-nav>
    <go-sign :isSign="isSign" :days="allListSign.length"></go-sign>
    <calendar
      class="calendar"
      :listSign="listSign"
      @lastMonth="lastMonth"
      @nextMonth="lastMonth"
    ></calendar>
    <sign-in-rules class="signInRules"></sign-in-rules>
  </div>
</template>
<script>
import calendar from "views/signIn/componentsChildren/calendar.vue";
import signInNav from "views/signIn/componentsChildren/signInNav.vue";
import goSign from "views/signIn/componentsChildren/goSign.vue";
import signInRules from "views/signIn/componentsChildren/signInRules.vue";
import { listsign, todaysign } from "network/sign.js";
// import { sign, todaysign } from "network/sign.js";
export default {
  components: {
    calendar,
    signInNav,
    goSign,
    signInRules,
  },
  data() {
    return {
      listSign: [],
      currentDay: 0,
      allListSign: [],
      isSign: 0,
    };
  },
  mounted() {
    this.todaysign();
    this.getListsign();
    this.$bus.$on("sign", (item) => {
      console.log(item);
      this.isSign = 1;
      this.getListsign();
    });
  },
  updated() {
    // console.log(this.isSign)
  },
  methods: {
    getListsign() {
      listsign(this.$store.state.token).then((res) => {
        this.allListSign = res.data;
        console.log(this.allListSign);
        for (var i = 0; i < res.data.length; i++) {
          var time = res.data[i].signInDate;
          var ymd = time.substr(0, 10);
          var year = ymd.substr(0, 4);
          var mounth = ymd.substr(5, 2);
          var day = ymd.substr(8, 2);
          var date = new Date();
          var y = date.getFullYear();
          var m = date.getMonth() + 1;
          if (year == y && m == mounth) {
            this.listSign.push(parseInt(day));
          }
        }
        // console.log(this.listSign);
      });
    },
    lastMonth(currentMonth, currentYear) {
      this.listSign = [];
      for (var i = 0; i < this.allListSign.length; i++) {
        var time = this.allListSign[i].signInDate;
        var ymd = time.substr(0, 10);
        var year = ymd.substr(0, 4);
        var mounth = ymd.substr(5, 2);
        var day = ymd.substr(8, 2);
        year = parseInt(year);
        mounth = parseInt(mounth);
        day = parseInt(day);
        if (year == currentYear && currentMonth + 1 == mounth) {
          this.listSign.push(parseInt(day));
        }
      }
    },
    todaysign() {
      todaysign(this.$store.state.token).then((res) => {
        console.log(res);
        if (typeof res.data == String) {
          if (res.data == "你未签到过") {
            this.isSign = 0;
          } else {
            this.isSign = 1;
          }
        } else {
          this.isSign = res.data;
          console.log(this.isSign);
        }
      });
    },
  },
};
</script>
<style scoped>
.signIn {
  height: 100vh;
  position: relative;
  z-index: 10;
  background: #f6f6f6;
  position: relative;
}
.calendar {
  position: absolute;
  top: 150px;
}
.signInRules {
  position: absolute;
  top: 400px;
}
</style>
