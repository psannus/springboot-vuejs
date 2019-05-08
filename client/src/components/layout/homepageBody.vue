<template>
    <div>
        <div>
            <b-carousel
                    :interval="4000"
                    indicators
                    img-width="1240"
                    img-height="550">
                <b-carousel-slide style="max-height: 550px">
                    <img
                            slot="img"
                            class="d-block img-fluid w-100"
                            src="https://s3.amazonaws.com/tt-trackify/assets/images.jpg"
                            alt="image slot">
                </b-carousel-slide>
                <b-carousel-slide style="max-height: 550px; background-color: #a4e271">
                    <img style="max-height: 600px"
                         slot="img"
                         class="d-block img-fluid w-100"
                         src="https://s3.amazonaws.com/tt-trackify/assets/logo.png"
                         alt="image slot">
                </b-carousel-slide>
                <b-carousel-slide style="max-height: 550px">
                    <img
                            slot="img"
                            class="d-block img-fluid w-100"
                            src="https://s3.amazonaws.com/tt-trackify/assets/iStock-862477938.jpg"
                            alt="image slot">
                </b-carousel-slide>
            </b-carousel>
        </div>

        <div class="container">
            <div class="best pb-3">
                <h1>Expiring products</h1>
            </div>
            <div>
                <b-table v-if="expiringProducts.length !== 0" hover :items="expiringProducts"
                         :fields="fields"></b-table>
                <b-table v-if="expired.length !== 0" style="color:red" hover :items="expired"
                         :fields="fields"></b-table>
            </div>

            <div v-if="show_table_boolean">
                <h3 style="text-align: center" class="p-4">No expiring products right now!</h3>
            </div>

        </div>
        <div class="jumbotron">
            <div class="container" style="text-align: center">
                <h4 style="font-family: Gotham">
                    Life expectancy would grow by leaps and bounds if green vegetables smelled as good as bacon -
                    Doug Larson
                </h4>
            </div>
        </div>
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-4">
                    <img src="https://s3.amazonaws.com/tt-trackify/assets/healthy.jpg" alt="picture">
                </div>
                <div class="col-md-4" style="align-content: center">
                    <img src="https://s3.amazonaws.com/tt-trackify/assets/images.png" alt="picture">
                </div>
                <div class="col-md-4">
                    <img src="https://s3.amazonaws.com/tt-trackify/assets/food_pyramid.jpg" alt="picture">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from "axios/index"
    import api from "../../Api.js";

    axios.defaults.withCredentials = true;


    export default {
        name: "homepageBody",

        data() {
            return {
                fields: ['expiryDate', 'name', 'amount', 'price'],
                stock: [],
                currYear: "",
                currMonth: "",
                currDay: "",
                expiringProducts: [],
                expired: []
            }
        },

        computed: {
            show_table_boolean() {
                return (this.expiringProducts.length === 0) && (this.expired.length === 0)
            }
        },

        mounted() {
            axios.get(api.url + '/basket-list', {
                params: {
                    id: 1
                }
            })
                .then(res => {
                    res.data.forEach(i => {
                        this.stock = this.stock.concat(i.productList);
                    });
                    this.getExpiringProducts();
                });
        },

        methods: {
            getExpiringProducts() {
                let expiring = [];
                let expired = [];

                this.stock.forEach(function (item) {
                    if (item.expiryDate !== "") {
                        if (Date.parse(item.expiryDate) >= (new Date().getTime()) &&
                            Date.parse(item.expiryDate) <= (new Date().getTime() + 259200000)) {
                            expiring.push(item);
                        }
                        if (Date.parse(item.expiryDate) < (new Date().getTime())) {
                            expired.push(item);

                        }
                    }
                });
                this.expiringProducts = expiring;
                this.expired = expired;
            }
        }
    }


</script>

<style scoped>
    .container {
        display: flex;
        flex-direction: column;
        width: 80%;
        position: relative;
        margin: 6% auto auto;
    }

    .best {
        text-align: left;
    }

</style>