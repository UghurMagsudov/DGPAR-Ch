import axios from './axios-api';

export const testAuthorization = async () => {
  try {
    const token = localStorage.getItem('token');
    const response = await axios.get('/test',{
        headers: { 'Authorization': `Bearer ${token}` }});
    alert (response.data)
    return response.data;
  } catch (error) {
    alert('Call Failed', error.response);
  }
};