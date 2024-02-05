import axios from 'axios';
import cookies from 'js-cookie';

axios.defaults.timeout = 5000;

export const axiosApi = axios.create({
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
        access_token: cookies.get('access_token'),
    }
})