<template>
  <div class="item">
    <div class="me common" v-if="isMe">
      <div class="content">
        <div class="text text1">{{item.text}}</div>
      </div>
      <img src="@/assets/img/example/头像.jpg" alt="" />
    </div>
    <div class="you common" v-else>
      <img src="@/assets/img/example/头像.jpg" alt="" />
      <div class="content">
        <div class="text text2">{{item.text}}</div>
      </div>
    </div>
    
  </div>
</template>
<script> 
export default{
    props:{
        item:{
            type:Object,
            default(){
                return {}
            }
        }
    },
    data(){
        return{
           isMe:false
        }
    },
    mounted(){
      this.differentiate()
    },
    methods:{
      differentiate(){
        if(this.item.nickname==this.$store.state.nikname){
          // console.log(this.$store.state.nickname)
          this.isMe=true
        }
        else{
          this.isMe=false
        }
      }
    }
}
</script>
<style scoped>
.common {
  display: flex;
  margin-bottom: 10px;

}
.common img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.me {
  /* -webkit-text-align: right; */
  justify-content:flex-end;
     /* -webkit-justify-content: right;
     -moz-justify-content: right;
     -o-justify-content: right; */
}

.you {
  justify-content: left;
}
.content {
  max-width: 70%;
  /* background-color: blue; */
}
.me .name {
  text-align: right;
}
.name {
  font-size: 12px;
  width: 100%;
  height: 12px;
  margin-bottom: 10px;
}
.text {
  margin-right: 10px;
  min-height: 20px;
  max-width: 95%;
  min-width: 20px;
  text-align: justify;
  padding: 10px 10px;
  word-wrap: break-word;
  word-break: normal;
  background-color: rgb(171, 202, 238);
  position: relative;
  border-radius: 8px;
}
.text1 {
  margin-right: 10px;
}
.text2 {
  margin-left: 10px;
}
.text::before {
  content: "";
  width: 12px;
  height: 12px;
  background: rgb(171, 202, 238);
  position: absolute;
  top: 10px;
  transform: rotateZ(45deg);
  border-radius: 2px;
}
.text1::before {
  right: -6px;
}
.text2::before {
  left: -6px;
}

</style>
