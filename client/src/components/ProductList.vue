<template>
    <div class="product_container">
        <div v-bind:key="product.id" v-for="product in productList">
            <ProductItem v-bind:product="product" v-on:add-product="$emit('add-product', product.id)"/>
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
        margin: 0.5em auto;
        width: 100%;
        height: 30em;
        position: relative;
        overflow: auto;
    }
</style>