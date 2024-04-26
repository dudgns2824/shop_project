const MainRoutes = {
  path: '/',
  meta: {
    requiresAuth: true
  },
  /*redirect: '/main',*/
  component: () => import('@/layouts/common/DefautLayout.vue'),
  children: [
    {
      name: '로그인',
      path: '/login',
      component: () => import('')
    }
  ]
};

export default MainRoutes;
