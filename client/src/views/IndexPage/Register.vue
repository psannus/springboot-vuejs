<template>
    <div class="wrapper">
        <div class="register-page">
            <div class="form">
                <form class="login-form">
                    <input type="text" v-model="input.username" placeholder="username">
                    <input type="password" v-model="input.password" placeholder="password">
                    <input type="text" v-model="input.firstName" placeholder="first name">
                    <input type="text" v-model="input.lastName" placeholder="last name">
                    <input type="email" placeholder="email address">
                    <button class="clickable-button" v-on:click="register()">Create</button>
                    <p class="message">Already registered?</p>
                    <router-link class="button-login" to="/login">Sign in</router-link>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    import api from '../../Api';

    export default {
        name: "Register",
        data() {
            return {
                input: {
                    username: "",
                    password: "",
                    firstName: "",
                    lastName: "",
                },
            }
        },
        methods: {
            register() {
                if (this.input.username !== "" && this.input.password !== "") {
                    api.registration({
                        username: this.input.username,
                        password: this.input.password,
                        firstName: this.input.firstName,
                        lastName: this.input.lastName
                    }).then(() => {
                        this.$router.replace('/home')
                    });
                }
            }
        }
    }

</script>

<style scoped>
    .wrapper {
        position: relative;
        height: 100vh;
        margin: auto;
        background: #d7ff78 url("https://s3.amazonaws.com/tt-trackify/assets/pexels-photo-616404.jpeg");
        background-size: cover;
        background-position: top;
        background-repeat: no-repeat;
    }

    .register-page {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 18em;
    }

    .form {
        position: relative;
        z-index: 1;
        background: #FFFFFF;
        max-width: 360px;
        margin: 0 auto 100px;
        padding: 45px;
        text-align: center;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }

    .form input {
        font-family: "Roboto", sans-serif;
        outline: 0;
        background: #f2f2f2;
        width: 100%;
        border: 0;
        margin: 0 0 30px;
        padding: 15px;
        box-sizing: border-box;
        font-size: 14px;
    }

    .form .message {
        margin: 15px 0 0;
        color: #b3b3b3;
        font-size: 12px;
    }

    .button-login {
        color: #4CAF50;
        text-decoration: none;
        font-size: 12px;
    }

    .clickable-button {
        background-color: #4CAF50;
    }

    .clickable-button {
        background-color: #4CAF50;
        text-decoration: none;
        padding: 10px 30px;
        color: white;
        margin-top: 20px;
    }


</style>