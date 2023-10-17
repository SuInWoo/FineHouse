<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <h3>회원가입</h3>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left" @submit="onSubmit">
              <b-form-group label="아이디" label-for="userid">
                <b-form-input id="userId" v-model="user.userid" required placeholder="아이디 입력">
                </b-form-input>
              </b-form-group>

              <b-form-group label="비밀번호" label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userPwd"
                  v-model="user.userpwd"
                  required
                  placeholder="비밀번호 입력"
                ></b-form-input>
              </b-form-group>

              <b-form-group label="이름" label-for="name">
                <b-form-input
                  type="text"
                  id="name"
                  v-model="user.name"
                  required
                  placeholder="이름 입력"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일" label-for="email">
                <b-form-input
                  type="text"
                  id="email"
                  v-model="user.email"
                  required
                  placeholder="이메일 입력"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="전화번호" label-for="phone">
                <b-form-input
                  type="text"
                  id="phone"
                  v-model="user.phone"
                  required
                  placeholder="전화번호 입력"
                ></b-form-input>
              </b-form-group>

              <b-form-group label="주소" label-for="addr">
                <b-form-input
                  type="text"
                  id="addr"
                  v-model="user.addr"
                  required
                  placeholder="주소 입력"
                ></b-form-input>
              </b-form-group>

              <b-button type="submit" variant="success" class="m-1">회원가입 완료</b-button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { join } from "@/api/member";

export default {
  name: "MemberSignup",
  components: {},
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
        name: "",
        email: "",
        addr: "",
        phone: "",
        admin: 0,
      },
    };
  },
  created() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.registUser();
    },
    registUser() {
      join(
        {
          userId: this.user.userid,
          userPwd: this.user.userpwd,
          name: this.user.name,
          email: this.user.email,
          addr: this.user.addr,
          phone: this.user.phone,
          admin: 0,
        },
        (response) => {
          console.log(response);
          let msg = "회원가입 처리시 문제가 발생했습니다.";
          if (response.status === 200) {
            msg = "회원가입이 완료되었습니다.";
            console.log("성공");
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped></style>
