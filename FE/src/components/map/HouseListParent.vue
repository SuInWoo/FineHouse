<template>
  <div id="houseListParent">
    <div id="searchBar">
      <house-search-bar @changeToggle2="changeView2"></house-search-bar>
    </div>
    <div id="houseDiv">
      <template v-if="c">
        <house-list
          @changeToggle1="changeView1"
          @addr="moveCenter"
          @flag="moveCenterOnly"
        ></house-list>
      </template>
      <template v-else>
        <house-detail @changeToggle3="changeView2"></house-detail>
      </template>
    </div>
    <router-view />
  </div>
</template>

<script>
import HouseList from "@/components/map/HouseList.vue";
import HouseDetail from "@/components/map/HouseDetail.vue";
import HouseSearchBar from "@/components/map/HouseSearchBar.vue";

export default {
  name: "HouseListParent",
  components: { HouseList, HouseDetail, HouseSearchBar },
  data() {
    return {
      c: true,
      check: 0,
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
#houseListParent {
  text-align: center;
}
#searchBar {
  display: inline-block;
}
#houseDiv {
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
