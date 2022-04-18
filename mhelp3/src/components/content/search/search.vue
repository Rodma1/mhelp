<template>
    <div class="search">
        <search-nav-bar @search="search"></search-nav-bar>
        <search-history :value="value" ref="searchHistory" ></search-history>
        <search-guess></search-guess>
    </div>
</template>
<script>
import searchNavBar from "components/content/search/componentsChildren/searchNavBar.vue"
import searchHistory from "components/content/search/componentsChildren/searchHistory.vue"
import searchGuess from "components/content/search/componentsChildren/searchGuess.vue"

export default{
    components:{
        searchNavBar,
        searchHistory,
        searchGuess,

    },
    data(){
        return{
            value:""
        }
    },
    props:{
    },
    methods:{
        search(value){
            this.value=value;
            this.$refs.searchHistory.searchHistoryList.push(this.value)
        },
    }
}
</script>

<style scoped>
.search{
    height: 100vh;
    background: white;
    position: relative;
    z-index: 10;
}
</style>
