<template>
    <div class="product-box">
        <img class="product-image" :src="basketItem.image" alt="NONE">
        <div class="info-display">
            <h1>{{basketItem.name}}</h1>
            <h2>Amount: {{basketItem.amount}}</h2>
            <h2>Shelf: {{basketItem.shelf}}</h2>
            <h2>{{basketItem.price}}€</h2>
            <h4>EAN: {{basketItem.ean}}</h4>
        </div>
        <button v-on:click="$emit('del-product')" class="circle">-</button>
    </div>
</template>

<script>
    export default {
        name: "BasketItem",
        props: ["basketItem"]
    }
</script>

<style scoped>
    h1, h2, h4 {
        margin: 10px;
    }

    h1 {
        font-size: 1em;
        margin-top: 1em;
        margin-bottom: 0;
    }

    h2 {
        font-weight: normal;
        font-size: 0.75em;
        margin-bottom: 0;
    }

    h4 {
        margin-top: 15px;
        font-weight: normal;
        font-style: italic;
        font-size: 0.50em;
    }

    .product-box {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 50em;
        border: 1px solid #ddd;
        border-radius: 5px;
        margin: 0.5em auto;
    }

    .circle {
        background-color: #ddd;
        border-radius: 999em;
        width: 56px;
        height: 56px;
        line-height: 1;
        font-size: 36px;
        position: relative;
        cursor: pointer;
        border: 0;
    }

    .circle:hover {
        background-color: #2CC3B5;
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