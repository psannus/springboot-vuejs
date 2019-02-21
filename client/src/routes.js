import Home from './views/Home.vue';
import Profile from './views/Profile.vue';
import Stock from './views/Stock.vue';

export default [
    // Redirects to /route-one as the default route.
    {
        path: '/',
        redirect: '/home'
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
    //{
    //    // Route two takes the route parameter "id".
    //    // The parameter value can be accessed with $route.params.id in the RouteTwo component.
    //    path: '/route-two/:id',
    //    component: RouteTwo
    //}
];