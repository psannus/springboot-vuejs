<template>
    <div class="home_container">
        <div id="app" v-bind:style="{ display: computedDisplay }" class="product_selection">
            <category-component v-for="category in categories" :category="category"/>
        </div>

        <div id="add_product" class="add_product">
            <button v-on:click="showProductSelection" class="circle">+</button>
        </div>
    </div>
</template>

<script>
    import Vue from "vue";

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
        template: `<button class="product">{{category.name}}</button>`,
        props: {
            category: Object
        }
    });
    export default {
        data() {
            return {
                display: "none",
                categories
            }
        },
        computed: {
            computedDisplay: function () {
                return this.display;
            }
        },
        methods: {
            showProductSelection: function (event) {
                if (this.display === "none") {
                    this.display = "flex";
                }
            }
        }
    }
</script>