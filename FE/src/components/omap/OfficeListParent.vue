<template>
  <div id="officeListParent">
    <div id="searchBar">
      <office-search-bar @changeToggle2="changeView2"></office-search-bar>
    </div>
    <div id="officeDiv">
      <template v-if="c">
        <office-list
          @changeToggle1="changeView1"
          @addr="moveCenter"
          @flag="moveCenterOnly"
        ></office-list>
      </template>
      <template v-else>
        <office-detail @changeToggle3="changeView2"></office-detail>
      </template>
    </div>
    <router-view />
  </div>
</template>

<script>
import OfficeList from "@/components/omap/OfficeList.vue";
import OfficeDetail from "@/components/omap/OfficeDetail.vue";
import OfficeSearchBar from "@/components/omap/OfficeSearchBar.vue";

export default {
  name: "OfficeListParent",
  components: { OfficeList, OfficeDetail, OfficeSearchBar },
  data() {
    return {
      c: true,
    };
  },
  created() {},
  methods: {
    changeView1(value) {
      this.c = value;
    },
    changeView2(value) {
      this.c = value;
    },
    moveCenter(addr) {
      this.$emit("addr", addr);
      // console.log("list 부모 addr", addr);
    },
    moveCenterOnly(flag) {
      this.$emit("flag", flag);
      // console.log("list 부모 addr", addr);
    },
  },
};
</script>
<style scoped>
#officeListParent {
  text-align: center;
}
#searchBar {
  display: inline-block;
}
#officeDiv {
  overflow: auto;
  height: 900px;
}
template {
  margin: 0;
}
div {
  margin: 0;
}
.container {
  /* // width: 100vw; */
  height: 80vh;
}
</style>
