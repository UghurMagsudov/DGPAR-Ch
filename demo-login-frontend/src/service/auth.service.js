import axios from './axios-api';

export const loginUser = async (userData) => {
    try {
        const response = await axios.post('/auth/login', userData);
        if (response.data.accessToken) {
            localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
    } catch (error) {
        console.error('Login error:', error.response);
        throw error;
    }
};

export const registerUser = async (userData) => {
    try {
        const response = await axios.post('/auth/register', userData);
        return response.data;
    } catch (error) {
        console.error('Registration error:', error.response);
        throw error;
    }
};
