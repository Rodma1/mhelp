<template>
    <div class="signIn">
        <sign-in-nav></sign-in-nav>
        <go-sign></go-sign>
        <calendar class="calendar"></calendar>
        <sign-in-rules class="signInRules"></sign-in-rules>
    </div>
</template>
<script>
import calendar from "views/signIn/componentsChildren/calendar.vue"
import signInNav from "views/signIn/componentsChildren/signInNav.vue"
import goSign from "views/signIn/componentsChildren/goSign.vue"
import signInRules from "views/signIn/componentsChildren/signInRules.vue"
export default{
    components:{
        calendar,
        signInNav,
        goSign,
        signInRules

    }
}
</script>
<style scoped>
.signIn{
    height: 100vh;
    position: relative;
    z-index: 10;
    background: #f6f6f6;
    position: relative;
}
.calendar{
    position: absolute;
    top: 150px;
}
.signInRules{
    position: absolute;
    top: 400px;
}
</style>
