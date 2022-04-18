<template>
  <div class="schoolSearchView">
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
  setSchoolSearchHistory,
  getSchoolSearchHistory,
  removeSchoolSearchHistory,
} from "network/searchHistory.js";
export default {
  components: {
    searchNavBar,
    searchHistory,
  },
  data() {
    return {
      searchHistory: [],
      value: "",
      isShowHis: false,
    };
  },
  mounted() {
    this.getHistory()
  },
  methods: {
    search(value) {
      this.value = value;
      //   this.searchHistory.push(value);
      setSchoolSearchHistory(value);
      this.searchHistory = getSchoolSearchHistory();
      this.isShowHis = true;
      console.log(this.searchHistory);
      this.$router.push("/school");
      this.$bus.$emit("scroolToSchool", value);
    },
    del() {
      this.searchHistory = [];
      this.isShowHis = false;
      removeSchoolSearchHistory();
    },
    getHistory() {
      this.searchHistory = getSchoolSearchHistory();
      if (this.searchHistory.length !== 0) {
        this.isShowHis = true;
      }
    },
  },
};
</script>
<style scoped>
.schoolSearchView {
  background: white;
  height: 100vh;
}
</style>