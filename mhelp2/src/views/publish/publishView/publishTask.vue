<template>
  <div class="publishTask">
    <fill-details @isShow="isShow" ref="fillDetails"></fill-details>
    <publish-toast
      v-if="isActive"
      @change="change"
      @publish="publish"
      ref="publishToast"
    ></publish-toast>
  </div>
</template>
<script>
import fillDetails from "views/publish/componentsChildren/fillDetails.vue";
import publishToast from "views/publish/componentsChildren/publishToast.vue";
import { publishTask } from "network/task.js";
export default {
  components: {
    fillDetails,
    publishToast,
  },
  data() {
    return {
      isActive: false,
      params: {
        title: "",
        content: "",
        remark: "",
        tags: "",
        category: "",
        price: "",
      },
    };
  },
  created() {},
  methods: {
    change() {
      this.isActive = !this.isActive;
    },
    isShow() {
      this.isActive = !this.isActive;
      this.params.title = this.$refs.fillDetails.params.title;
      this.params.content = this.$refs.fillDetails.params.content;
    },
    publish() {
      this.params.remark = this.$refs.publishToast.params.remark;
      this.params.tags = this.$refs.publishToast.params.tags;
      this.params.category = this.$refs.publishToast.params.category;
      this.params.price = this.$refs.publishToast.params.price;

      publishTask(this.$store.state.token, this.params).then((res) => {
        console.log(res);
        this.$refs.fillDetails.params.title = "";
        this.$refs.fillDetails.params.content = "";
      });

      this.isActive = !this.isActive;
      this.$router.push('/')
    },
  },
};
</script>
