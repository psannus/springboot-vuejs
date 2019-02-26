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
        justify-content: center;
        align-items: center;
        margin: 2.3em auto;
        width: 80%;
        height: 500px;
        position: relative;
    }
</style>