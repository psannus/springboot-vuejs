<template>
    <button v-on:click="$emit('sel-cat', category.id)" class="product">{{category.name}}</button>
</template>

<script>
    export default {
        name: "CategoryItem",
        props: ["category"]
    }
</script>

<style scoped>
    .product {
        font-size: 1.5em;
        background: #4285F4;
        margin: 0.5em;
        border-radius: 2px;
        border: 0;
        transition: .2s ease-out;
        color: #fff;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    }

    .product:hover {
        color: #fff;
        box-shadow: 0 5px 11px 0 rgba(0, 0, 0, 0.18), 0 4px 15px 0 rgba(0, 0, 0, 0.15);
    }

    .product:active, .btn:focus, .btn.active {
        outline: 0;
        color: #fff;
    }
</style>