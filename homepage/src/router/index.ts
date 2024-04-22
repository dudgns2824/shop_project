import { createRouter, createWebHistory } from 'vue-router';
import MainRoutes from './MainRoutes';
import AuthRoutes from './AuthRoutes';
import { useAuthStore } from '@/stores/auth';
import { useUIStore } from '@/stores/ui';
import axios from 'axios';

export const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/:pathMatch(.*)*',
            component: () => import('@/views/pages/maintenance/error/Error404Page.vue')
        },
        MainRoutes,
        AuthRoutes
    ]
});

interface User {
    // Define the properties and their types for the user data here
    // For example:
    id: number;
    name: string;
}

// Assuming you have a type/interface for your authentication store
interface AuthStore {
    user: User | null;
    returnUrl: string | null;

    login(username: string, password: string): Promise<void>;

    logout(): void;
}

router.beforeEach(async (to, from, next) => {
    const axiosApi = axios.create({
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
            AUTHORIZATION: 'Bearer ' + localStorage.getItem('access_token')
        },
        withCredentials: true
    });
    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/auth/login'];
    const authRequired = !publicPages.includes(to.path);
    const auth = useAuthStore();

    if (authRequired) {
        if (authRequired && (localStorage.getItem('access_token') == null || localStorage.getItem('access_token') == '')) {
            auth.returnUrl = to.fullPath;
            return next('/auth/login');
        } else {
            axiosApi
                .get('/api/ubhWeb/auth/healthCheck')
                .then((e) => {
                    next();
                })
                .catch((e) => {
                    localStorage.removeItem('access_token');
                    localStorage.removeItem('user_no');
                    return next('/auth/login');
                });
        }
    } else {
        if (!authRequired) {
            next();
        }
    }
    next();
});

router.beforeEach(() => {
    const uiStore = useUIStore();
    uiStore.isLoading = true;
});

router.afterEach(() => {
    const uiStore = useUIStore();
    uiStore.isLoading = false;
});
