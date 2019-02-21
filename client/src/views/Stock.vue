<template>
    <div>
        <Navbar activeTab="Stock"/>
        <div class="stock_container">
            <div id="app" v-bind:style="{ display: computedDisplay }" class="product_selection">
                <category-component v-for="category in categories" :key="category.id" :category="category"/>
            </div>

            <div id="add_product" class="add_product">
                <button v-on:click="showProductSelection" class="circle">{{value}}</button>
            </div>
        </div>
    </div>
</template>

<script>
    import Vue from "vue";
    import Navbar from "../components/Navbar";

    const categories = [
        {
            id: 1,
            name: 'Meat/animal products',
            img: ''
        },
        {
            id: 2,
            name: 'Dairy',
            img: ''
        },
        {
            id: 3,
            name: 'Vegetable/Fruit',
            img: ''
        },
    ];
    Vue.component('category-component', {
        data() {
            return {
                display: "flex"
            }
        },
        template: `<button v-on:click="showCategorySelection" class="product">{{category.name}}</button>`,
        props: {
            category: Object
        }
    });
    export default {
        data() {
            return {
                display: "none",
                value: "+",
                category: 1,
                categories
            }
        },
        computed: {
            computedDisplay: function () {
                return this.display;
            }
        },
        methods: {
            showProductSelection() {
                if (this.display === "none") {
                    this.display = "flex";
                    this.value = "-"

                } else {
                    this.display = "none";
                    this.value = "+"
                }
            }
        },
        components: {
            Navbar,
        }
    }
</script>

<style>
    .stock_container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin: 2.3em auto;
        width: 80%;
        height: 500px;
        position: relative;
    }

    .add_product {
        margin-bottom: 2em;
        bottom: 0;
    }

    .circle {
        background-color: #d23f31;
        border-radius: 999em;
        width: 56px;
        height: 56px;
        line-height: 1;
        font-size: 36px;
        position: relative;
        cursor: pointer;
        border: 0;
    }

    .product_selection {
        display: none;
        flex-direction: column;
        margin-top: 2em;
        margin-bottom: 2em;
        overflow: auto;
    }

    .product {
        font-size: 1.5em;
        background: #4285F4;
        margin: 0.5em;
        border-radius: 2px;
        border: 0;
        transition: .2s ease-out;
        color: #fff;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    }

    .product:hover {
        color: #fff;
        box-shadow: 0 5px 11px 0 rgba(0, 0, 0, 0.18), 0 4px 15px 0 rgba(0, 0, 0, 0.15);
    }

    .product:active, .btn:focus, .btn.active {
        outline: 0;
        color: #fff;
    }
</style>