<template>
    <div  style="height: 100vh;margin-top: 110px;margin-left: 80px;"  >
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

            <v-btn class="mt-2 bg-redbrick text-white" @click="deleteUser(haha.email)" >Delete</v-btn>
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
       
      }
    }
  }
  </script>