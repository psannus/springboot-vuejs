<template>
    <div>
        <Navbar activeTab="Stock"/>
        <BasketList v-if=basketOpen v-bind:showButton="showButton" v-bind:basketList="basketList"
                    v-on:del-product="deleteProduct"/>
        {{value !== "none" ? value : ""}}
    </div>
</template>

<script>
    import Navbar from "../../components/layout/Navbar";
    import BasketList from "../../components/article/BasketList"
    import axios from "axios/index"

    export default {
        name: 'Stock',
        components: {
            Navbar,
            BasketList,
        },
        mounted() {
            axios.get('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/basket-list', {
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
                axios.post('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/basket-remove', remove).then(res => {
                    if (res !== null) this.value = "";
                    axios.get('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/basket-list', {
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