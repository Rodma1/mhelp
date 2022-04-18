<template>
  <div class="homeSearchView">
    <search-nav-bar @search="search" ref="searchNavBar"></search-nav-bar>
    <search-history
      ref="searchHistory"
      :value="value"
      :searchHistoryList="searchHistory"
      @del="del"
      :isShowHis="isShowHis"
    ></search-history>
    <search-guess></search-guess>
    <router-view
      class="routerView"
      @pullingMore="pullingMore"
    ></router-view>
  </div>
</template>
<script>
import searchNavBar from "components/content/search/componentsChildren/searchNavBar.vue";
import searchHistory from "components/content/search/componentsChildren/searchHistory.vue";
import searchGuess from "components/content/search/componentsChildren/searchGuess.vue";

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
      value:""
    };
  },
  mounted() {
    this.getHistory();
    this.$refs.searchNavBar.$refs.inputBox.focus();
    this.$bus.$on("searchBack", (value) => {
      console.log(value);
      this.$refs.searchNavBar.value = value;
      console.log(this.$refs.searchNavBar.value);
      this.$refs.searchNavBar.$refs.inputBox.focus();
    });
  },
  activated() {
    this.$refs.searchNavBar.$refs.inputBox.focus();
  },
  methods: { 
    search(value) {
      this.value = value;
      setHomeSearchHistory(value);
      this.searchHistory = getHomeSearchHistory();
      this.isShowHis = true;
      // this.getSearchTasks();
      this.$router.push("/home/homeSearch/homeSearchTasks/"+value);
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
    pullingMore() {
      this.getSearchTasks();
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