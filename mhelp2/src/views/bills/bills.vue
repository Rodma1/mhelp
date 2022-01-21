<template>
    <div class="bills">
        <bills-nav></bills-nav>
    </div>
</template>
<script>
import billsNav from "views/bills/componentsChildren/billsNav.vue"

export default{
    components:{
        billsNav
       
    }
}
</script>
<style scoped>
.bills{
    height: 100vh;
    position: relative;
    z-index:10;
    background: white;
}
</style>