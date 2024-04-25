const MainRoutes = {
  path: '/',
  meta: {
    requiresAuth: true
  },
  /*redirect: '/main',*/
  component: () => import('@/layouts/common/DefautLayout.vue'),
  children: [
    {
      name: '적립 프로모션',
      path: '/savePromotion',
      component: () => import('@/views/promotionManagement/savePromotion/SavePromotion.vue')
    }
  ]
};

export default MainRoutes;
