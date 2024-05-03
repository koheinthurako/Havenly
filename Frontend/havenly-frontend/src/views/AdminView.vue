<template>
  <div style=" height: 60px; color: white; background-color: #e86f52;"><h1><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havenly</em></h1></div>
    <div  style="height: 75vh;margin-top: 60px;margin-left: 60px;">
          <v-table >
      <thead>
        <tr>
          <th class="text-left">
            Name
          </th>
          <th class="text-left">
            E-mail
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="haha in users"
          :key="haha.name"
        >
          <td>{{ haha.name }}</td>
          <td>{{ haha.email }}</td>
          <td>

            <v-btn class="mt-2 me-3 bg-redbrick text-white" @click="deleteUser(haha.email)" >Delete</v-btn>
            <v-btn class="mt-2 bg-redbrick text-white" @click="banUser(haha)">Ban</v-btn>
          </td>
        </tr>
      </tbody>
    </v-table>
  </div>
  </template>


  <script>
import axios from 'axios';
  

    export default {
       
      data () {
        return {
          users: [
            {
              name: '',
              email: ''
            },
            
          ],
        }
      },

      mounted(){
        this.fetchUsers();
      },

      methods :{
        fetchUsers(){
          fetch('http://localhost:8083/getAll')
          .then(response=>response.json())
          .then(data=>{
            const mappedData=data.map(users=>({

              name :users.name,
              email : users.email
            }))
            sessionStorage.setItem('users',JSON.stringify(mappedData));
            this.users=mappedData;
          }
        )
        },deleteUser(email) {
      axios.delete(`http://localhost:8083/delete/${email}`)
      .then(() => {
   
      this.users = this.users.filter((u) => u.email !== email);
      alert('User deleted successfully.');
    })
    .catch((error) => {
      console.error('Error deleting user:', error);
      alert('Failed to delete user. Please try again.');
    });
       
      },
      banUser(haha){
        axios.post(`http://localhost:8083/ban/register`,haha)
        .then(alert('User is banned successfully.'))
        .catch((error) => {
      console.error('Error banning user:', error);
      alert('Failed to ban user. Please try again.');
    });
      }
    }
  }
  </script>