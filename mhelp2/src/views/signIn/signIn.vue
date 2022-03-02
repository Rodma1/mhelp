<template>
  <div class="signIn">
    <sign-in-nav></sign-in-nav>
    <go-sign></go-sign>
    <calendar class="calendar" :listSign="listSign"></calendar>
    <sign-in-rules class="signInRules"></sign-in-rules>
  </div>
</template>
<script>
import calendar from "views/signIn/componentsChildren/calendar.vue";
import signInNav from "views/signIn/componentsChildren/signInNav.vue";
import goSign from "views/signIn/componentsChildren/goSign.vue";
import signInRules from "views/signIn/componentsChildren/signInRules.vue";
import { listsign } from "network/sign.js";
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
    };
  },
  mounted() {
    this.getListsign();
    this.$bus.$on("sign", (item) => {
      // this.listSign.push(item);
      console.log(item);
      // console.log(this.listSign)
      this.getListsign();
    });
  },
  methods: {
    getListsign() {
      listsign(this.$store.state.token).then((res) => {
        console.log(res);
        for (var i = 0; i < res.data.length; i++) {
          var time = res.data[i].signInDate;
          var ymd = time.substr(0, 10);
          var year=ymd.substr(0,4)
          var mounth=ymd.substr(5,2)
          var day = ymd.substr(8, 2);
          var date = new Date();
          var y = date.getFullYear();
          var m = date.getMonth() + 1
          if(year==y&&m==mounth){
            this.listSign.push(parseInt(day));
          }
        }
        // console.log(this.listSign);
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
