<template>
  <div>
    <member-sign-in></member-sign-in>
  </div>
</template>

<script>
import MemberSignIn from "../components/user/MemberSignIn.vue";

export default {
  name: "AppLogin",
  components: { MemberSignIn },
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
