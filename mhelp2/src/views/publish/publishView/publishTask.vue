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
        summary: "",
        remark: "",
        tags: "",
        category: "",
        price: "",
        images: "",
        school:this.$store.state.school
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
      this.params.summary = this.$refs.fillDetails.params.summary;
      this.params.images=this.$refs.fillDetails.params.images
    },
    publish() {
      this.params.remark = this.$refs.publishToast.params.remark;
      this.params.tags = this.$refs.publishToast.currentTags;
      this.params.category = this.$refs.publishToast.currentCategory;
      this.params.price = this.$refs.publishToast.params.price;
      console.log(this.$refs.publishToast.params.category)
      console.log(this.params)
      publishTask(this.$store.state.token, this.params).then((res) => {
        console.log(res);
        this.$refs.fillDetails.params.title = "";
        this.$refs.fillDetails.params.summary = "";
        this.$refs.fillDetails.fileList=[]
      });
      this.isActive = !this.isActive;
      this.$router.push('/')
    },
  },
};
</script>
