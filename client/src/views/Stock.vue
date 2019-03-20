<template>
    <div>
        <Navbar activeTab="Stock"/>
        <BasketList v-if=basketOpen v-bind:showButton="showButton" v-bind:basketList="basketList"
                    v-on:del-product="deleteProduct"/>
        {{value !== "none" ? value : ""}}
    </div>
</template>

<script>
    import Navbar from "../components/Navbar";
    import BasketList from "../components/BasketList"
    import axios from "axios"

    export default {
        name: 'Stock',
        components: {
            Navbar,
            BasketList,
        },
        mounted() {
            axios.get('http://localhost:9000/basket-list', {
                params: {
                    id: 1
                }
            })
                .then(res => {
                    this.basketList = [];
                    res.data.forEach(i => {
                        this.basketList = this.basketList.concat(i.productList);
                    });
                    this.basketOpen = true;
                });
        },
        methods: {
            deleteProduct(id) {
                let remove = this.basketList.find(product => product.id === id);
                axios.post('http://localhost:9000/basket-remove', remove).then(res => {
                    if (res !== null) this.value = "";
                    axios.get('http://localhost:9000/basket-list', {
                        params: {
                            id: 1
                        }
                    })
                        .then(res => {
                            this.basketList = [];
                            res.data.forEach(i => {
                                this.basketList = this.basketList.concat(i.productList);
                            });
                            this.basketOpen = true;
                        });
                });
            },
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