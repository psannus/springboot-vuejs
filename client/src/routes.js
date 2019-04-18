import Home from './views/HomePage/Home.vue';
import Profile from './views/HomePage/Profile.vue';
import Stock from './views/HomePage/Stock.vue';
import LandingPage from './views/IndexPage/LandingPage.vue';
import Login from './views/IndexPage/Login.vue';
import Register from './views/IndexPage/Register.vue'

export default [
    // Redirects to /route-one as the default route.
    {
        path: '/',
        name: "landing",
        component: LandingPage
    },
    {
        path: '/home',
        name: "home",
        component: Home,
        //// Children is just another route definition of sub-routes.
        //children: [
        //    {
        //        // Note: No leading slash. This can trip people up sometimes.
        //        path: 'route-one-child',
        //        component: RouteOneChild
        //    }
        //]
    },
    {
        path: '/profile',
        component: Profile,
    },
    {
        path: '/stock',
        component: Stock,
    },
    {
        path: '/login',
        name: "login",
        component: Login
    },
    {
        path: '/register',
        component: Register
    }
    //{
    //    // Route two takes the route parameter "id".
    //    // The parameter value can be accessed with $route.params.id in the RouteTwo component.
    //    path: '/route-two/:id',
    //    component: RouteTwo
    //}


];