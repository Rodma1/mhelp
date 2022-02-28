<template>
    <div class="bills">
        <bills-nav></bills-nav>
        <bill-list class="billList"></bill-list>
    </div>
</template>
<script>
import billsNav from "views/bills/componentsChildren/billsNav.vue"
import billList from "views/bills/componentsChildren/billList.vue"
export default{
    components:{
        billsNav,
        billList
    }
}
</script>
<style scoped>
.bills{
    height: 100vh;
    position: relative;
    z-index:10;
    
}
.billList{
    position: absolute;
    top: 44px;
    bottom: 0px;
    left: 0px;
    right: 0px;
    background: #f3f3f3;
}
</style>