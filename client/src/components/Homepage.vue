<template>
    <div class="home_container">
        <div v-bind:style="{ display: computedDisplay }" class="product_selection">
            <button class="product">Meat/Animal products</button>
            <button class="product">Dairy</button>
            <button class="product">Vegetable/Fruit</button>
            <button class="product">Drinks</button>
            <button class="product">Sweets/Snacks</button>
            <button class="product">Dry food</button>
            <button class="product">Other</button>
        </div>

        <div id="add_product" class="add_product">
            <button v-on:click="showProductSelection" class="circle">+</button>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                display: "none"
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