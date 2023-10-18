import {createRouter, createWebHashHistory} from 'vue-router';
import welcomeComponent from "@/components/WelcomeComponent";
import unknownRoute from "@/components/UnknownRoute";
import cabinsOverview31 from "@/components/cabins/CabinsOverview31.vue";
import cabinsOverview32 from "@/components/cabins/CabinsOverview32.vue";
import cabinsOverview33 from "@/components/cabins/CabinsOverview33.vue";
import cabinsOverview34 from "@/components/cabins/CabinsOverview34.vue";
import cabinsDetail32 from "@/components/cabins/CabinsDetail32.vue";
import cabinsDetail34 from "@/components/cabins/CabinsDetail34.vue";
import cabinsOverview37 from "@/components/cabins/CabinsOverview37.vue";
import cabinsDetail37 from "@/components/cabins/CabinsDetail37.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: welcomeComponent
    },
    {
        path: '/cabins',
        name: 'cabins',
        children: [
            {
                path: 'overView31',
                name: 'overView31',
                component: cabinsOverview31
            },
            {
                path: 'overView32',
                name: 'overView32',
                component: cabinsOverview32
            },
            {
                path: 'overView33',
                name: 'overView33',
                component: cabinsOverview33,
                children: [
                    {
                        path: ':id',
                        component: cabinsDetail32
                    }
                ]
            },
            {
                path: 'overView34',
                name: 'overView34',
                component: cabinsOverview34,
                children: [
                    {
                        path: ':id',
                        component: cabinsDetail34
                    }
                ]
            },
            {
                path: 'overView37',
                name: 'overView37',
                component: cabinsOverview37,
                children: [
                    {
                        path: ':id',
                        component: cabinsDetail37
                    }
                ]
            }
        ]
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