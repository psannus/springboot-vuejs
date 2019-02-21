<template>
    <div>
        <Navbar activeTab="Profile"/>
        <div class="profile_container">
            First_name Last_name
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