<template>
  <div class="homeSearchView">
    <search-nav-bar @search="search" ref="searchNavBar"></search-nav-bar>
    <search-history
      ref="searchHistory"
      :value="data.value"
      :searchHistoryList="searchHistory"
      @del="del"
      :isShowHis="isShowHis"
    ></search-history>
    <search-guess></search-guess>
    <router-view class="routerView" :searchTaskList="data.searchTaskList" :value="data.value"></router-view>
  </div>
</template>
<script>
import searchNavBar from "components/content/search/componentsChildren/searchNavBar.vue";
import searchHistory from "components/content/search/componentsChildren/searchHistory.vue";
import searchGuess from "components/content/search/componentsChildren/searchGuess.vue";
import { searchTask } from "network/task.js";
import {
  setHomeSearchHistory,
  getHomeSearchHistory,
  removeHomeSearchHistory,
} from "network/searchHistory.js";
export default {
  components: {
    searchNavBar,
    searchHistory,
    searchGuess,
  },
  data() {
    return {
      isShowHis: false,
      searchHistory: [],
      data: {
        page: 1,
        pageSize: 10,
        value: "",
        schoolId: 1,
        searchTaskList:[]
      },
    };
  },
  mounted() {
    this.getHistory();
    this.$refs.searchNavBar.$refs.inputBox.focus()
    this.$bus.$on("searchBack",(value)=>{
     console.log(value)
     this.$refs.searchNavBar.value=value
     console.log(this.$refs.searchNavBar.value)
     this.$refs.searchNavBar.$refs.inputBox.focus()
   })
    
  },
  activated(){
   this.$refs.searchNavBar.$refs.inputBox.focus()
  //  this.$bus.$on("searchBack",(value)=>{
  //    console.log(value)
  //    this.$refs.searchNavBar.value=value
  //    console.log(this.$refs.searchNavBar.value)
  //  })
  },
  methods: {
    search(value) {
      this.data.value = value;
      setHomeSearchHistory(value);
      this.searchHistory = getHomeSearchHistory();
      this.isShowHis = true;
      searchTask(this.data).then((res) => {
        console.log(res);
        this.data.searchTaskList=res.data;
        this.$router.push("/home/homeSearch/homeSearchTasks");
      });
    },
    del() {
      this.searchHistory = [];
      this.isShowHis = false;
      removeHomeSearchHistory();
    },
    getHistory() {
      this.searchHistory = getHomeSearchHistory();
      if (this.searchHistory.length !== 0) {
        this.isShowHis = true;
      }
    },
  },
};
</script> 

<style scoped>
.homeSearchView {
  background: white;
  height: 100vh;
}
.routerView {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 11;
}
</style> 