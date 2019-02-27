<template>
    <div class="stock_container">
        <div v-bind:key="category.id" v-for="category in categories">
            <CategoryItem v-bind:category="category" v-on:sel-cat="$emit('sel-cat', category.id)"/>
        </div>
    </div>
</template>

<script>
    import CategoryItem from '../components/CategoryItem'

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
        justify-content: center;
        align-items: center;
        margin: 2.3em auto;
        width: 100%;
        height: 500px;
        position: relative;
    }

    .is-open {
        display: none;
    }
</style>