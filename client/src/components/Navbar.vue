<template>
    <div class="nav_container">
        <div class="topnav">
            <router-link to="/">
                <img class="logo" alt="Trackify" src="../assets/logo.png">
            </router-link>
        </div>
        <div class="navbar">
            <router-link to="/stock" :class="{ active: activeTab === 'Stock'}">Stock</router-link>
            <router-link to="/home" :class="{ active: activeTab === 'Home'}">Home</router-link>
            <router-link to="/profile" :class="{ active: activeTab === 'Profile'}">Profile</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Navbar',
        props: ['activeTab'],
    }
</script>

<style>
    .nav_container {
        display: flex;
        justify-content: center;
    }

    .navbar {
        z-index: 1;
        background-color: #555;
        overflow: hidden;
        position: fixed;
        bottom: 0;
        width: 100%;
        height: 2.25rem;
        text-align: center;
        display: inline-flex;
        justify-content: center;
    }

    /* Style the links inside the navigation bar */
    .navbar a {
        float: left;
        display: flex;
        color: #f2f2f2;
        text-align: center;
        align-items: center;
        padding: 1rem 0.75rem;
        text-decoration: none;
        font-size: 0.75rem;
    }

    /* Change the color of links on hover */
    .navbar a:hover {
        background-color: #ddd;
        color: black;
    }

    /* Add a color to the active/current link */
    .navbar a.active {
        background-color: #4CAF50;
        color: white;
    }

    .topnav {
        z-index: 1;
        background-color: #555;
        width: 100%;
        height: 3.5rem;
        overflow: hidden;
        display: inline-flex;
        justify-content: center;
    }

    /* Style the links inside the navigation bar */
    .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        text-decoration: none;
        background-image: url("/src/assets/logo.png");
    }

    .logo {
        width: 100%;
        height: 100%;
        object-fit: contain;
        margin: auto;
    }
</style>