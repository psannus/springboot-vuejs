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
    h1, h2, h4 {
        margin: 10px;
        font-size: 14px;
    }
    .product-box {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 50em;
        margin-left: auto;
        margin-right: auto;
    }

    .circle {
        background-color: #c8ccd2;
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
        align-self: flex-start;
        width: 9em;
        height: 9em;
    }

    .info-display {
        display: flex;
        width: 25em;
        flex-direction: column;
    }
</style>