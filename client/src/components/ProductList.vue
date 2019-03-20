<template>
    <div class="product_container">
        <div v-bind:key="product.id" v-for="product in productList">
            <ProductItem v-bind:product="product" v-on:add-product="$emit('add-product', product.ean)"/>
        </div>
    </div>
</template>

<script>
    import ProductItem from "../components/ProductItem"

    export default {
        name: "ProductList",
        components: {
            ProductItem
        },
        props: ["productList"]
    }
</script>

<style scoped>
    .product_container {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 8% auto;
        width: 80%;
        height: auto;
        position: relative;
        padding-bottom: 7.5rem;
    }
</style>