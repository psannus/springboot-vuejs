<template>
    <div v-on:click="$emit('show-basket')" class="icon-cart" style="clear: right; float: right">
        <div class="cart-line-1"></div>
        <div class="cart-line-2"></div>
        <div class="cart-line-3"></div>
        <div class="cart-wheel"></div>
    </div>
</template>

<script>
    export default {
        name: "Basket",
    }
</script>

<style scoped>

    .icon-cart {
        width: 55px;
        height: 55px;
        overflow: hidden;
        top: 10%;
        right: 2%;
        position: fixed;
        --main-color: #ddd;

    }

    .icon-cart:hover {
        --main-color: #2CC3B5;
    }

    .icon-cart:active {
        --main-color: #2CC3B5;
    }

    .icon-cart .cart-line-1 {
        width: 15%;
        height: 7%;
        position: absolute;
        left: 8%;
        top: 25%;
        -webkit-transform: rotate(5deg);
        -moz-transform: rotate(5deg);
        -ms-transform: rotate(5deg);
        transform: rotate(5deg);
        background-color: var(--main-color);
        border-bottom-left-radius: 35%;
    }

    .icon-cart .cart-line-2 {
        width: 35%;
        height: 7%;
        position: absolute;
        left: 6%;
        top: 40%;
        -webkit-transform: rotate(80deg);
        -moz-transform: rotate(80deg);
        -ms-transform: rotate(80deg);
        transform: rotate(80deg);
        background-color: var(--main-color);
    }

    .icon-cart .cart-line-2:before {
        content: "";
        width: 120%;
        height: 100%;
        position: absolute;
        left: 45%;
        top: -280%;
        -webkit-transform: rotate(-80deg);
        -moz-transform: rotate(-80deg);
        -ms-transform: rotate(-80deg);
        transform: rotate(-80deg);
        background-color: var(--main-color);
    }

    .icon-cart .cart-line-2:after {
        content: "";
        width: 70%;
        height: 100%;
        position: absolute;
        left: 59%;
        top: -670%;
        background-color: var(--main-color);
        -webkit-transform: rotate(40deg);
        -moz-transform: rotate(40deg);
        -ms-transform: rotate(40deg);
        transform: rotate(40deg);
        border-top-left-radius: 50%;
        border-bottom-left-radius: 25%;
    }

    .icon-cart .cart-line-3 {
        width: 30%;
        height: 7%;
        position: absolute;
        left: 33%;
        top: 45%;
        background-color: var(--main-color);
    }

    .icon-cart .cart-line-3:after {
        content: "";
        width: 124%;
        height: 100%;
        position: absolute;
        top: -150%;
        left: -5%;
        background-color: var(--main-color);
    }

    .icon-cart .cart-wheel {
        width: 12%;
        height: 12%;
        background-color: var(--main-color);
        border-radius: 100%;
        position: absolute;
        left: 28%;
        bottom: 20%;
    }

    .icon-cart .cart-wheel:after {
        content: "";
        width: 100%;
        height: 100%;
        background-color: var(--main-color);
        border-radius: 100%;
        position: absolute;
        left: 200%;
        bottom: 0;
    }

    a {
        position: absolute;
        bottom: 12px;
        right: 20px;
    }
</style>