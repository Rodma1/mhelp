<template>
    <div class="information">
        <items @click="goChat"></items>
        <items></items>
        <items></items>
        <items></items>
    </div>
</template>
<script>
import Items from 'views/message/componentsChildren/informationItem.vue'
export default {
    components:{
        Items
    },
    methods:{
        goChat(){
            this.$router.push('/chat');
        },
    }
}
</script>

<style scoped>

</style>