import axios from 'axios';
import cookies from 'js-cookie';

export const axiosApi = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
    headers: {
        access_token: cookies.get('access_token'),
    }
})