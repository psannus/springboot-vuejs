<template>
    <div id="app">
        <Navbar/>
        <Homepage/>
    </div>
</template>

<script>
    import Navbar from "./components/Navbar";
    import Homepage from "./components/Homepage"
    // app Vue instance
    const app = {
        name: 'app',
        components: {
            Navbar,
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