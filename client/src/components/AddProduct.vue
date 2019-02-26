<template>
    <div class="button-holder">
        <button v-on:click="$emit('show-cat')" class="circle">+</button>
    </div>
</template>

<script>
    export default {
        name: "AddProduct"
    }
</script>

<style scoped>
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

    .button-holder {
        width: 100%;
        display: flex;
        justify-content: center;
        position: fixed;
        bottom: 4em;
    }
</style>