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
                         @click="ban()">
                        
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
  

     <div class="row mb-5 g-3 box2"  >
                <div v-for="post in posts" :key="post.post_id" class="col-md-3">
                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card" style="height: 600px;">
                            <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                <img :src="url" class="w-100 h-100" alt="Card image cap">
                            </div> -->
                            <div class="cardImgBox mb-2">
                                <img :src="post.photo_url" class="w-100 h-100" alt="Card image cap">
                            </div>
                            <div class="card-body p-4 d-flex flex-column">
                                <h5 class="card-title mb-3">{{ post.title }}</h5>
                                <p class="card-text small opacity-75">{{ post.description }}</p>
                                <p class="card-text text-danger small mb-auto opacity-75 mb-auto ">
                                    <v-icon >mdi-map-marker-radius</v-icon>
                                    {{ post.region }}  ,{{ post.province }}, {{ post.country }}
                                </p>
                                <p>{{ post.status }}</p>
                                <div class="d-flex align-items-center justify-content-between mb-2">
                                    
                                    <span class="badge text-bg-danger rounded-pill">{{ post.property_type }}</span>
                                </div>
                                <div class="d-flex align-items-center justify-content-between">
                                    <p class="m-0 small">{{ post.area }}</p>
                                    <p class="m-0 small fw-bold fs-6">{{ post.price }}</p>
                                    
                                </div>
                                <v-divider :thickness="2" class="border-opacity-25 d-block"/>
                                
                            </div>
                            <div style="padding: 20px;">
                                <v-row>
                                <v-col cols="6">
                                    <v-btn @click="approve()">Approve</v-btn>
                                </v-col>
                                <v-col @click="cancel()" cols="6">
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

export default {
    
    data: () => ({
        posts : [],

      

      
    }),
       
      
 
       mounted(){
         this.fetchPosts();
       },
 
       methods :{
        fetchPosts() {
        // Make API call to fetch posts from backend
       fetch('http://localhost:8083/posts/all      ')
          .then(response => response.json())
          .then(data => {
            console.log(data);
            data.forEach(post => {
                this.posts.push({
                    post_id :post.post_id,
                    status : post.status,
                    province: post.testsellpostss.locations.province,
                    region: post.testsellpostss.locations.region,
                    country: post.testsellpostss.locations.countries.country_name,
                   
                   
                    
                    title: post.testsellpostss.title,
                    description: post.testsellpostss.description,
                    house_type: post.testsellpostss.house_type,
                    property_type: post.testsellpostss.property_type,
                    area: post.testsellpostss.area,
                    price: post.testsellpostss.price,
                    photo_url: 'data:image/jpeg;base64,' + post.testsellpostss.image,
                });
            });
            // console.log(this.posts);
          })
          .catch(error => {
            console.error('Error fetching photos:', error);
          });
      },

      approve(){

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

        axios.put("http://localhost:8083/posts/update",this.posts[0])
        .then(function(response){
                const status=JSON.parse(response.status);
                if(status=='200'){
                  alert("Approved Successfully")

                }
            })
            .catch(httpErrorHandler)
      },
      cancel(){

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

axios.put("http://localhost:8083/posts/decline",this.posts[0])
.then(function(response){
        const status=JSON.parse(response.status);
        if(status=='200'){
          alert("Canceled  Successfully")

        }
    })
    .catch(httpErrorHandler)
},
      pushy(){
        router.push('/admin')
      },
      ban(){
        router.push('/admin/ban')
      },
      logout(){
            router.push('/admin/login')
        },
        pushhh(){
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