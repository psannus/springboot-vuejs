<template>
    <div class="stock_container">
        <div v-bind:key="category.id" v-for="category in categories">
            <CategoryItem v-bind:category="category" v-on:sel-cat="$emit('sel-cat', category)"/>
        </div>
    </div>
</template>

<script>
    import CategoryItem from './CategoryItem'

    export default {
        name: "Categories",
        components: {
            CategoryItem
        },
        props: ["categories"]
    }
</script>

<style scoped>
    .stock_container {
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 500px;
        position: relative;
        margin-top: 8%;
    }

    .stock_container > * {
        flex: 1 0 0;
    }

    .is-open {
        display: none;
    }
</style>