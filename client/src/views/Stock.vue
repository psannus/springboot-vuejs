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
        methods: {
            selectCategory(id) {
                this.depth++;
                if (this.depth === 1) {
                    //get subcategories of previous choice
                    axios.get('http://my-json-server.typicode.com/psannus/springboot-vuejs/subcategories')
                        .then(res => this.categories = res.data)
                }
                if (this.depth === 2) {
                    //make final choice and load product list
                    axios.get('http://my-json-server.typicode.com/psannus/springboot-vuejs/productList')
                        .then(res => this.productList = res.data);
                    this.categoriesOpen = false;
                    this.productOpen = true;
                    this.value = this.categories.filter(cat => cat.id === id);
                }
            },
            showCategories() {
                axios.get('http://my-json-server.typicode.com/psannus/springboot-vuejs/categories')
                    .then(res => this.categories = res.data);
                this.categoriesOpen = !this.categoriesOpen;
                this.productOpen = false;
                this.depth = 0;
            },
            addProduct() {
                this.value = this.productList[0]
            }
        },
        data() {
            return {
                depth: 0,
                value: "none",
                productOpen: "false",
                categoriesOpen: "false",
                categories: [],
                productList: []
            }
        }
    }
</script>

<style>
</style>