<template>
  <div class="taskListSearchView">
    <search-nav-bar @search="search"></search-nav-bar>
    <search-history
      ref="searchHistory"
      :value="value"
      :searchHistoryList="searchHistory"
      @del="del"
      :isShowHis="isShowHis"
    ></search-history>
  </div>
</template>
<script>
import searchNavBar from "components/content/search/componentsChildren/searchNavBar.vue";
import searchHistory from "components/content/search/componentsChildren/searchHistory.vue";
import {
  setTaskListSearchHistory,
  getTaskListSearchHistory,
  removeTaskListSearchHistory,
} from "network/searchHistory.js";
export default {
  components: {
    searchNavBar,
    searchHistory,
  },
  data() {
    return {
      isShowHis: false,
      searchHistory: [],
      value:''
    };
  },
  methods: {
    search(value) {
      this.value = value;
      setTaskListSearchHistory(value);
      this.searchHistory = getTaskListSearchHistory();
      this.isShowHis = true;
      console.log(this.searchHistory);
    },
    del() {
      this.searchHistory = [];
      this.isShowHis = false;
      removeTaskListSearchHistory();
    },
    getHistory() {
      this.searchHistory = getTaskListSearchHistory();
      if (this.searchHistory.length !== 0) {
        this.isShowHis = true;
      }
    },
  },
};
</script>

<style scoped>
.taskListSearchView {
  background: white;
  height: 100vh;
}
</style>
