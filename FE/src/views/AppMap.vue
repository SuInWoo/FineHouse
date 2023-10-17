<template>
  <div>
    <house-map class="float: left" :addr="addr" :flag="flag"></house-map>
    <house-list-parent class="float: right" @addr="setAddr" @flag="setFlag"></house-list-parent>
  </div>
</template>

<script>
import HouseListParent from "@/components/map/HouseListParent.vue";
import HouseMap from "@/components/map/HouseMap.vue";

export default {
  name: "AppMap",
  components: {
    HouseListParent,
    HouseMap,
  },
  data() {
    return {
      addr: "",
      flag: true,
    };
  },
  created() {},
  methods: {
    setAddr(addr) {
      this.addr = addr;
      //console.log("appmap", addr);
    },
    setFlag(flag) {
      this.flag = flag;
    },
  },
};
</script>

<style scoped>
div {
  width: 100%;
  height: 100vh;
  /* border: 1px solid #000; */
}
div.left {
  width: 67%;
  float: left;
  box-sizing: border-box;
}
div.right {
  width: 33%;
  float: right;
  box-sizing: border-box;
}
</style>
