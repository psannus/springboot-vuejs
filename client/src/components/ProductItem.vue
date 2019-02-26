<template>
    <div class="product-box">
        <img class="product-image" :src="product.image" alt="NONE">
        <div class="info-display">
            <h1>{{product.name}}</h1>
            <h2>Amount: {{product.amount}}</h2>
            <h2>Shelf: {{product.shelf}}</h2>
            <h2>{{product.price}}€</h2>
            <h4>EAN: {{product.ean}}</h4>
        </div>
        <button v-on:click="$emit('add-product')" class="circle">+</button>
    </div>
</template>

<script>
    export default {
        name: "ProductItem",
        props: ["product"]
    }
</script>

<style scoped>
    .product-box {
        display: flex;
        justify-content: space-around;
    }

    .circle {
        background-color: #d23f31;
        border-radius: 999em;
        width: 56px;
        height: 56px;
        line-height: 1;
        font-size: 36px;
        position: relative;
        cursor: pointer;
        border: 0;
    }

    .product-image {
        object-fit: contain;
        width: 3em;
        height: 3em;
    }

    .info-display {
        display: flex;
        flex-direction: column;
    }
</style>