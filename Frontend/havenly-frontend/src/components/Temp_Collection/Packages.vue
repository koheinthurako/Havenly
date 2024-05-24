<template>
  <div class="d-flex align-center justify-center" style="height: 130vh">
     <div class="temp-package-data">
            <div class="temp-header-row ">
                <div>
                    <p>Join us, {{user.name}}</p>
                    <h3 class="my-2 mx-auto">Choose a package according to your needs!</h3>
                </div>
    <v-container> 
      <v-row>
        <v-col cols="12">
          <swiper
          :keyboard="true" :loop="false" :effect="'coverflow'" :grabCursor="true" :centeredSlides="true"
             :slides-per-view="3" :space-between="30" :mousewheel="true"
            :navigation = "true"
            :coverflowEffect="{
                        rotate: 10,
                        stretch: 10,
                        depth: 120,
                        modifier: 1.5,
                        // slideShadows: true,
                    }" :modules="modules" @slideChange="handleSlideChange" class="mySwiper"
          >
            <swiper-slide v-for="item in items" :key="item.packageTypeId">
              <div class="temp-package">
                            <div style="line-height: 0px;">
              <v-card class="w-200 h-100 px-3">
                <v-card-title>{{ item.packName }}&nbsp;&nbsp;package</v-card-title>
                <v-card-text>
                    <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;
                        <p class="p1">$ {{ item.price }} </p>
                    </div>
                    <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;
                        <p class="p2">Up to {{ item.total_posts }} posts a month.</p>
                    </div> 
                    <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;
                        <p class="p3">Up to {{ item.total_ads }} ads a month.</p>
                    </div>
                                         
                </v-card-text>
                <v-card-actions>
                    <div class="text-center">
    <v-btn type="submit" v-bind:rounded="true" block class="m-2 bg-redbrick text-white mt-3" 
    style="height: 40px; width: 164px;" @click="handleAction(item)">Buy Package</v-btn>
      </div>
                </v-card-actions>
              </v-card>
            </div></div>
            </swiper-slide>
          </swiper>
        </v-col>
      </v-row>
    </v-container>
    </div></div></div>
  </template>
  
  <script>
  import axios from 'axios';
  import { Swiper, SwiperSlide } from 'swiper/vue';
  import 'swiper/css';
  import 'swiper/css/navigation'; 
  import 'swiper/css/pagination'; 
  import router from '@/router';
  import { EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';

  export default {
    name: 'PackagesVue',

    components: {
      Swiper,
      SwiperSlide
    },
    setup() {
        return {
    modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],
              };
    },
    data() {
      return {
        items: [],
        user: {
          name:'John Doe'
        }
      };
    },
    created(){
    const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    if (loginUserData ==null ) { 
        // router.push("/");
        console.log("User is not logged in.");     
    }else{
      this.user.name = loginUserData.name;
    }
    },
    mounted() {
      this.fetchData();
    },
    computed(){

    },
    methods: {
      fetchData() {
        axios.get('http://localhost:8083/package-types/getall') 
          .then(response => {
            this.items = response.data; 
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
    },
    handleAction(item) {
      if( item.packageTypeId !== null){
        if (item.packageTypeId === 1) {
        router.push("/Subscribe");
      } 
      else {
        sessionStorage.setItem('packageName', JSON.stringify(item[item.packageTypeId-1]));
        router.push('/packages/payment');
      // } else if (item.id === 3) {
      //   sessionStorage.setItem('packageName', JSON.stringify(item[item.id-1]));
      //   router.push('/packages/payment');
      }
      }
       else {
        console.log('ERROR parsing DATA for packages.', item);
      }
    },
}
  }
  </script>
  
  <style>
  /* Optional: Custom styles for the swiper */
  .swiper-container {
    width: 100%;
    height: 300px; /* Adjust the height as needed */
  }
  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  </style>
  