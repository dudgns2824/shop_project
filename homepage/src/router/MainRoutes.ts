const MainRoutes = {
  path: '/main',
  meta: {
    requiresAuth: true
  },
  redirect: '/main',
  component: () => import('@/layouts/common/DefaultLayout.vue'),
  children: [
    {
    }
  ]
};

export default MainRoutes;
