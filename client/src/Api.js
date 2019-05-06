import axios from 'axios'

axios.defaults.withCredentials = true;

const SERVER_URL = 'http://ec2-3-92-62-1.compute-1.amazonaws.com:9000';

//const SERVER_URL = 'http://localhost:9000';

const instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 600000
});

export default {
    login: (user) => instance.post('login', user),
    registration: (user) => instance.post('registration', user),
}