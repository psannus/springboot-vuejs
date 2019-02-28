<template>
    <div>
        <Navbar activeTab="Home"/>
        <div class="home_container">
            <div id="nav">
                <router-link v-if="$parent.authenticated" to="/">Log out</router-link>
            </div>
            <h1>
                Welcome to a beautiful Vue app!
            </h1>
        </div>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar";

    export default {
        components: {
            Navbar,
        }
    }
</script>