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
                       <span>Pending Ads</span>
                   </a>
               </li>
                <!-- <li class="sidebar-item">
                   <a class="sidebar-link"
                       @click="pushy()">
                       <span>Users</span>
                   </a>
               </li>
               <li class="sidebar-item">
                   <a class="sidebar-link" 
                        @click="ban()">
                       
                       <span>Ban List</span>
                   </a>
               </li> -->



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
 

    <div class="row mb-5 g-3 box2"  >
               <div v-for="ad in ads" :key="ad.ads_post_id" class="col-md-3">
                   <div class="card-container">
                       <!-- TZH card styles -->
                       <div class="card d-flex" style="height: 350px; width:fit-content;">
                          
                           <div class="cardImgBox mb-2" >
                               <img :src="ad.imageUrl" class="w-100 h-100" alt="Card image cap">
                           </div>
                           <div class="card-body p-2 d-flex flex-column">
                               <h5 class="card-title mb-2 pl-2">{{ ad.title }}</h5>
                               <p class="pl-2">{{ ad.status }}</p>
                               <v-divider :thickness="2" class="border-opacity-25 d-block"/>
                           </div>
                           <div style="padding: 0px 20px 20px 20px;">
                            <v-row>
                               <v-col cols="6">
                                   <v-btn @click="approve(ad)">Approve</v-btn>
                               </v-col>
                               <v-col @click="cancel(ad.ads_post_id)" cols="6">
                                   <v-btn>Cancel</v-btn>
                               </v-col>
                            </v-row>
                            </div>
                </div>
        </div>
</div>
</div>
</div>

</template>

<script>
import axios from 'axios';
import router from '@/router';
import Swal from 'sweetalert2';

export default {
   
   data: () => ({
       ads : [],
   }),

      mounted(){
        this.fetchAds();
      },

      methods :{

       fetchAds() {
       // fetch ads from backend
      fetch('http://localhost:8083/ads/all/pending')
        //  .then(response => {
        //   if (response.data !== null) {
        //     this.ads = response.data;            
        //     console.log("Ads", this.ads);
        //   }
        // })   
        .then(response => response.json())
          .then(data => {
            console.log(data);
            data.forEach(ad => {
                    //let imgUrl= ad.imageUrl;
                    if(ad.title.length>30){
                        let tit = ad.title; 
                        ad.title = tit.substring(0, 30) + "...";
                    }
                    console.log(ad)
                    this.ads.unshift({
                        ads_post_id :ad.ads_post_id,
                        status : ad.status,
                        title: ad.title,                      
                        imageUrl: ad.imageUrl,
                    });
                  //  console.log(imgUrl)
                
                
            });
            // console.log(this.posts);
          })    
         .catch(error => {
           console.error('Error fetching photos:', error);
         });
     },

     approve(ad){

       function httpErrorHandler(error) {
                       if (axios.isAxiosError(error)) {
                           const response = error?.response
                           if(response){
                               const statusCode = response?.status
                               if(statusCode===500){alert("Please try again!!!")}
                               else if(statusCode===400){alert("Please try again!!!")}
                               
                           }
                           }
       }
       axios.put("http://localhost:8083/admin/approve",ad)
       .then(function(response){
           const status=JSON.parse(response.status);
           if(status=='200'){
               Swal.fire({
                   title: 'Successfully Approved!',
                   icon: 'success',
                   customClass: {
                   confirmButton: 'myCustomSuccessButton'
                   },
                   buttonsStyling: false,
                   allowOutsideClick: false,
                   allowEscapeKey: false
               }).then(() => {
                   window.location.reload();
               });
           }
       })
       .catch(httpErrorHandler)
     },

     cancel(adsId){

function httpErrorHandler(error) {
               if (axios.isAxiosError(error)) {
                   const response = error?.response
                   if(response){
                       const statusCode = response?.status
                       if(statusCode===500){alert("Please try again!!!")}
                       else if(statusCode===400){alert("Please try again!!!")}
                       
                   }
                   }
}
axios.delete("http://localhost:8083/admin/decline",{
     params: {
        AdsId: adsId
      }
})
.then(function(response){
       const status=JSON.parse(response.status);
       if(status=='200'){
           Swal.fire({
               title: 'Successfully Canceled!',
               icon: 'success',
               customClass: {
               confirmButton: 'myCustomButton'
               },
               buttonsStyling: false,
               allowOutsideClick: false,
               allowEscapeKey: false
           }).then(() => {
               window.location.reload();
           });

       }
   })
   .catch(httpErrorHandler)
},
    //  pushy(){
    //    router.push('/admin')
    //  },
    //  ban(){
    //    router.push('/admin/ban')
    //  },
     logout(){
           router.push('/admin/login')
       },
    pushhh(){
           router.push('/admin/ad')
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