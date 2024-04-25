import axios from 'axios';

axios.defaults.timeout = 10000;

export let axiosApi = axios.create({
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
    AUTHORIZATION: 'Bearer ' + localStorage.getItem('access_token')
  },
  withCredentials: true
});

export function setAxiosApi() {
  axiosApi = axios.create({
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      AUTHORIZATION: 'Bearer ' + localStorage.getItem('access_token')
    },
    withCredentials: true
  });
}