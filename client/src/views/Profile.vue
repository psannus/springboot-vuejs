<template>
    <div>
        <Navbar activeTab="Profile"/>
        <div class="profile_container">
            First_name <br>
            user <br><br>
            Last_name <br>
            user
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