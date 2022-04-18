<template>
  <div class="checkButton" :class="{ checked: isChecked }">
    <img src="@/assets/img/cart/tick.svg" alt="" />
  </div>
</template>
<script>
export default {
  props: {
    isChecked: {
      type: Boolean,
      default() {
        return false;
      },
    },
  },
  methods: {},
};
</script>

<style scoped>
.checkButton {
  border: 2px solid #aaa;
  border-radius: 50%;
  background-color: white;
}
.checked {
  background-color: var(--color-tint);
  border-radius: 50%;
  border: 2px solid var(--color-tint);
}
</style>