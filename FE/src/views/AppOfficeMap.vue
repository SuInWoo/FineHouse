<template>
  <div>
    <office-map class="float: left" :addr="addr" :flag="flag"></office-map>
    <office-list-parent class="float: right" @addr="setAddr" @flag="setFlag"></office-list-parent>
  </div>
</template>

<script>
import OfficeListParent from "@/components/omap/OfficeListParent.vue";
import OfficeMap from "@/components/omap/OfficeMap.vue";

export default {
  name: "AppOfficeMap",
  components: {
    OfficeListParent,
    OfficeMap,
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
  width: 65%;
  float: left;
  box-sizing: border-box;
}
div.right {
  width: 35%;
  float: right;
  box-sizing: border-box;
}
</style>
