<template>
    <div>
        <Navbar activeTab="Stock"/>
        <Categories v-if=categoriesOpen v-bind:categories="categories"
                    v-on:sel-cat="selectCategory"/>
        <ProductList v-if=productOpen v-bind:productList="productList"
                     v-on:add-product="addProduct"/>
        <AddProduct v-on:show-cat="showCategories"/>
        {{value !== "none" ? value : ""}}
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
                    this.allproducts = res.data;
                    this.categories = res.data.categories;
                    this.productList = res.data.productlist;
                });
        },
        methods: {
            selectCategory(id) {
                this.depth++;
                if (this.depth === 1) {
                    this.value = this.categories.filter(cat => cat.id === id);
                    this.categories = this.allproducts.subcategories
                }
                else if (this.depth === 2) {
                    this.categoriesOpen = false;
                    this.productOpen = true;
                    this.value = this.categories.filter(cat => cat.id === id);
                }
            },
            showCategories() {
                if (this.depth > 0) {
                    this.depth = 0;
                    this.value = "none";
                    this.categories = this.allproducts.categories;
                    this.categoriesOpen = false;
                    this.productOpen = false;
                } else {
                    this.categoriesOpen = !this.categoriesOpen;
                    this.productOpen = false;
                }
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
                allproducts: {},
                categories: [],
                productList: []
            }
        }
    }
</script>

<style>
</style>