import { debounce } from "common/utils.js";
import backTop from "components/content/backTop/backTop.vue"
export const itemListenerMixin = {
    data() {
        return {
            itemImgListener: null,
            refresh: null
        }
    },
    mounted() {
        this.refresh = debounce(this.$refs.scroll.refresh, 200)
        this.itemImgListener = () => {
            this.refresh()
        }
        this.$bus.$on('itemImageLoad', this.itemImgListener)
        // console.log("我是mixins中的内容")
    }
}
export const backTopMixins = {
    data() {
        return {
            isShowBackTop: false,
        }

    },
    components: {
        backTop
    },
    methods: {
        backTop() {
            this.$refs.scroll.scroller(0, 0, 500);
        }
    }

}
export const isLoging = {
    data() {
        return {
            logingShow: true,
        }
    },
    activated() {
        this.judgeLoging()
    },
    created() {
        this.$store.dispatch('getUserInfo').then(() => {
            setTimeout(() => {
               this.judgeLoging()
            }, 200);
        })
    },
    methods:{
        judgeLoging(){
            if (this.$store.state.id) {
                this.logingShow = true
            } 
            else {
                this.logingShow = false
            }
        }
    }
}