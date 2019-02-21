<template>
    <div id="app">
        <Homepage/>
    </div>
</template>

<script>
    import Homepage from "./components/Navbar";
    // app Vue instance
    const app = {
        name: 'app',
        components: {
            Homepage
        },
        // app initial state
        data: () => {
            return {}
        }
    }

    export default app
</script>

<style>
    [v-cloak] {
        display: none;
    }
</style>