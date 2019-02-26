import Home from './views/Home.vue';
import Profile from './views/Profile.vue';
import Stock from './views/Stock.vue';
import LandingPage from './views/LandingPage.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue'

export default [
    // Redirects to /route-one as the default route.
    {
        path: '/',
        component: LandingPage
    },
    {
        path: '/home',
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