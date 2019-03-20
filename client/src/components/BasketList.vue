<template>
    <div class="product_container">
        <div v-bind:key="basketItem.id" v-for="basketItem in basketList">
            <BasketItem v-bind:basketItem="basketItem" v-on:del-product="$emit('del-product', basketItem.id)"/>
        </div>
        <button v-if=showButton class="save-button" v-on:click="$emit('save-list')">Save Products</button>
    </div>
</template>

<script>
    import BasketItem from "../components/BasketItem"

    export default {
        name: "BasketList",
        components: {
            BasketItem
        },
        props: ["basketList", "showButton"],
    }
</script>

<style scoped>
    .product_container {
        display: flex;
        flex-direction: column;
        align-items: center;
        height: auto;
        position: relative;
        padding-bottom: 7.5rem;
        margin-top: 8%;
    }

    .save-button {
        display: flex;
        align-items: center;
        justify-content: center;
        min-width: 10em;
        min-height: 2em;
        margin: 0.25em;
        font-size: 1.5em;
        background: #ddd;
        border-radius: 2px;
        border: 0;
        text-align: center;
        transition: .2s ease-out;
        color: #fff;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    }

    .save-button:hover {
        background-color: #2CC3B5;
        box-shadow: 0 5px 11px 0 rgba(0, 0, 0, 0.18), 0 4px 15px 0 rgba(0, 0, 0, 0.15);
    }

    .is-open {
        display: none;
    }
</style>