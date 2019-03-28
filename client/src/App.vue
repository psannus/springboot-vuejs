<template>
    <div id="app">
        <router-view @authenticated="setAuthenticated"/>
    </div>
</template>

<script>
    export default {
        name: 'app',
        data() {
            return {
                authenticated: false,
            }
        },
        mounted() {
            if (!localStorage.authenticated) {
                this.$router.replace({name: "landing"})
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            },
        }
    }
</script>

<style>
    [v-cloak] {
        display: none;
    }
</style>