<template>
    <div>
        <Navbar activeTab="Stock"/>
        <BasketList v-if=basketOpen v-bind:showButton="showButton" v-bind:basketList="basketList"
                    v-on:del-product="deleteProduct" v-on:update-product="updateExpiryDate"/>
        {{value !== "none" ? value : ""}}
    </div>
</template>

<script>
    import Navbar from "../../components/layout/Navbar";
    import BasketList from "../../components/article/BasketList";
    import axios from "axios/index";
    import api from "../../Api.js";

    export default {
        name: 'Stock',
        components: {
            Navbar,
            BasketList,
        },
        mounted() {
            axios.get(api.url + '/basket-list', {
                params: {
                    id: 1
                }
            })
                .then(res => {
                    this.addToBasket(res);
                });
        },
        methods: {
            deleteProduct(id) {
                let remove = this.basketList.find(product => product.id === id);
                axios.post(api.url + '/basket-remove', remove).then(res => {
                    if (res !== null) this.value = "";
                    axios.get(api.url + '/basket-list', {
                        params: {
                            id: 1
                        }
                    })
                        .then(res => {
                            this.addToBasket(res);
                        });
                });
            },
            updateExpiryDate(id, date) {
                const product = this.basketList.find(product => product.id === id);
                product.expiryDate = date;

                axios.post(api.url + '/basket-update-expiry', product).then(res => {
                    if (res !== null) this.value = "";
                    axios.get(api.url + '/basket-list', {
                        params: {
                            id: 1
                        }
                    })
                        .then(res => {
                            this.addToBasket(res);
                        });
                });
            },
            addToBasket(res) {
                this.basketList = [];
                res.data.forEach(i => {
                    this.basketList = this.basketList.concat(i.productList);
                });
                this.basketOpen = true;
            }
        },
        data() {
            return {
                value: "none",
                basketOpen: false,
                showButton: false,
                basketList: []
            }
        }
    }
</script>

<style>
</style>