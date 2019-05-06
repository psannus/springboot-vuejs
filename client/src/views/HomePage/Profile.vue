<template>
    <div>
        <Navbar activeTab="Profile"/>
        <hr>
        <div class="jumbotron mt-3">
            <div class="container" style="background-color: white; padding: 4%; max-width: 900px">
                <div class="">
                        <h2>Personal info</h2>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <h6 class="pb-4">First name</h6>
                                <h6 class="pb-4">Last name</h6>
                            </div>
                            <div class="col-sm-8">
                                <h6 class="pb-4">{{firstName}}</h6>
                                <h6 class="pb-4">{{secondName}}</h6>
                            </div>
                        </div>
                        <h2>Stock</h2>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <h6 class="pb-4">Products: </h6>
                                <h6 class="pb-4">Total price:</h6>
                                <h6 class="pb-4">Most popular category:</h6>
                            </div>
                            <div class="col-sm-8">
                                <h6 class="pb-4">{{products}}</h6>
                                <h6 class="pb-4">{{price}} €</h6>
                                <h6 class="pb-4">{{categorys}}</h6>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</template>

<style scoped>

</style>


<script>
    import Navbar from "../../components/layout/Navbar";
    import axios from "axios/index"

    export default {
        components: {
            Navbar,
        },
        mounted() {
            //axios.get('http://localhost:9000/basket-list', {
            axios.get('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/basket-list', {
                params: {
                    id: 1
                }
            })
                .then(res => {
                    res.data.forEach(i => {
                        this.stock = this.stock.concat(i.productList)
                    });
                    this.calculation()

                });

            //axios.get('http://localhost:9000/user' , {})
            axios.get('http://ec2-3-92-62-1.compute-1.amazonaws.com:9000/user', {})
                .then(res => {
                    this.firstName = res.data.firstName;
                    this.secondName = res.data.lastName;
                })

        },
        methods: {
            calculation() {
                let price = 0;
                let products = 0;
                let categorys = {};
                let highest = "";
                let highest_value = 1;
                this.stock.forEach(function (item) {
                    price += (parseFloat(item.price));
                    products += 1;
                    if (categorys[item.shelf] == null) {
                        categorys[item.shelf] = 1;
                        if (highest_value === 1) {
                            highest = item.shelf;
                        }
                    } else {
                        categorys[item.shelf] = categorys[item.shelf] + 1;
                        if (categorys[item.shelf] > highest_value) {
                            highest_value = categorys[item.shelf];
                            highest = item.shelf;
                        }
                    }
                });
                this.price = Math.round(price * 100) / 100;
                this.products = products;
                this.categorys = highest;

            }
        },
        data() {
            return {
                stock: [],
                price: 0,
                shelf: 0,
                categorys: "",
                products: 0,
                firstName: "",
                secondName: "...",
            }
        }
    }

</script>