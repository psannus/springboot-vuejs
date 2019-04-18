<template>
    <div>
        <Navbar activeTab="Home"/>
        <homepageBody v-if="(!basketOpen && !categoriesOpen && !productOpen)"></homepageBody>
        <Basket v-on:show-basket="showBasket"/>
        <BasketList v-if=basketOpen v-bind:showButton="showButton" v-bind:basketList="basketList"
                    v-on:del-product="deleteProduct"
                    v-on:save-list="saveBasketList"/>
        <Categories v-if=categoriesOpen v-bind:categories="categories"
                    v-on:sel-cat="selectCategory"/>
        <ProductList v-if=productOpen v-bind:productList="productList"
                     v-on:add-product="addProduct"/>
        <AddProduct v-on:show-cat="showCategories"/>
        {{value !== "none" ? value : ""}}
    </div>
</template>


<style>

</style>

<script>
    import Navbar from "../../components/layout/Navbar";
    import Categories from "../../components/article/Categories";
    import AddProduct from "../../components/ui/AddProductButton"
    import ProductList from "../../components/article/ProductList";
    import Basket from "../../components/layout/Basket"
    import BasketList from "../../components/article/BasketList"
    import homepageBody from "../../components/layout/homepageBody"
    import axios from "axios/index"

    axios.defaults.withCredentials = true;

    export default {
        name: 'Stock',
        components: {
            ProductList,
            BasketList,
            Categories,
            AddProduct,
            Basket,
            Navbar,
            homepageBody,
        },
        mounted() {
            axios.post('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/categories-get', {
                id: "0",
                url: " https://www.prismamarket.ee/products/selection",
                name: "init"
            }).then(res => {
                this.allproducts = res.data.categoryList;
            });
            axios.get('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/products-mock')
                .then(res => {
                    this.productList = res.data.productlist;
                });
        },


        methods: {
            showBasket() {
                this.depth = 0;
                this.categories = this.allproducts;
                if (this.basketOpen) this.basketOpen = false;
                else {
                    this.categoriesOpen = false;
                    this.productOpen = false;
                    this.basketOpen = true;
                }
            },
            deleteProduct(id) {
                let found = false;
                this.basketList = this.basketList.filter(product => {
                    if (product.id === id && !found) {
                        found = true;
                        return false;
                    } else {
                        return true;
                    }

                })
            },
            saveBasketList() {
                //TO-DO
                //Some backend magic to save the basketList to stockpile
                axios.post('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/basket-save', {
                    id: "1",
                    productList: this.basketList
                })
                    .then(res => {
                        if (res !== null) this.basketList = []
                    })
            },
            selectCategory(category) {
                this.depth++;
                if (this.depth === 1) {
                    axios.post('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/categories-get', category)
                        .then(res => {
                            this.categories = res.data.categoryList;
                        });

                } else if (this.depth === 2) {
                    axios.post('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/products-list', category)
                        .then(res => {
                            this.productList = res.data.productList;
                            this.categoriesOpen = false;
                            this.productOpen = true;
                        });
                    //this.value = this.categories.filter(cat => cat.id === id);
                }
            },
            showCategories() {
                this.basketOpen = false;
                if (this.depth > 0) {
                    this.depth = 0;
                    this.value = "none";
                    this.categories = this.allproducts;
                    this.categoriesOpen = false;
                    this.productOpen = false;
                } else {
                    this.categories = this.allproducts;
                    this.categoriesOpen = !this.categoriesOpen;
                    this.productOpen = false;
                }
            },
            addProduct(ean) {
                this.basketList.push(this.productList.filter(product => product.ean === ean)[0]);
            }
        },
        data() {
            return {
                depth: 0,
                value: "none",
                productOpen: false,
                categoriesOpen: false,
                basketOpen: false,
                showButton: true,
                basketList: [],
                allproducts: [],
                categories: [],
                productList: []
            }
        }
    }
</script>

