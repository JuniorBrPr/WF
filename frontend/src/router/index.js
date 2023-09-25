import { createRouter, createWebHashHistory } from 'vue-router';
import welcomeComponent from "@/components/WelcomeComponent";
import cabinsOverview from "@/components/cabins/cabinsOverview";
import overviewComponent from "@/components/cabins/OverviewComponent";
import unknownRoute from "@/components/UnknownRoute";
const routes = [
    {
        path: '/',
        name: 'Home',
        component: welcomeComponent
    },
    {
        path: '/overView31',
        name: 'overView31',
        component: overviewComponent
    },
    {
        path: '/overView32',
        name: 'overView32',
        component: cabinsOverview
    },
    {
        // path: "*",
        path: "/:catchAll(.*)",
        name: "NotFound",
        component: unknownRoute,
        meta: {
            requiresAuth: false
        }
    }
];

export const router = createRouter({
    history: createWebHashHistory(),
    routes
})