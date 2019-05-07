<template>
    <div class="product-box">
        <img class="product-image" :src="basketItem.image" alt="NONE">
        <div class="info-display-container">
            <div class="info-display">
                <h1>{{basketItem.name}}</h1>
                <h2>Amount: {{basketItem.amount}}</h2>
                <h2>Shelf: {{basketItem.shelf}}</h2>
                <h2>{{basketItem.price}}€</h2>
                <h4>EAN: {{basketItem.ean}}</h4>
            </div>
            <div class="info-display-buttons">
                <h1 v-if="basketItem.expiryDate" class="expiry-date" v-on:click="expiryClick">Expires:
                    {{basketItem.expiryDate}}</h1>
                <h1 v-else class="expiry-date" v-on:click="expiryClick">Expiry UNSET</h1>
                <b-form-input
                        v-if=showExpiryEdit
                        class="expiry-date-input form-control-sm"
                        :type="expiryInput"
                        v-model="expiry"
                ></b-form-input>
                <button v-if=showExpiryEdit v-on:click="setExpiry">set expiry</button>
            </div>
        </div>
        <button v-on:click="$emit('del-product')" class="circle">-</button>
    </div>
</template>

<script>
    export default {
        name: "BasketItem",
        props: ["basketItem"],
        data() {
            return {
                expiryInput: "date",
                expiry: "",
                showExpiryEdit: false,
            }
        },
        methods: {
            expiryClick() {
                this.showExpiryEdit === true ? this.showExpiryEdit = false : this.showExpiryEdit = true;
            },
            setExpiry() {
                this.basketItem.expiryDate = this.expiry;
                this.$emit('update-product');
            }
        }
    }
</script>

<style scoped>
    h1, h2, h4 {
        margin: 10px;
    }

    h1 {
        font-size: 1em;
        margin-top: 1em;
        margin-bottom: 0;
    }

    h2 {
        font-weight: normal;
        font-size: 0.75em;
        margin-bottom: 0;
    }

    h4 {
        margin-top: 15px;
        font-weight: normal;
        font-style: italic;
        font-size: 0.50em;
    }

    .product-box {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 50em;
        border: 1px solid #ddd;
        border-radius: 5px;
        margin: 0.5em auto;
    }

    .circle {
        background-color: #ddd;
        border-radius: 999em;
        width: 56px;
        height: 56px;
        line-height: 1;
        font-size: 36px;
        position: relative;
        cursor: pointer;
        border: 0;
    }

    .circle:hover {
        background-color: #2CC3B5;
    }

    .product-image {
        object-fit: contain;
        align-self: flex-start;
        width: 9em;
        height: 9em;
    }

    .info-display {
        display: flex;
        width: 20em;
        flex-direction: column;
    }

    .info-display-container {
        display: flex;
    }

    .info-display-buttons {
        width: 10rem;
        display: flex;
        flex-direction: column;
        justify-content: center;
        font-size: 0.9rem;
    }

    .expiry-date-input {
        width: 10rem;
        align-self: flex-end;
    }

    .expiry-date {
        margin-bottom: 1rem;
    }
</style>