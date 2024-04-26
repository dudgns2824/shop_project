import { defineStore } from 'pinia';
import { router } from '@/router';
import { axiosApi, setAxiosApi } from '@/utils/axiosApi';
import Swal from 'sweetalert2';

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    // initialize state from local storage to enable user to stay logged in
    /* eslint-disable-next-line @typescript-eslint/ban-ts-comment */
    // @ts-ignore
    user: JSON.parse(localStorage.getItem('user')),
    userId: '',
    userPassword: '',
    returnUrl: null as any
  }),
  actions: {
    clickLoginButton() {
      axiosApi
        .get('/api/ubhWeb/auth/login', {
          params: {
            id: this.userId,
            password: this.userPassword
          }
        })
        .then((res) => {
          if (
            res.data.status == '정상' &&
            res.data.statusCode == 200 &&
            res.data.data.accessToken != null &&
            res.data.data.accessToken != ''
          ) {
            localStorage.setItem('access_token', res.data.data.accessToken);
            localStorage.setItem('user_no', res.data.data.user.userNo);
            localStorage.setItem('customer_cd', res.data.data.customerCd);

            setAxiosApi();

            router.push('/dashboard/currentPromotion');
          } else {
            Swal.fire({
              title: '입력 정보와 일치하는 유저가 없습니다!',
              icon: 'error',
              timer: 3000,
              showCancelButton: false,
              showConfirmButton: false,
              timerProgressBar: true
            });
          }
        });
    },
    logout() {
      this.user = null;
      localStorage.removeItem('access_token');
      localStorage.removeItem('user_no');
      router.push('/');
    }
  }
});
