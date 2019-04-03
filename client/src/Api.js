import axios from 'axios'
axios.defaults.withCredentials = true;

const SERVER_URL = 'http://localhost:9000';

const instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 600000
});

export default {
    login: (user) => instance.post('login', user),
    registration: (user) => instance.get('registration', user),
}