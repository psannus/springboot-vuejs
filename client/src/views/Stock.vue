<template>
    <div>
        <Navbar activeTab="Stock"/>
        <Categories v-bind:class="{'is-open':open}" v-bind:categories="categories" v-on:sel-cat="selectCategory"/>
        <AddProduct v-on:show-cat="addProduct"/>
        {{value}}
    </div>
</template>

<script>
    import Navbar from "../components/Navbar";
    import Categories from "../components/Categories";
    import AddProduct from "../components/AddProduct"
    import axios from "axios";

    export default {
        name: 'Stock',
        components: {
            Categories,
            AddProduct,
            Navbar,
        },
        methods: {
            selectCategory(id) {
                this.value = this.categories.filter(cat => cat.id === id)
            },
            addProduct() {
                axios.get('http://my-json-server.typicode.com/psannus/springboot-vuejs/categories')
                    .then(res => this.categories = res.data);
                this.open = !this.open;
            }
        },
        data() {
            return {
                value: "none",
                open: "false",
                categories: []
            }
        }
    }
</script>

<style>
</style>