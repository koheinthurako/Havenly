<template>
  <div class="d-flex align-center justify-center mt-5 pt-5" style="height: 70vh;">
      <v-sheet width="400" class="mx-auto">
          <v-form @submit.prevent="submit(user.email,user.newPassword)" >
              
              <v-text-field v-model="user.email" label="Email" :rules="[value => !!value || 'Required']" ></v-text-field>
  
              <v-text-field
               v-model="user.newPassword"  label="New Password" :rules="[value => !!value || 'Required']" >
  
              </v-text-field>
             
  
              <a href="/login" class="text-body-2 font-weight-regular">Log in</a>
  
              <v-btn  type="submit" block class="mt-2 bg-redbrick text-white mt-3">Submit</v-btn>
  
          </v-form>
          
      </v-sheet>
  </div>
  </template>
  <script>
  import axios from 'axios';
  import Swal from 'sweetalert2';  
  import router from '@/router';
  
  export default {
  data() {
    return {
      user :{
         
        email: '',
        newPassword:''
       
      },
      
    };
  },
  
  
  
  methods: {
 
      // submit(email,newPassword) {
        
          
      //     function httpErrorHandler(error) {
      //                 if (axios.isAxiosError(error)) {
      //                     const response = error?.response
      //                     if(response){
      //                         const statusCode = response?.status
      //                         if(statusCode===400){alert("Invalid Username ...Please try again!!!")}
      //                     }
      //                     }
      //             }
      //   axios.put(`http://localhost:8083/setpassword/${email}/${newPassword}`)
        
        
         
      //     .then(function(response){
           
                
                  
               
  
      //             const status=response.status
      //             console.log(status)
      //             if(status=='200'){
                    
      //              alert("Password updated Successfully!!!")
                  
      //           }
                
                  
      //                 })
               
      //     .catch(httpErrorHandler)
        
          
         
      //          },
      submit(email,newPassword){
         console.log(email,newPassword)

         function httpErrorHandler() {
            Swal.fire({
              title: 'Missing Information.',
              text: 'Failed to update new password. Please try again...',
              icon: 'error',
              customClass: {
                confirmButton: 'myCustomButton'
              },
              buttonsStyling: false,
              allowOutsideClick: false,
              allowEscapeKey: false
            })
        }
                
         axios.put(`http://localhost:8083/setpassword/${email}/${newPassword}`)
       

        
        
         
          .then(function(response){
                  const status=response.status
                  console.log(status)
                  if(status=='200'){
                    Swal.fire({
                      title: 'Updated New Password',
                      text: 'Your password updated successfully. Now go to login...',
                      icon: 'success',
                      customClass: {
                        confirmButton: 'myCustomSuccessButton'
                      },
                      buttonsStyling: false,
                      allowOutsideClick: false,
                      allowEscapeKey: false
                    }).then(() => {
                      router.push('/login');
                    });
                }
                
                  
                      })
                      .catch(httpErrorHandler)
      }
  },
  }
  </script>