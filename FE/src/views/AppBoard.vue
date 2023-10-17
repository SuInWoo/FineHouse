<template>
  <div>
    <board-list></board-list>
  </div>
</template>

<script>
import BoardList from "@/components/board/BoardList.vue";

export default {
  name: "AppBoard",
  components: {
    BoardList,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
