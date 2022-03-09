<template>
  <div class="homeSearchView">
    <search-nav-bar @search="search"></search-nav-bar>
    <search-history
      ref="searchHistory"
      :value="value"
      :searchHistoryList="searchHistory"
      @del="del"
      :isShowHis="isShowHis"
    ></search-history>
    <search-guess></search-guess>
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
    searchGuess
  },
  data(){
      return{
          isShowHis:false,
          searchHistory:[],
          value:''
      }
  },
  mounted(){
      this.getHistory()
  },
  methods: {
    search(value) {
      this.value = value;
      setHomeSearchHistory(value);
      this.searchHistory = getHomeSearchHistory();
      this.isShowHis = true;
      console.log(this.searchHistory);
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
</style> 