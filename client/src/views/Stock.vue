<template>
    <div>
        <Navbar activeTab="Stock"/>
        <Categories v-bind:class="{'is-open':open}" v-bind:categories="categories" v-on:sel-cat="selectCategory"/>
        <AddProduct v-on:show-cat="addProduct"/>
        {{value}}
        {{open}}
    </div>
</template>

<script>
    import Navbar from "../components/Navbar";
    import Categories from "../components/Categories";
    import AddProduct from "../components/AddProduct"

    export default {
        name: 'Stock',
        components: {
            Categories,
            AddProduct,
            Navbar,
        },
        methods: {
            selectCategory(id) {
                this.value = this.categories.filter(cat => cat.id === id)
            },
            addProduct() {
                this.open = !this.open;
                this.value = "dropdown"
            }
        },
        data() {
            return {
                value: "none",
                open: "false",
                categories: [
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
                ]
            }
        }
    }
</script>

<style>
</style>