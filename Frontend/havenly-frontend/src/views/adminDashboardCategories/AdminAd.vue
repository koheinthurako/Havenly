<template>
    
 <div class="container">
    
 <div class="">
  <h1>Users' pending ads!</h1>
     </div>

     <div v-if="loading">
            <v-row class="g-1 mb-3">
                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>

                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>

                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>

                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>


            </v-row>
        </div>
        <div v-else>
    <div class="row mb-5 g-3 box2"  >
               <div v-for="ad in ads" :key="ad.ads_post_id" class="col-md-3">
                   <div class="card-container">
                       <!-- TZH card styles -->
                       <div class="card" style="height: 400px; ">
                          
                           <div class="cardImgBox mb-1" @click="showDialog = true">
                               <img :src="ad.imageUrl" class="w-100 h-100 m-auto" alt="Card image cap" style="cursor:pointer;">
                           </div>
        <div>
    <v-dialog v-model="showDialog" max-width="600px">
      <v-card>
        <v-card-title class="text-h5">
          
          <button icon @click="showDialog = false" class="close-button">
            <v-icon>mdi-close-circle</v-icon>
          </button>
          <span>{{ ad.title }}</span>
        </v-card-title>
        <v-card-text>
          <v-img :src="ad.imageUrl" aspect-ratio="16/9"></v-img>
        </v-card-text>
      </v-card>
    </v-dialog>                              
                           </div>

                           <div class="card-body px-1 py-1 m-0 d-flex flex-column">
                               <h5 class="card-title mb-2 pl-2">{{ ad.title }}</h5>
                               <p class="pl-2" style="color: #d33;">{{ ad.status }}</p>
                               <v-divider :thickness="2" class="mx-2 border-opacity-25 d-block"/>
                           </div>
                           <div>
                            <v-card-actions class="py-0 m-2">
                                   <v-btn class="green-outline" elevation="0" variant="outlined" @click="approve(ad)" 
                                   style="text-transform:capitalize;">Approve</v-btn>
                               <v-spacer></v-spacer>
                                   <v-btn class="red-outline" elevation="0" variant="outlined" @click="cancel(ad.ads_post_id)"
                                   style="text-transform:capitalize;">Reject</v-btn>
                            </v-card-actions>
                            </div>
                </div>
        </div>
</div>
</div>
</div>
</div>

</template>

<script>
import axios from 'axios';
// import router from '@/router';
import Swal from 'sweetalert2';

export default {
   
   data: () => ({
       ads : [],
       loading: false,
       showDialog: false
   }),

      mounted(){
        this.fetchAds();
      },

      methods :{

       fetchAds() {
       // fetch ads from backend
       this.loading = true
      fetch('http://localhost:8083/ads/all/pending')
          
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
                
            });
          })    
         .catch(error => {
           console.error('Error fetching photos:', error);
         });
         this.loading = false;
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
    
       }
   }

</script>

<style>


/* .container {
 display: flex;
 margin-top:20px; 
  border-top: solid #e86f52 1px ;
 border-bottom: solid #e86f52 1px ;
 border-right: solid #e86f52 1px ; 


 


} */

/* .box1 {
 padding: 10px;
 background-color: #e86f52;
 color: white;
 height: auto;
} */

.box2 {
  /* Distribute available space equally */
 padding: 20px;
 

 
}
.sidebar-link{
 

 text-decoration: none;
 color: white;
 background-color: #e86f52;
 
}

.close-button {
  position: absolute;
  top: 8px;
  right: 8px;
}

.red-outline {
  border-color: #d33 !important;
  color: #d33 !important;
}

.red-outline:hover {
  background-color: #d33 !important;
  color: white !important; /* Change text color to white on hover for better contrast */
}

.green-outline {
  border-color: green !important;
  color: green !important;
}

.green-outline:hover {
  background-color: green !important;
  color: white !important; /* Change text color to white on hover for better contrast */
}

</style>