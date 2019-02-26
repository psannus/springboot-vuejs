<template>
    <div>
        <Navbar activeTab="Stock"/>
        <Categories v-bind:class="{'is-open':categoriesOpen}" v-bind:categories="categories"
                    v-on:sel-cat="selectCategory"/>
        <ProductList v-bind:class="{'is-open':productOpen}" v-bind:productList="productList"
                     v-on:add-product="addProduct"/>
        <AddProduct v-on:show-cat="showCategories"/>
        {{value}}
    </div>
</template>

<script>
    import Navbar from "../components/Navbar";
    import Categories from "../components/Categories";
    import AddProduct from "../components/AddProduct"
    import ProductList from "../components/ProductList";
    import axios from "axios";

    export default {
        name: 'Stock',
        components: {
            ProductList,
            Categories,
            AddProduct,
            Navbar,
        },
        mounted() {
            axios.get('http://localhost:9000/products-mock')
                .then(res => {
                    this.categories = res.data.categories;
                    this.productList = res.data.productlist;
                });
        },
        methods: {
            selectCategory(id) {
                this.depth++;
                if (this.depth === 2) {
                    this.categoriesOpen = true;
                    this.productOpen = true;
                    this.categories = [];
                    this.value = this.categories.filter(cat => cat.id === id);
                }
            },
            showCategories() {
                this.categoriesOpen = !this.categoriesOpen;
                this.productOpen = false;
                this.depth = 0;
            },
            addProduct(id) {
                this.value = this.productList.filter(product => product.id === id);
            }
        },
        data() {
            return {
                depth: 0,
                value: "none",
                productOpen: false,
                categoriesOpen: false,
                categories: [],
                productList: []
            }
        }
    }
</script>

<style>
</style>