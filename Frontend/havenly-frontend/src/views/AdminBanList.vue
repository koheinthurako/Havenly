<template>


    <div  style=" height: 60px; color: #e86f52; background-color: white; text-align: center;"><h1><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havenly</em></h1></div>
    <div class="container">
    <div class="box1">
          <div id="sidebar" ref="sidebar" :class="{ expand: isExpanded }">
              <div class="d-flex">
                 
                    
                      <v-icon>mdi-view-grid</v-icon>
                      
                 
                  <ul class="sidebar-nav ">
                    <li class="sidebar-item">
                    <a class="sidebar-link"
                        @click="pushhh()">
                        <span>Pending Posts</span>
                    </a>
                </li>
                  <li class="sidebar-item">
                      <a class="sidebar-link"
                          @click="pushy()">
                          <span>Users</span>
                      </a>
                  </li>
                  <li class="sidebar-item">
                      <a class="sidebar-link" 
                      @click="ban()" >
                           <span>Ban List</span>
                      </a>
                  </li>
  
  
  
              </ul>
                  
              </div>
             
              <div class="d-flex custom-logout" @click="logout" style="cursor:pointer;">
                  <button class="toggle-btn" type="button">
                      <v-icon>mdi-logout</v-icon>
                  </button>
                  <div class="sidebar-logo">
                      <a class="sidebar-link">&nbsp; &nbsp; &nbsp; &nbsp;Logout</a>
                  </div>
              </div>
  
        </div>
        </div>
  
      <div class="box2" style="height: 75vh;">
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
              
            </td>
          </tr>
        </tbody>
      </v-table>
    </div>
    </div>
    </template>
  
  
    <script>
  import router from '@/router';
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
            fetch('http://localhost:8083/ban/getAll')
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
          },
          
          deleteUser(email) {
        axios.delete(`http://localhost:8083/ban/delete/${email}`)
        .then(() => {
     
        this.users = this.users.filter((u) => u.email !== email);
        alert('User deleted successfully.');
      })
      .catch((error) => {
        console.error('Error deleting user:', error);
        alert('Failed to delete user. Please try again.');
      });
         
        },
       
        
        pushy(){
          router.push('/admin')
        },
        ban(){
          router.push('/admin/ban')
        },
        logout(){
            router.push('/admin/login')
        }, pushhh(){
            router.push('/admin/post')
        }
      }
    }
    </script>
  
  
    <style>
     .container {
    display: flex;
    margin-top:20px;
    border-top: solid #e86f52 1px ;
    border-bottom: solid #e86f52 1px ;
    border-right: solid #e86f52 1px ;
  
  
    
     /* Use flexbox layout */
   
  }
  
  .box1 {
     /* Distribute available space equally */
    padding: 20px;
    
    
    
    background-color: #e86f52;
    color: white;
    height: auto;
    
    
  }
  
  .box2 {
     /* Distribute available space equally */
    padding: 20px;
    
    
   
    
  }
  .sidebar-link{
    
   
    text-decoration: none;
    color: white;background-color:
     #e86f52;
    
  
  
  }
  
  </style>