<template>
    <div class="wrapper">
        <div>
            <img class="hero-image" src="../assets/logo.png"/>
        </div>
        <div class="hero-text">
            <h3>Track your everyday household items and their quantities</h3>
            <RouterLink class="header-router-link" to="/login">Start tracking now</RouterLink>
        </div>
        <div class="signin-signup">
            <RouterLink class="header-login" to="/login">Sign In</RouterLink>
            <RouterLink class="header-register" to="/register">Register</RouterLink>
        </div>
    </div>
</template>

<script>

    export default {
        name: "LandingPage",
    }

</script>

<style scoped>
    .wrapper {
        height: 100vh;
        margin: auto;
        background: #d7ff78 url("../assets/pexels-photo-616404.jpeg");
        background-size: cover;
        background-position: top;
        background-repeat: no-repeat;

    }

    .hero-image {
        text-align: center;
        position: absolute;
        top: 45%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 20em;
        height: 11.2em;
    }

    .hero-text {
        text-align: center;
        position: absolute;
        padding-top: 7%;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        color: #000000;
    }

    .header-router-link {
        font-size: 2rem;
        text-decoration: none;
        background-color: #4CAF50;
        color: black;
        padding: 5px 2em 5px 2em;
        border-radius: 25px;
    }

    .signin-signup {
        position: absolute;
        top: 1%;
        right: 1%;
    }

    .header-login {
        text-decoration: none;
        padding-right: 1em;
        color: black;
    }

    .header-register {
        text-decoration: none;
        color: black;
    }
</style>