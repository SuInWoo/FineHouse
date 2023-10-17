<template>
  <b-tr>
    <b-td>{{ bno }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardDetail', params: { bno: bno } }"> {{ title }}</router-link>
    </b-th>
    <b-td>{{ writer }}</b-td>
    <b-td>{{ registerTime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    bno: Number,
    writer: String,
    title: String,
    content: String,
    registerTime: String,
  },
  data() {
    return {
      date: "",
    };
  },
  filters: {
    dateFormat(registerTime) {
      return moment(new Date(registerTime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
