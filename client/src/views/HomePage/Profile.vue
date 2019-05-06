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
                let initialValue = 0;

                this.products = this.stock.length;
                this.price = this.stock.reduce(function (total, currentValue) {
                    return total + parseFloat(currentValue.price);
                }, initialValue);

                let highest = this.stock.reduce(function (countMap, word) {
                    countMap[word.shelf] = ++countMap[word.shelf] || 1;
                    return countMap
                }, {});

                let highest_value = Math.max.apply(null, Object.values(highest));

                this.categorys = Object.keys(highest).find(key => highest[key] === highest_value);

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