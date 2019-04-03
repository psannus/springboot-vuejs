<template>
    <div class="wrapper">
        <div>
            <img class="hero-image pb-4" src="../assets/logo.png"/>
        </div>
        <div class="hero-text">
            <h3>Track your everyday household items and their quantities</h3>
            <RouterLink class="header-router-link" to="/login">Start tracking now</RouterLink>
        </div>
        <div class="signin-signup">
            <RouterLink class="LandingButton" to="/login">Sign In</RouterLink>
            <RouterLink class="LandingButton" to="/register">Register</RouterLink>
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

    .LandingButton {
        margin: 10px;
        -moz-box-shadow: inset 0 1px 0 0 #a4e271;
        box-shadow: inset 0 1px 0 0 #a4e271;
        background: -moz-linear-gradient(top, #4caf4f 5%, #1e9620 100%);
        background: -webkit-linear-gradient(top, #4caf4f 5%, #1e9620 100%);
        background: -o-linear-gradient(top, #4caf4f 5%, #1e9620 100%);
        background: -ms-linear-gradient(top, #4caf4f 5%, #1e9620 100%);
        background: linear-gradient(to bottom, #4caf4f 5%, #1e9620 100%);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4caf4f', endColorstr='#1e9620', GradientType=0);
        background-color: #4caf4f;
        -moz-border-radius: 6px;
        -webkit-border-radius: 6px;
        border-radius: 6px;
        display: inline-block;
        cursor: pointer;
        color: #000000;
        font-size: 15px;
        padding: 6px 24px;
        text-decoration: none;
    }

    .LandingButton:hover {
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1e9620), color-stop(1, #4caf4f));
        background: -moz-linear-gradient(top, #1e9620 5%, #4caf4f 100%);
        background: -webkit-linear-gradient(top, #1e9620 5%, #4caf4f 100%);
        background: -o-linear-gradient(top, #1e9620 5%, #4caf4f 100%);
        background: -ms-linear-gradient(top, #1e9620 5%, #4caf4f 100%);
        background: linear-gradient(to bottom, #1e9620 5%, #4caf4f 100%);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e9620', endColorstr='#4caf4f', GradientType=0);
        background-color: #1e9620;
    }

    .LandingButton:active {
        position: relative;
        top: 1px;
    }


    .signin-signup {
        position: absolute;
        top: 1%;
        right: 1%;

    }
</style>