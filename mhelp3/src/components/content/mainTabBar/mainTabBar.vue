<template>
    <div class="mainTabBar">
        <tab-bar>
            <tab-bar-item path="/home" activeColor="#1296db">
                <img src="@/assets/img/tabBar/home.png" alt="" slot="item-icon">
                <img src="@/assets/img/tabBar/home_active.png" alt="" slot="item-icon-active">
                <div slot="item-text">动态</div>
            </tab-bar-item>
            <tab-bar-item path="/publish" activeColor="#1296db"> 
                <img src="@/assets/img/tabBar/task.png" alt="" slot="item-icon">
                <img src="@/assets/img/tabBar/task_active.png" alt="" slot="item-icon-active">
                <div slot="item-text">写任务</div>
            </tab-bar-item>
            <tab-bar-item path='/message' activeColor="#1296db">
                <img src="@/assets/img/tabBar/message.png" alt="" slot="item-icon">
                <img src="@/assets/img/tabBar/message_active.png" alt="" slot="item-icon-active">
                <div slot="item-text">消息</div>
            </tab-bar-item>
            <tab-bar-item path="/my" activeColor="#1296db">
                <img src="@/assets/img/tabBar/my.png" alt="" slot="item-icon">
                <img src="@/assets/img/tabBar/my_active.png" alt="" slot="item-icon-active">
                <div slot="item-text">我的</div>
            </tab-bar-item>
        </tab-bar>
    </div>
</template>
<script>
import tabBar from "components/common/tabbar/tabbar.vue"
import TabBarItem from 'components/common/tabbar/tabbarItem.vue'
export default{
    components:{
        tabBar,
        TabBarItem
    }
}
</script>
<style scoped>
    .mainTabBar{
        position: relative;
        z-index: 9;
    }
</style>
